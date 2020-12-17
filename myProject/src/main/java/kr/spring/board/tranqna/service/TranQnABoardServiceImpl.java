package kr.spring.board.tranqna.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.tranqna.dao.TranQnABoardMapper;
import kr.spring.board.tranqna.vo.TranQnABoardVO;
import kr.spring.comment.tranqnac.vo.TranQnABoardCommentReplyVO;
import kr.spring.comment.tranqnac.vo.TranQnABoardCommentVO;

@Service("tranQnABoardService")
public class TranQnABoardServiceImpl implements TranQnABoardService {
	
	@Resource
	TranQnABoardMapper tranQnABoardMapper;

	@Override
	public List<TranQnABoardVO> selectList(Map<String, Object> map) {
		return tranQnABoardMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return tranQnABoardMapper.selectRowCount(map);
	}

	@Override
	public TranQnABoardVO selectBoardTranqna(Integer tq_num) {
		return tranQnABoardMapper.selectBoardTranqna(tq_num);
	}

	@Override
	public void insertBoardTranqna(TranQnABoardVO tranqnaBoard) {
		tranQnABoardMapper.insertBoardTranqna(tranqnaBoard);
	}

	@Override
	public void updateHitBoardTranqna(Integer tq_num) {
		tranQnABoardMapper.updateHitBoardTranqna(tq_num);
	}

	@Override
	public void updateBoardTranqna(TranQnABoardVO tranqnaBoard) {
		tranQnABoardMapper.updateBoardTranqna(tranqnaBoard);
	}

	@Override
	public void updateBoardTranqnaExImg(TranQnABoardVO tranqnaBoard) {
		tranQnABoardMapper.updateBoardTranqnaExImg(tranqnaBoard);
	}

	@Override
	public void deleteBoardTranqna(Integer tq_num) {
		tranQnABoardMapper.deleteBoardTranqna(tq_num);
	}

	@Override
	public List<TranQnABoardCommentVO> selectListTranqnaComment(Integer tq_num) {
		return tranQnABoardMapper.selectListTranqnaComment(tq_num);
	}

	@Override
	public int insertTranqnaComment(TranQnABoardCommentVO tq_comment) {
		return tranQnABoardMapper.insertTranqnaComment(tq_comment);
	}

	@Override
	public TranQnABoardCommentVO selectOneTranqnaComment(Integer tqc_num) {
		return tranQnABoardMapper.selectOneTranqnaComment(tqc_num);
	}

	@Override
	public int updateTranqnaComment(TranQnABoardCommentVO tq_comment) {
		return tranQnABoardMapper.updateTranqnaComment(tq_comment);
	}

	@Override
	public int deleteTranqnaComment(int tqc_num) {
		return tranQnABoardMapper.deleteTranqnaComment(tqc_num);
	}

	@Override
	public List<TranQnABoardCommentReplyVO> selectReplyComment(Integer tqc_num) {
		return tranQnABoardMapper.selectReplyComment(tqc_num);
	}

	@Override
	public int insertReplyComment(TranQnABoardCommentReplyVO replyComment) {
		return tranQnABoardMapper.insertReplyComment(replyComment);
	}

	@Override
	public int deleteReplyComment(int rtqc_num) {
		return tranQnABoardMapper.deleteReplyComment(rtqc_num);
	}

	@Override
	public int selectBoardWriterMemNum(int tq_num) {
		return tranQnABoardMapper.selectBoardWriterMemNum(tq_num);
	}
	
	@Override
	public int selectBoardCommentWriterMemnum(int tqc_num) {
		return tranQnABoardMapper.selectBoardCommentWriterMemnum(tqc_num);
	}
	
	@Override
	public String selectBoardWriterContent(int tq_num) {
		return tranQnABoardMapper.selectBoardWriterContent(tq_num);
	}
	
	@Override
	public String selectBoardCommentWriterContent(int tqc_num) {
		return tranQnABoardMapper.selectBoardCommentWriterContent(tqc_num);
	}
	
}
