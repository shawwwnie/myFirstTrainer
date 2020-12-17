package kr.spring.board.free.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.free.service.FreeBoardService;
import kr.spring.board.notice.service.NoticeService;
import kr.spring.board.notice.vo.NoticeVO;
import kr.spring.comment.freec.vo.FreeBoardCommentVO;

@Controller
public class FreeBoardAjaxController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	FreeBoardService freeBoardService;
	
	@Resource
	NoticeService noticeService;
	
	@RequestMapping("/boardFree/list_comment.do")
	@ResponseBody
	public List<FreeBoardCommentVO> list(@RequestParam int free_num){		
		return freeBoardService.selectListFreeComment(free_num);
	}
	
	@RequestMapping("/boardFree/submit_freecomment.do")
	@ResponseBody
	public int process(@RequestParam String comment,
						@RequestParam int free_num,
						@RequestParam int mem_num){
		
		if(log.isDebugEnabled()) log.debug("<<자유게시판 댓글등록>> : " + free_num + ":" + comment);
				
		FreeBoardCommentVO free_comment = new FreeBoardCommentVO();
		free_comment.setFree_comment(comment);
		free_comment.setFree_num(free_num);		
		free_comment.setMem_num(mem_num);
		
		//댓글등록 알림 데이터 넣기
		int writer_memNum = freeBoardService.selectBoardWriterMemNum(free_num);
		String writer_board = freeBoardService.selectBoardWriterContent(free_num);
		NoticeVO notice = new NoticeVO();
		notice.setBoard_num(free_num);
		notice.setWriter_memnum(writer_memNum);
		notice.setWriter_board(writer_board);
		notice.setReply_mem_num(mem_num);
		notice.setBoard_comment(comment);
		notice.setNotice_comment("자유게시판 글에 댓글을 등록했습니다.");
		notice.setReturn_url("boardFree/detail.do?free_num="+free_num);
		noticeService.insertNoticeVO(notice);
		if(log.isDebugEnabled()) log.debug("<<알림전달>> :" + notice);
				
		return freeBoardService.insertFreeComment(free_comment);		
	}
	
	@RequestMapping("/boardFree/update_freecomment.do")
	@ResponseBody
	public int updateComment(@RequestParam int freec_num,@RequestParam String update_comment) {
		if(log.isDebugEnabled()) log.debug("<<자유게시판 댓글수정>> : " + freec_num);
		FreeBoardCommentVO free_comment = freeBoardService.selectOneFreeComment(freec_num);
		free_comment.setFree_comment(update_comment);
		return freeBoardService.updateFreeComment(free_comment);
	}
	
	@RequestMapping("/boardFree/delete_freecomment.do")
	@ResponseBody
	public int deleteComment(@RequestParam int freec_num) {
		if(log.isDebugEnabled()) log.debug("<<자유게시판 댓글 삭제>> : " + freec_num);
		return freeBoardService.deleteFreeComment(freec_num);
	}
	

}
