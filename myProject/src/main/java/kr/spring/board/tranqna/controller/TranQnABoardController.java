package kr.spring.board.tranqna.controller;

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

import kr.spring.board.tranqna.dao.TranQnABoardMapper;
import kr.spring.board.tranqna.vo.TranQnABoardVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class TranQnABoardController {
	
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	TranQnABoardMapper tranQnABoardMapper;

	//자바빈(VO) 초기화
	@ModelAttribute
	public TranQnABoardVO initCommand() {
		return new TranQnABoardVO();
	}

	//게시판 목록
	@RequestMapping("/boardTranqna/list.do")
	public ModelAndView process(
					@RequestParam(value="pageNum",defaultValue="1")
					int currentPage,
					@RequestParam(value="keyfield",defaultValue="")
					String keyfield,
					@RequestParam(value="keyword",defaultValue="")
					String keyword,
					@RequestParam(value="rowCount",defaultValue="10")
					int rowCount,
					@RequestParam(value="tq_type",defaultValue="2")
					int tq_type) {

		Map<String,Object> map = 
				new HashMap<String,Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		map.put("tq_type", tq_type);
		
		//총 글의 갯수 또는 검색된 글의 갯수
		int count = tranQnABoardMapper.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield,keyword,
				              currentPage,count,rowCount,10,"list.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<TranQnABoardVO> list = null;
		if(count > 0) {
			list = tranQnABoardMapper.selectList(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<글 목록>> : " + list);
			}
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardTranqnaList");
		mav.addObject("count",count);
		mav.addObject("list", list);
		mav.addObject("rowCount",rowCount);
		mav.addObject("tq_type",tq_type);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//이미지 출력
	@RequestMapping("/boardTranqna/imageView.do")
	public ModelAndView viewImage(@RequestParam int tq_num) {
		if(log.isDebugEnabled()) log.debug("<<이미지 출력>> : " + tq_num);
		TranQnABoardVO tranqnaBoardVO = tranQnABoardMapper.selectBoardTranqna(tq_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");		
		mav.addObject("imageFile", tranqnaBoardVO.getTq_file());
		mav.addObject("filename", tranqnaBoardVO.getTq_filename());
		return mav;
	}
	
	//글 등록 폼
	@RequestMapping(value="/boardTranqna/write.do",method=RequestMethod.GET)
	public String form() {
		return "boardTranqnaWrite";
	}
	
	//글 등록 처리
	@RequestMapping(value="/boardTranqna/write.do",method=RequestMethod.POST)
	public String submit(@Valid TranQnABoardVO tranqnaBoardVO, 
							BindingResult result, 
							HttpServletRequest request,
							HttpSession session,
							Model model) {
		
		if(log.isDebugEnabled()) log.debug("<<트레이너 게시판 글 저장>> :" + tranqnaBoardVO);
		
		if(result.hasErrors())	{
			System.out.println(result.toString());
			return form();
		}
		
		MemberVO member = (MemberVO)session.getAttribute("user");
		tranqnaBoardVO.setMem_num(member.getMem_num());
		tranqnaBoardVO.setTq_ip(request.getRemoteAddr());
		tranQnABoardMapper.insertBoardTranqna(tranqnaBoardVO);
		
		model.addAttribute("message", "트레이너 게시판에 글이 등록되었습니다.");
		model.addAttribute("url",request.getContextPath() + "/boardTranqna/list.do");
		
		return "common/result";
	}
	
	//글 상세
	@RequestMapping("/boardTranqna/detail.do")
	public String process(@RequestParam int tq_num, Model model, HttpSession session) {
		if(log.isDebugEnabled()) log.debug("<<글상세>> : " + tq_num);

		TranQnABoardVO tranqnaBoardVO = tranQnABoardMapper.selectBoardTranqna(tq_num);

		//다른 아이디로 조회하는 경우에만 해당 글의 조회 수 증가
		MemberVO member = (MemberVO)session.getAttribute("user");
		if(member.getMem_num() != tranqnaBoardVO.getMem_num()) {
			tranQnABoardMapper.updateHitBoardTranqna(tq_num);
		}
		
		model.addAttribute("boardTranqna", tranqnaBoardVO);
		return "boardTranqnaView";
	}
	
	//글 수정 폼 호출
	@RequestMapping(value="/boardTranqna/modify.do",method=RequestMethod.GET)
	public String updateForm(@RequestParam int tq_num, Model model) {
		
		TranQnABoardVO tranqnaBoardVO = tranQnABoardMapper.selectBoardTranqna(tq_num);
		
		model.addAttribute("boardTranqna", tranqnaBoardVO);
		
		return "boardTranqnaModify";
	}
	
	//글 수정
	@RequestMapping(value="/boardTranqna/modify.do", method=RequestMethod.POST)
	public String submitUpdate(@Valid TranQnABoardVO tranqnaBoardVO, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		
		if(log.isDebugEnabled()) log.debug("<<글수정>> : " + tranqnaBoardVO);
		
		if(result.hasErrors()) updateForm(tranqnaBoardVO.getTq_num(),model);
		
		
		tranqnaBoardVO.setTq_ip(request.getRemoteAddr());
	
		
		if(tranqnaBoardVO.getIsImgUpdate()==1) {
			tranQnABoardMapper.updateBoardTranqnaExImg(tranqnaBoardVO);
		}else {
			tranQnABoardMapper.updateBoardTranqna(tranqnaBoardVO);
		}
		
		
		model.addAttribute("message", "글이 수정되었습니다.");
		model.addAttribute("url", request.getContextPath() + "/boardTranqna/list.do");
		
		return "common/result";
	}

	//글 삭제
	@RequestMapping("/boardTranqna/delete.do")
	public String submitDelete(@RequestParam int tq_num, Model model, HttpServletRequest request) {
		
		if(log.isDebugEnabled()) log.debug("<<트레이너 게시판 글삭제>> : " + tq_num);
		
		tranQnABoardMapper.deleteBoardTranqna(tq_num);
		
		model.addAttribute("message", "글 삭제 완료");
		model.addAttribute("url", request.getContextPath()+"/boardTranqna/list.do");
		
		return "common/result";
	}
	
}
