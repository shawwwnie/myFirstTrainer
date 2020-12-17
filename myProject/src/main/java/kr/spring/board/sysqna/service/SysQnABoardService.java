package kr.spring.board.sysqna.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.sysqna.vo.SysQnABoardVO;
import kr.spring.comment.sysqnac.vo.SysQnABoardCommentVO;

public interface SysQnABoardService {

public List<SysQnABoardVO> selectList(Map<String,Object> map);
	
	public int selectRowCount(Map<String,Object> map);
	public void insertBoardSysqna(SysQnABoardVO sysqnaBoardVO);
	public SysQnABoardVO selectBoardSysqna(Integer sq_num);
	public void updateHitBoardSysqna(Integer sq_num);
	public void updateBoardSysqna(SysQnABoardVO sysqnaBoardVO);
	public void updateBoardSysqnaExImg(SysQnABoardVO sysqna);
	public void deleteBoardSysqna(Integer sq_num);
	public int insertSysqnaComment(SysQnABoardCommentVO sq_comment);
	public List<SysQnABoardCommentVO> selectListSysqnaComment(Integer sq_num);
	public SysQnABoardCommentVO selectOneSysqnaComment(Integer sqc_num);
	public int updateSysqnaComment(SysQnABoardCommentVO sq_comment);
	public int deleteSysqnaComment(int sqc_num);
	
	public int selectBoardWriterMemNum(int sq_num);
	public String selectBoardWriterContent(int sq_num);
}
