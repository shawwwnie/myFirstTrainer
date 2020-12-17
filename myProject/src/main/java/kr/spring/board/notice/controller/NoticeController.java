package kr.spring.board.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.notice.service.NoticeService;
import kr.spring.board.notice.vo.NoticeVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class NoticeController {

	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	NoticeService noticeService;

	@Resource
	MemberService memberService;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public NoticeVO initCommand() {
		return new NoticeVO();
	}

	//게시판 목록
	@RequestMapping("/boardNotice/list.do")
	public ModelAndView process(
						@RequestParam(value="pageNum",defaultValue="1")
						int currentPage,
						@RequestParam(value="rowCount",defaultValue="10")
						int rowCount,
						HttpSession session) {

		Map<String,Object> map = new HashMap<String,Object>();
		
		MemberVO member =  (MemberVO)session.getAttribute("user");
		map.put("writer_memnum", member.getMem_num());
		//총 글의 갯수 또는 검색된 글의 갯수
		int count = noticeService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil("","", currentPage,count,rowCount,10,"list.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<NoticeVO> list = null;
		if(count > 0) {
			list = noticeService.selectList(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<글 목록>> : " + list);
			}
		}
		
		int noticeCount = memberService.selectNoticeCount(member.getMem_num());
		session.setAttribute("noticeCount", noticeCount);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("NoticeList");
		mav.addObject("count",count);
		mav.addObject("list", list);
		mav.addObject("rowCount", rowCount);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
}
