package kr.spring.board.tip.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.notice.service.NoticeService;
import kr.spring.board.notice.vo.NoticeVO;
import kr.spring.board.tip.service.TipBoardService;
import kr.spring.comment.freec.vo.FreeBoardCommentReplyVO;
import kr.spring.comment.freec.vo.FreeBoardCommentVO;

@Controller
public class TipBoardAjaxController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	TipBoardService tipBoardService;
	
	@Resource
	NoticeService noticeService;
	
	//댓글부분
	@RequestMapping("/boardTip/list_comment.do")
	@ResponseBody
	public List<FreeBoardCommentVO> list(@RequestParam int free_num){
		return tipBoardService.selectListFreeComment(free_num);
	}
	
	@RequestMapping("/boardTip/submit_freecomment.do")
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
		int writer_memNum = tipBoardService.selectBoardWriterMemNum(free_num);
		String writer_board = tipBoardService.selectBoardWriterContent(free_num);
		NoticeVO notice = new NoticeVO();
		notice.setBoard_num(free_num);
		notice.setWriter_memnum(writer_memNum);
		notice.setWriter_board(writer_board);
		notice.setReply_mem_num(mem_num);
		notice.setBoard_comment(comment);
		notice.setNotice_comment("팁게시판 글에 댓글을 등록했습니다.");
		notice.setReturn_url("boardTip/detail.do?free_num="+free_num);
		noticeService.insertNoticeVO(notice);
		if(log.isDebugEnabled()) log.debug("<<알림전달>> :" + notice);
				
		return tipBoardService.insertFreeComment(free_comment);		
	}
	
	@RequestMapping("/boardTip/update_freecomment.do")
	@ResponseBody
	public int updateComment(@RequestParam int freec_num,@RequestParam String update_comment) {
		if(log.isDebugEnabled()) log.debug("<<자유게시판 댓글수정>> : " + freec_num);
		FreeBoardCommentVO free_comment = tipBoardService.selectOneFreeComment(freec_num);
		free_comment.setFree_comment(update_comment);		
		return tipBoardService.updateFreeComment(free_comment);
	}
	
	@RequestMapping("/boardTip/delete_freecomment.do")
	@ResponseBody
	public int deleteComment(@RequestParam int freec_num) {
		if(log.isDebugEnabled()) log.debug("<<자유게시판 댓글 삭제>> : " + freec_num);
		
		tipBoardService.DeleteCommentReplySet(freec_num);		
		return tipBoardService.deleteFreeComment(freec_num);
	}
	
	//댓글의 댓글 리스트 불러오기
	@RequestMapping("/boardTip/list_replyComment.do")
	@ResponseBody
	public List<FreeBoardCommentReplyVO> selectListCofC(@RequestParam int freec_num){
		if(log.isDebugEnabled()) log.debug("<<댓글의 댓글>> : " + freec_num);
		return tipBoardService.selectReplyComment(freec_num);
	}
	
	
	//댓글의 댓글 입력
	@RequestMapping("/boardTip/submit_replyComment.do")
	@ResponseBody
	public int submitReplyComment(@RequestParam String replyComment_content,
									@RequestParam int free_num,
									@RequestParam int freec_num,
									@RequestParam int mem_num) {
		if(log.isDebugEnabled()) log.debug("<<댓글의댓글입력>> : " + replyComment_content);
		
		//댓글의 댓글등록 알림 데이터 넣기
		int writer_memNum = tipBoardService.selectBoardCommentWriterMemnum(freec_num);
		String writer_board = tipBoardService.selectBoardCommentWriterContent(freec_num);
		NoticeVO notice = new NoticeVO();
		notice.setBoard_num(freec_num);
		notice.setWriter_memnum(writer_memNum);
		notice.setWriter_board(writer_board);
		notice.setReply_mem_num(mem_num);
		notice.setBoard_comment(replyComment_content);
		notice.setNotice_comment("팁게시판 댓글에 댓글을 등록했습니다.");
		notice.setReturn_url("boardTip/detail.do?free_num="+free_num);
		noticeService.insertNoticeVO(notice);
		if(log.isDebugEnabled()) log.debug("<<댓글의댓글등록알림전달>> :" + notice);
		
		FreeBoardCommentReplyVO replyComment = new FreeBoardCommentReplyVO();
		replyComment.setFreec_num(freec_num);
		replyComment.setMem_num(mem_num);
		replyComment.setRfreec_comment(replyComment_content);
		return tipBoardService.inserReplyComment(replyComment);
	}
	
	@RequestMapping("/boardTip/delete_replyComment.do")
	@ResponseBody
	public int deleteReplyComment(@RequestParam int rfreec_num) {
		if(log.isDebugEnabled()) log.debug("<<댓글의댓글삭제>> : " + rfreec_num);
		return tipBoardService.deleteReplyComment(rfreec_num);
	}
	
}
