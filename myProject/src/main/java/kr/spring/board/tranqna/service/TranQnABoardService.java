package kr.spring.board.tranqna.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.tranqna.vo.TranQnABoardVO;
import kr.spring.comment.tranqnac.vo.TranQnABoardCommentReplyVO;
import kr.spring.comment.tranqnac.vo.TranQnABoardCommentVO;

public interface TranQnABoardService {
	
	public List<TranQnABoardVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public TranQnABoardVO selectBoardTranqna(Integer tq_num);
	public void insertBoardTranqna(TranQnABoardVO tranqnaBoard);
	public void updateHitBoardTranqna(Integer tq_num);
	public void updateBoardTranqna(TranQnABoardVO tranqnaBoard);
	public void updateBoardTranqnaExImg(TranQnABoardVO tranqnaBoard);
	public void deleteBoardTranqna(Integer tq_num);
	
	//댓글부분
	public List<TranQnABoardCommentVO> selectListTranqnaComment(Integer tq_num);
	public int insertTranqnaComment(TranQnABoardCommentVO tq_comment);
	public TranQnABoardCommentVO selectOneTranqnaComment(Integer tqc_num);
	public int updateTranqnaComment(TranQnABoardCommentVO tq_comment);
	public int deleteTranqnaComment(int tqc_num);
	
	//댓글의댓글부분
	public List<TranQnABoardCommentReplyVO> selectReplyComment(Integer tqc_num);
	public int insertReplyComment(TranQnABoardCommentReplyVO replyComment);
	public int deleteReplyComment(int rtqc_num);
	
	//알림전달에 필요한 데이터 :글 쓴 사람의 멤버번호
	public int selectBoardWriterMemNum(int tq_num);
	public int selectBoardCommentWriterMemnum(int tqc_num);
	
	//댓글이 달리는 사람이 쓴 글
	public String selectBoardWriterContent(int tq_num);
	public String selectBoardCommentWriterContent(int tqc_num);

	
}
