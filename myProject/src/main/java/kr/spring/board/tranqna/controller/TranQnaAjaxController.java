package kr.spring.board.tranqna.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.notice.service.NoticeService;
import kr.spring.board.notice.vo.NoticeVO;
import kr.spring.board.tranqna.dao.TranQnABoardMapper;
import kr.spring.comment.tranqnac.vo.TranQnABoardCommentReplyVO;
import kr.spring.comment.tranqnac.vo.TranQnABoardCommentVO;

@Controller
public class TranQnaAjaxController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	TranQnABoardMapper tranQnABoardMapper;
	
	@Resource
	NoticeService noticeService;
	
	//댓글부분
	@RequestMapping("/boardTranqna/list_comment.do")
	@ResponseBody
	public List<TranQnABoardCommentVO> list(@RequestParam int tq_num){
		return tranQnABoardMapper.selectListTranqnaComment(tq_num);
	}
	
	@RequestMapping("/boardTranqna/submit_tqcomment.do")
	@ResponseBody
	public int process(@RequestParam String comment,
						@RequestParam int tq_num,
						@RequestParam int mem_num){
		
		if(log.isDebugEnabled()) log.debug("<<트레이너 게시판 댓글등록>> : " + tq_num + ":" + comment);
				
		//댓글등록 알림 데이터 넣기
		int writer_memNum = tranQnABoardMapper.selectBoardWriterMemNum(tq_num);
		String writer_board = tranQnABoardMapper.selectBoardWriterContent(tq_num);
		NoticeVO notice = new NoticeVO();
		notice.setBoard_num(tq_num);
		notice.setWriter_memnum(writer_memNum);
		notice.setWriter_board(writer_board);
		notice.setReply_mem_num(mem_num);
		notice.setBoard_comment(comment);
		notice.setNotice_comment("트레이너 게시판 글에 댓글을 등록했습니다.");
		notice.setReturn_url("boardTranqna/detail.do?tq_num="+tq_num);
		noticeService.insertNoticeVO(notice);
		if(log.isDebugEnabled()) log.debug("<<알림전달>> :" + notice);
		
		
		TranQnABoardCommentVO tq_comment = new TranQnABoardCommentVO();
		tq_comment.setTq_comment(comment);
		tq_comment.setTq_num(tq_num);		
		tq_comment.setMem_num(mem_num);
				
		return tranQnABoardMapper.insertTranqnaComment(tq_comment);		
	}
	
	@RequestMapping("/boardTranqna/update_tqcomment.do")
	@ResponseBody
	public int updateComment(@RequestParam int tqc_num,@RequestParam String update_comment) {
		if(log.isDebugEnabled()) log.debug("<<트레이너게시판 댓글수정>> : " + tqc_num);
		TranQnABoardCommentVO tq_comment = tranQnABoardMapper.selectOneTranqnaComment(tqc_num);
		tq_comment.setTq_comment(update_comment);		
		return tranQnABoardMapper.updateTranqnaComment(tq_comment);
	}
	
	@RequestMapping("/boardTranqna/delete_tqcomment.do")
	@ResponseBody
	public int deleteComment(@RequestParam int tqc_num) {
		if(log.isDebugEnabled()) log.debug("<<트레이너게시판 댓글 삭제>> : " + tqc_num);
		return tranQnABoardMapper.deleteTranqnaComment(tqc_num);
	}
	
	//댓글의 댓글
	@RequestMapping("/boardTranqna/list_replyComment.do")
	@ResponseBody
	public List<TranQnABoardCommentReplyVO> selectListCofC(@RequestParam int tqc_num){
		if(log.isDebugEnabled()) log.debug("<<댓글의 댓글>> : " + tqc_num);
		return tranQnABoardMapper.selectReplyComment(tqc_num);
	}
	
	@RequestMapping("/boardTranqna/submit_replyComment.do")
	@ResponseBody
	public int submitReplyComment(@RequestParam String replyComment_content,
									@RequestParam int tq_num,
									@RequestParam int tqc_num,
									@RequestParam int mem_num) {
		if(log.isDebugEnabled()) log.debug("<<댓글의댓글입력>> : " + replyComment_content);
		
		//댓글등록 알림 데이터 넣기
		int writer_memNum = tranQnABoardMapper.selectBoardCommentWriterMemnum(tqc_num);
		String writer_board = tranQnABoardMapper.selectBoardCommentWriterContent(tqc_num);
		NoticeVO notice = new NoticeVO();
		notice.setBoard_num(tqc_num);
		notice.setWriter_memnum(writer_memNum);
		notice.setWriter_board(writer_board);
		notice.setReply_mem_num(mem_num);
		notice.setBoard_comment(replyComment_content);
		notice.setNotice_comment("트레이너 게시판 댓글에 댓글을 등록했습니다.");
		notice.setReturn_url("boardTranqna/detail.do?tq_num="+tq_num);
		noticeService.insertNoticeVO(notice);
		if(log.isDebugEnabled()) log.debug("<<댓글의댓글등록알림전달>> :" + notice);
					
		TranQnABoardCommentReplyVO replyComment = new TranQnABoardCommentReplyVO();
		replyComment.setTqc_num(tqc_num);
		replyComment.setMem_num(mem_num);
		replyComment.setRtqc_comment(replyComment_content);
		return tranQnABoardMapper.insertReplyComment(replyComment);
	}
	
	@RequestMapping("/boardTranqna/delete_replyComment.do")
	@ResponseBody
	public int deleteReplyComment(@RequestParam int rtqc_num) {
		if(log.isDebugEnabled()) log.debug("<<댓글의댓글삭제>> : " + rtqc_num);
		return tranQnABoardMapper.deleteReplyComment(rtqc_num);
	}
	
}
