package kr.spring.board.tip.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.free.vo.FreeBoardVO;
import kr.spring.comment.freec.vo.FreeBoardCommentReplyVO;
import kr.spring.comment.freec.vo.FreeBoardCommentVO;

public interface TipBoardService {

	//게시판부분
	public List<FreeBoardVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public FreeBoardVO selectBoardFree(Integer free_num);
	public void insertBoardTip(FreeBoardVO freeBoard);
	public void updateHitBoardFree(Integer free_num);
	public void updateBoardFree(FreeBoardVO boardFree);
	public void updateBoardFreeExImg(FreeBoardVO boardFree);
	public void deleteBoardFree(Integer free_num);
	
	//댓글 부분
	public List<FreeBoardCommentVO> selectListFreeComment(Integer free_num);
	public int insertFreeComment(FreeBoardCommentVO free_comment);
	public FreeBoardCommentVO selectOneFreeComment(Integer freec_num);
	public int updateFreeComment(FreeBoardCommentVO free_comment);
	public int deleteFreeComment(int freec_num);
	
	//댓글의 댓글부분(수정은 불가-> 조회, 등록, 삭제)
	public List<FreeBoardCommentReplyVO> selectReplyComment(Integer freec_num);	
	public int inserReplyComment(FreeBoardCommentReplyVO replyComment);	
	public int deleteReplyComment(int rfreec_num);
	public int DeleteCommentReplySet(int freec_num);
	
	//댓글 등록 알림: 댓글이 달리는 사람의 번호
	public int selectBoardWriterMemNum(int free_num);
	public int selectBoardCommentWriterMemnum(int freec_num);
	
	//댓글이 달리는 사람이 쓴 글
	public String selectBoardWriterContent(int free_num);
	public String selectBoardCommentWriterContent(int freec_num);
}
