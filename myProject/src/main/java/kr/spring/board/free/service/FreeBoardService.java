package kr.spring.board.free.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.free.vo.FreeBoardVO;
import kr.spring.comment.freec.vo.FreeBoardCommentVO;

public interface FreeBoardService {
	
	
	public List<FreeBoardVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertBoardFree(FreeBoardVO freeBoardVO);
	public FreeBoardVO selectBoardFree(Integer free_num);
	public void updateHitBoardFree(Integer free_num);
	public void updateBoardFree(FreeBoardVO boardFree);
	public void updateBoardFreeExImg(FreeBoardVO boardFree);
	public void deleteBoardFree(Integer free_num);
	public void deleteBoardCommentSet(Integer free_num);
	public int insertFreeComment(FreeBoardCommentVO free_comment);
	public List<FreeBoardCommentVO> selectListFreeComment(Integer free_num);
	public FreeBoardCommentVO selectOneFreeComment(Integer freec_num);
	public int updateFreeComment(FreeBoardCommentVO free_comment);
	public int deleteFreeComment(int freec_num);
	
	//댓글 알림에 필요한 내용
	public int selectBoardWriterMemNum(int free_num);
	public String selectBoardWriterContent(int free_num);
	
	//신고하기
	public void updateAlarmReport(int board_num);
	//신고 수 초기화
	public void resetAlarmReport(int board_num);
}
