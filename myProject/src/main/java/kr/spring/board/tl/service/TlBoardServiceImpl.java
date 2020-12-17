package kr.spring.board.tl.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.tl.dao.TlBoardMapper;
import kr.spring.board.tl.vo.TlBoardVO;
import kr.spring.comment.tl.vo.TlBoardCommentVO;


@Service("TlBoardService")
public class TlBoardServiceImpl implements TlBoardService {
	
	@Resource
	TlBoardMapper tlmapper;

	@Override
	public List<TlBoardVO> selectTrainerList() {
		return tlmapper.selectTrainerList();
	}

	@Override
	public int selectTrainerRowCount() {
		return tlmapper.selectTrainerRowCount();
	}
	
	@Override
	public TlBoardVO selectTrainerDetail(Integer mem_num) {
		return tlmapper.selectTrainerDetail(mem_num);
	}

	@Override
	public void updateTrainerProfile(TlBoardVO tlboardVO) {
		tlmapper.updateTrainerProfile(tlboardVO);
	}

	@Override
	public void updateIntro(TlBoardVO tlBoardVO) {
		tlmapper.updateIntro(tlBoardVO);	
	}

	
	@Override
	public void insertMatching(Map<String, Object> map) {
		map.put("mat_num", tlmapper.selectMat_num()); 
		tlmapper.insertMatching(map);
	}

	@Override
	public List<TlBoardVO> matchingList(Map<String, Object> map) {
		return tlmapper.matchingList(map);
	}

	@Override
	public int matchingCount(int mem_num) {
		return tlmapper.matchingCount(mem_num);
	}

	@Override
	public TlBoardVO selectMemberDetail(Integer mem_num) {
		return tlmapper.selectMemberDetail(mem_num);
	}

	@Override
	public void deleteMatchingCancle(Integer mem_num) {
		tlmapper.deleteMatchingCancle(mem_num);
	}

	@Override
	public void updateTNum(Map<String, Object> map) {
		tlmapper.updateTNum(map);
	}

	@Override
	public void insertTrainingTable(Map<String, Object> map) {
		map.put("training_num", tlmapper.selectMat_num()); 
		tlmapper.insertTrainingTable(map);	
	}

	@Override
	public void deleteTnum(Integer mem_num) {
		tlmapper.deleteTnum(mem_num);
	}

	@Override
	public void deleteTraining(Integer mem_num) {
		tlmapper.deleteTraining(mem_num);
	}

	@Override
	public TlBoardVO selectMatchingInfo(Integer mem_num) {
		return tlmapper.selectMatchingInfo(mem_num);
	}

	@Override
	public void insertTlBoardComment(TlBoardCommentVO tlBoardCommentVO) {
		tlmapper.insertTlBoardComment(tlBoardCommentVO);
	}

	@Override
	public List<TlBoardCommentVO> selectTlBoardCommentList(Map<String,Object> map) {
		return tlmapper.selectTlBoardCommentList(map);
	}

	@Override
	public void updateTlBoardComment(TlBoardCommentVO tlBoardCommentVO) {
		tlmapper.updateTlBoardComment(tlBoardCommentVO);
	}

	@Override
	public void deleteTlBoardComment(int tlc_num) {
		tlmapper.deleteTlBoardComment(tlc_num);
	}

	@Override
	public int selectTlBoardCommentCount(Map<String, Object> map) {
		
		return tlmapper.selectTlBoardCommentCount(map);
	}

	@Override
	public TlBoardCommentVO selectOneComment(int tlc_num) {
		
		return tlmapper.selectOneComment(tlc_num);
	}
 
}
