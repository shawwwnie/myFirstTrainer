package kr.spring.board.tip.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.free.vo.FreeBoardVO;
import kr.spring.board.tip.dao.TipBoardMapper;
import kr.spring.comment.freec.vo.FreeBoardCommentReplyVO;
import kr.spring.comment.freec.vo.FreeBoardCommentVO;

@Service("tipBoardService")
public class TipBoardServiceImpl implements TipBoardService{

	@Resource
	TipBoardMapper tipBoardMapper;

	@Override
	public List<FreeBoardVO> selectList(Map<String, Object> map) {
		return tipBoardMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return tipBoardMapper.selectRowCount(map);
	}

	@Override
	public FreeBoardVO selectBoardFree(Integer free_num) {
		return tipBoardMapper.selectBoardFree(free_num);
	}

	@Override
	public void insertBoardTip(FreeBoardVO freeBoard) {
		tipBoardMapper.insertBoardTip(freeBoard);
	}

	@Override
	public void updateHitBoardFree(Integer free_num) {
		tipBoardMapper.updateHitBoardFree(free_num);
	}

	@Override
	public void updateBoardFree(FreeBoardVO boardFree) {
		tipBoardMapper.updateBoardFree(boardFree);
	}

	@Override
	public void updateBoardFreeExImg(FreeBoardVO boardFree) {
		tipBoardMapper.updateBoardFreeExImg(boardFree);
	}

	@Override
	public void deleteBoardFree(Integer free_num) {
		tipBoardMapper.deleteBoardFree(free_num);
	}

	//댓글부분
	@Override
	public List<FreeBoardCommentVO> selectListFreeComment(Integer free_num) {
		return tipBoardMapper.selectListFreeComment(free_num);
	}

	@Override
	public int insertFreeComment(FreeBoardCommentVO free_comment) {
		return tipBoardMapper.insertFreeComment(free_comment);
	}

	@Override
	public FreeBoardCommentVO selectOneFreeComment(Integer freec_num) {
		return tipBoardMapper.selectOneFreeComment(freec_num);
	}

	@Override
	public int updateFreeComment(FreeBoardCommentVO free_comment) {
		return tipBoardMapper.updateFreeComment(free_comment);
	}

	@Override
	public int deleteFreeComment(int freec_num) {
		return tipBoardMapper.deleteFreeComment(freec_num);
	}
	
	@Override
	public int DeleteCommentReplySet(int freec_num) {
		return tipBoardMapper.DeleteCommentReplySet(freec_num);
	}

	@Override
	public List<FreeBoardCommentReplyVO> selectReplyComment(Integer freec_num) {
		return tipBoardMapper.selectReplyComment(freec_num);
	}

	@Override
	public int inserReplyComment(FreeBoardCommentReplyVO replyComment) {
		return tipBoardMapper.inserReplyComment(replyComment);
	}

	@Override
	public int deleteReplyComment(int rfreec_num) {
		return tipBoardMapper.deleteReplyComment(rfreec_num);
	}

	@Override
	public int selectBoardWriterMemNum(int free_num) {
		return tipBoardMapper.selectBoardWriterMemNum(free_num);
	}	

	@Override
	public int selectBoardCommentWriterMemnum(int freec_num) {
		return tipBoardMapper.selectBoardCommentWriterMemnum(freec_num);
	}
	
	@Override
	public String selectBoardWriterContent(int free_num) {
		return tipBoardMapper.selectBoardWriterContent(free_num);
	}
	
	@Override
	public String selectBoardCommentWriterContent(int freec_num) {
		return tipBoardMapper.selectBoardCommentWriterContent(freec_num);
	}
	
}
