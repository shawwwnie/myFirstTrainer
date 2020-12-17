package kr.spring.board.sysqna.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.sysqna.service.SysQnABoardService;
import kr.spring.board.sysqna.vo.SysQnABoardVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;



@Controller
public class SysQnABoardController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	SysQnABoardService sysQnABoardService;

	//자바빈(VO) 초기화
	@ModelAttribute
	public SysQnABoardVO initCommand() {
		return new SysQnABoardVO();
	}

	//게시판 목록
	@RequestMapping("/boardSysqna/list.do")
	public ModelAndView process(
			@RequestParam(value="pageNum",defaultValue="1") int currentPage,
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword,
			@RequestParam(value="rowCount",defaultValue="10") int rowCount) {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		int count = sysQnABoardService.selectRowCount(map);

		if(log.isDebugEnabled()) log.debug("<<count>> : " + count);

		PagingUtil page = new PagingUtil(keyfield,keyword,currentPage,count,rowCount,10,"list.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<SysQnABoardVO> list = null;
		if(count > 0) {
			list = sysQnABoardService.selectList(map);

			if(log.isDebugEnabled()) {
				log.debug("<<글 목록>> : " + list);
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardSysqnaList");
		mav.addObject("count",count);
		mav.addObject("list", list);
		mav.addObject("rowCount", rowCount);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;

	}
	
	//글쓰기 폼
	@RequestMapping(value="/boardSysqna/write.do",method=RequestMethod.GET)
	public String form() {
		return "boardSysqnaWrite";
	}

	//글 등록 처리
	@RequestMapping(value="/boardSysqna/write.do",method=RequestMethod.POST)
	public String submit(@Valid SysQnABoardVO sysqnaBoardVO, 
							BindingResult result, 
							HttpServletRequest request,
							HttpSession session,
							Model model) {
		
		if(log.isDebugEnabled()) log.debug("<<sysqna게시판 글 저장>> :" + sysqnaBoardVO);
		
		if(result.hasErrors()) return form();
		
		MemberVO member = (MemberVO)session.getAttribute("user");
		sysqnaBoardVO.setMem_num(member.getMem_num());
		sysqnaBoardVO.setSq_ip(request.getRemoteAddr());
		sysQnABoardService.insertBoardSysqna(sysqnaBoardVO);
		
		model.addAttribute("message", "시스템 문의 게시에 글이 등록되었습니다.");
		model.addAttribute("url",request.getContextPath() + "/boardSysqna/list.do");
		
		return "common/result";
	}
	
	//글 상세
	@RequestMapping("/boardSysqna/detail.do")
	public String detail(@RequestParam int sq_num, Model model, HttpSession session) {
		if(log.isDebugEnabled()) log.debug("<<글상세>> : " + sq_num);

		SysQnABoardVO sysqnaBoardVO = sysQnABoardService.selectBoardSysqna(sq_num);
		
		//다른 아이디로 조회하는 경우에만 해당 글의 조회 수 증가
		MemberVO member = (MemberVO)session.getAttribute("user");
		if(member.getMem_num() != sysqnaBoardVO.getMem_num()) {
			sysQnABoardService.updateHitBoardSysqna(sq_num);
		}
		
		model.addAttribute("boardSysqna", sysqnaBoardVO);
		return "boardSysqnaView";
	}

	//이미지 출력
	@RequestMapping("/boardSysqna/imageView.do")
	public ModelAndView viewImage(@RequestParam int sq_num) {
		SysQnABoardVO sysqnaBoardVO = sysQnABoardService.selectBoardSysqna(sq_num);	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");		
		mav.addObject("imageFile", sysqnaBoardVO.getSq_file());
		mav.addObject("filename", sysqnaBoardVO.getSq_filename());
		return mav;
	}

	//글 수정 폼 호출
	@RequestMapping(value="/boardSysqna/modify.do",method=RequestMethod.GET)
	public String updateForm(@RequestParam int sq_num, Model model) {
		
		if(log.isDebugEnabled()) log.debug("<<글 수정 폼 호출>> " + sq_num);
		
		SysQnABoardVO sysqnaBoardVO = sysQnABoardService.selectBoardSysqna(sq_num);	
		
		model.addAttribute("boardSysqna", sysqnaBoardVO);
		
		return "boardSysqnaModify";
	}
	
	//글 수정
	@RequestMapping(value="/boardSysqna/modify.do", method=RequestMethod.POST)
	public String submitUpdate(@Valid SysQnABoardVO sysqnaBoardVO, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		
		if(log.isDebugEnabled()) log.debug("<<글수정>> : " + sysqnaBoardVO);
		
		if(result.hasErrors()) return "boardSysqnaModify";
		
		sysqnaBoardVO.setSq_ip(request.getRemoteAddr());
		
		if(sysqnaBoardVO.getIsImgUpdate()==1) {
			sysQnABoardService.updateBoardSysqnaExImg(sysqnaBoardVO);
		}else {
			sysQnABoardService.updateBoardSysqna(sysqnaBoardVO);
		}
		
		
		model.addAttribute("message", "글이 수정되었습니다.");
		model.addAttribute("url", request.getContextPath() + "/boardSysqna/list.do");
		
		return "common/result";
	}

	//글 삭제
	@RequestMapping("/boardSysqna/delete.do")
	public String submitDelete(@RequestParam int sq_num, Model model, HttpServletRequest request) {
		
		if(log.isDebugEnabled()) log.debug("<<자유게시판 글삭제>> : " + sq_num);
		
		sysQnABoardService.deleteBoardSysqna(sq_num);
		
		model.addAttribute("message", "글 삭제 완료");
		model.addAttribute("url", request.getContextPath()+"/boardSysqna/list.do");
		
		return "common/result";
	}
}
