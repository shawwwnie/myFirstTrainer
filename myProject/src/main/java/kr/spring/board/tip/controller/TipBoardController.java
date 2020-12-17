package kr.spring.board.tip.controller;

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

import kr.spring.board.free.service.FreeBoardService;
import kr.spring.board.free.vo.FreeBoardVO;
import kr.spring.board.tip.service.TipBoardService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class TipBoardController {
	
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	TipBoardService tipBoardService;
	
	@Resource
	FreeBoardService freeBoardService;

	//자바빈(VO) 초기화
	@ModelAttribute
	public FreeBoardVO initCommand() {
		return new FreeBoardVO();
	}

	//게시판 목록
	@RequestMapping("/boardTip/list.do")
	public ModelAndView process(
			@RequestParam(value="pageNum",defaultValue="1")
			int currentPage,
			@RequestParam(value="keyfield",defaultValue="")
			String keyfield,
			@RequestParam(value="keyword",defaultValue="")
			String keyword,
			@RequestParam(value="rowCount",defaultValue="5")
			int rowCount) {

		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//총 글의 갯수 또는 검색된 글의 갯수
		int count = tipBoardService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield,keyword,
				              currentPage,count,rowCount,10,"list.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<FreeBoardVO> list = null;
		if(count > 0) {
			list = tipBoardService.selectList(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<글 목록>> : " + list);
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardTipList");
		mav.addObject("count",count);
		mav.addObject("list", list);
		mav.addObject("rowCount",rowCount);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//이미지 출력
	@RequestMapping("/boardTip/imageView.do")
	public ModelAndView viewImage(@RequestParam int free_num) {
		if(log.isDebugEnabled()) log.debug("<<이미지 출력>> : " + free_num);
		FreeBoardVO freeBoardVO = tipBoardService.selectBoardFree(free_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");		
		mav.addObject("imageFile", freeBoardVO.getFree_file());
		mav.addObject("filename", freeBoardVO.getFree_filename());
		return mav;
	}
	
	//글 등록 폼
	@RequestMapping(value="/boardTip/write.do",method=RequestMethod.GET)
	public String form() {
		return "boardTipWrite";
	}
	
	//글 등록 처리
	@RequestMapping(value="/boardTip/write.do",method=RequestMethod.POST)
	public String submit(@Valid FreeBoardVO freeBoardVO, 
							BindingResult result, 
							HttpServletRequest request,
							HttpSession session,
							Model model) {
		
		if(log.isDebugEnabled()) log.debug("<<게시판 글 저장>> :" + freeBoardVO);
		
		if(result.hasErrors()) return form();
		
		MemberVO member = (MemberVO)session.getAttribute("user");
		freeBoardVO.setMem_num(member.getMem_num());
		freeBoardVO.setFree_ip(request.getRemoteAddr());
		freeBoardVO.setFree_type(1);
		tipBoardService.insertBoardTip(freeBoardVO);
		
		model.addAttribute("message", "팁 게시판에 글이 등록되었습니다.");
		model.addAttribute("url",request.getContextPath() + "/boardTip/list.do");
		
		return "common/result";
	}
	
	//글 상세
	@RequestMapping("/boardTip/detail.do")
	public String process(@RequestParam int free_num, Model model, HttpSession session) {
		if(log.isDebugEnabled()) log.debug("<<글상세>> : " + free_num);

		FreeBoardVO freeBoardVO = tipBoardService.selectBoardFree(free_num);

		//다른 아이디로 조회하는 경우에만 해당 글의 조회 수 증가
		MemberVO member = (MemberVO)session.getAttribute("user");
		if(member.getMem_num() != freeBoardVO.getMem_num()) {
			tipBoardService.updateHitBoardFree(free_num);
		}
		
		model.addAttribute("boardFree", freeBoardVO);
		return "boardTipView";
	}
	
	//글 수정 폼 호출
	@RequestMapping(value="/boardTip/modify.do",method=RequestMethod.GET)
	public String updateForm(@RequestParam int free_num, Model model) {
		
		FreeBoardVO freeBoardVO = tipBoardService.selectBoardFree(free_num);
		
		model.addAttribute("boardFree", freeBoardVO);
		
		return "boardTipModify";
	}
	
	//글 수정
	@RequestMapping(value="/boardTip/modify.do", method=RequestMethod.POST)
	public String submitUpdate(@Valid FreeBoardVO freeBoardVO, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		
		if(log.isDebugEnabled()) log.debug("<<글수정>> : " + freeBoardVO);
		
		if(result.hasErrors()) return "boardFreeModify";
		
		freeBoardVO.setFree_ip(request.getRemoteAddr());
		
		if(freeBoardVO.getIsImgUpdate()==1) {
			tipBoardService.updateBoardFreeExImg(freeBoardVO);
		}else {
			tipBoardService.updateBoardFree(freeBoardVO);
		}
		
		
		model.addAttribute("message", "글이 수정되었습니다.");
		model.addAttribute("url", request.getContextPath() + "/boardTip/list.do");
		
		return "common/result";
	}

	//글 삭제
	@RequestMapping("/boardTip/delete.do")
	public String submitDelete(@RequestParam int free_num, Model model, HttpServletRequest request) {
		
		if(log.isDebugEnabled()) log.debug("<<자유게시판 글삭제>> : " + free_num);
		
		freeBoardService.deleteBoardCommentSet(free_num);
		tipBoardService.deleteBoardFree(free_num);
		
		model.addAttribute("message", "글 삭제 완료");
		model.addAttribute("url", request.getContextPath()+"/boardTip/list.do");
		
		return "common/result";
	}
}
