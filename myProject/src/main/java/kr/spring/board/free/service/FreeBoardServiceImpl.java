package kr.spring.board.free.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.free.dao.FreeBoardMapper;
import kr.spring.board.free.vo.FreeBoardVO;
import kr.spring.comment.freec.vo.FreeBoardCommentVO;

@Service("freeBoardService")
public class FreeBoardServiceImpl implements FreeBoardService{
	
	@Resource
	FreeBoardMapper freeBoardMapper;

	@Override
	public List<FreeBoardVO> selectList(Map<String, Object> map) {
		return freeBoardMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return freeBoardMapper.selectRowCount(map);
	}

	@Override
	public void insertBoardFree(FreeBoardVO freeBoardVO){
		freeBoardMapper.insertBoardFree(freeBoardVO);
	}

	@Override
	public FreeBoardVO selectBoardFree(Integer free_num) {
		return freeBoardMapper.selectBoardFree(free_num);
	}

	@Override
	public void updateHitBoardFree(Integer free_num) {
		freeBoardMapper.updateHitBoardFree(free_num);
	}

	@Override
	public void updateBoardFree(FreeBoardVO boardFree) {
		freeBoardMapper.updateBoardFree(boardFree);
	}

	@Override
	public void updateBoardFreeExImg(FreeBoardVO boardFree) {
		freeBoardMapper.updateBoardFreeExImg(boardFree);
	}

	@Override
	public void deleteBoardFree(Integer free_num) {
		freeBoardMapper.deleteBoardFree(free_num);
	}

	@Override
	public void deleteBoardCommentSet(Integer free_num) {
		freeBoardMapper.deleteBoardCommentSet(free_num);
	}
	
	@Override
	public int insertFreeComment(FreeBoardCommentVO free_comment) {
		return freeBoardMapper.insertFreeComment(free_comment);		
	}

	@Override
	public List<FreeBoardCommentVO> selectListFreeComment(Integer free_num) {
		return freeBoardMapper.selectListFreeComment(free_num);
	}

	@Override
	public int updateFreeComment(FreeBoardCommentVO free_comment) {
		return freeBoardMapper.updateFreeComment(free_comment);
	}

	@Override
	public FreeBoardCommentVO selectOneFreeComment(Integer freec_num) {
		return freeBoardMapper.selectOneFreeComment(freec_num);
	}

	@Override
	public int deleteFreeComment(int freec_num) {
		return freeBoardMapper.deleteFreeComment(freec_num);
	}
	
	@Override
	public int selectBoardWriterMemNum(int free_num) {
		return freeBoardMapper.selectBoardWriterMemNum(free_num);
	}
	
	@Override
	public String selectBoardWriterContent(int free_num) {
		return freeBoardMapper.selectBoardWriterContent(free_num);
	}

	@Override
	public void updateAlarmReport(int board_num) {
		freeBoardMapper.updateAlarmReport(board_num);
	}
	
	@Override
	public void resetAlarmReport(int board_num) {
		freeBoardMapper.resetAlarmReport(board_num);
	}
	
}
