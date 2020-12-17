package kr.spring.board.notice.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.notice.service.NoticeService;
import kr.spring.board.notice.vo.NoticeVO;
import kr.spring.member.service.MemberService;

@Controller
public class NoticeAjaxController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	NoticeService noticeService;
	
	@Resource
	MemberService memberService;
	
	@RequestMapping("/boardNotice/confirmOneNotice.do")
	@ResponseBody
	public void confirmOneNotice(@RequestParam int notice_num) {
		if(log.isDebugEnabled()) log.debug("<<알림 하나만 체크>> : " + notice_num);
		noticeService.confirmOneNotice(notice_num);
	}
	
	@RequestMapping("/boardNotice/update_checkdate.do")
	@ResponseBody
	public void updateComment(@RequestParam int board_num, @RequestParam int writer_memnum) {
		if(log.isDebugEnabled()) log.debug("<<알림 확인>> : " + board_num);
		NoticeVO notice = new NoticeVO();
		notice.setBoard_num(board_num);
		notice.setWriter_memnum(writer_memnum);
		noticeService.updateNoticeCheckdate(notice);	
	}
	
	@RequestMapping("/boardNotice/confirmAll.do")
	@ResponseBody
	public void confirmAllNotice(@RequestParam int writer_memnum) {
		if(log.isDebugEnabled()) log.debug("<<알림전체확인>> : " + writer_memnum);
		noticeService.confirmAllNotice(writer_memnum);
	}
	
	@RequestMapping("/boardNotice/updateNoticeCount.do")
	@ResponseBody
	public void updateNoticeCount(@RequestParam int mem_num,HttpSession session) {
		if(log.isDebugEnabled()) log.debug("<<알림개수 업댓>>");
		int noticeCount = memberService.selectNoticeCount(mem_num);
		session.setAttribute("noticeCount", noticeCount);
	}
	
}
