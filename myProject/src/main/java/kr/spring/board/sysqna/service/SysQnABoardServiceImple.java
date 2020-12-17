package kr.spring.board.sysqna.service;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.sysqna.dao.SysQnABoardMapper;
import kr.spring.board.sysqna.vo.SysQnABoardVO;
import kr.spring.comment.sysqnac.vo.SysQnABoardCommentVO;


@Service("sysQnABoardService")
public class SysQnABoardServiceImple implements SysQnABoardService {
	
	@Resource
	SysQnABoardMapper sysQnaBoardMapper;

	@Override
	public List<SysQnABoardVO> selectList(Map<String, Object> map) {
		return sysQnaBoardMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return sysQnaBoardMapper.selectRowCount(map);
	}

	@Override
	public void insertBoardSysqna(SysQnABoardVO sysqnaBoardVO) {
		sysQnaBoardMapper.insertBoardSysqna(sysqnaBoardVO);		
	}

	@Override
	public SysQnABoardVO selectBoardSysqna(Integer sq_num) {
		return sysQnaBoardMapper.selectBoardSysqna(sq_num);
	}

	@Override
	public void updateHitBoardSysqna(Integer sq_num) {
		sysQnaBoardMapper.updateHitBoardSysqna(sq_num);
	}

	@Override
	public void updateBoardSysqna(SysQnABoardVO sysqnaBoardVO) {
		sysQnaBoardMapper.updateBoardSysqna(sysqnaBoardVO);
	}

	@Override
	public void updateBoardSysqnaExImg(SysQnABoardVO sysqna) {
		sysQnaBoardMapper.updateBoardSysqnaExImg(sysqna);
	}

	@Override
	public void deleteBoardSysqna(Integer sq_num) {
		sysQnaBoardMapper.deleteBoardSysqna(sq_num);
	}

	@Override
	public int insertSysqnaComment(SysQnABoardCommentVO sq_comment) {
		return sysQnaBoardMapper.insertSysqnaComment(sq_comment);
	}

	@Override
	public List<SysQnABoardCommentVO> selectListSysqnaComment(Integer sq_num) {
		return sysQnaBoardMapper.selectListSysqnaComment(sq_num);
	}

	@Override
	public SysQnABoardCommentVO selectOneSysqnaComment(Integer sqc_num) {
		return sysQnaBoardMapper.selectOneSysqnaComment(sqc_num);
	}

	@Override
	public int updateSysqnaComment(SysQnABoardCommentVO sq_comment) {
		return sysQnaBoardMapper.updateSysqnaComment(sq_comment);
	}

	@Override
	public int deleteSysqnaComment(int sqc_num) {
		return sysQnaBoardMapper.deleteSysqnaComment(sqc_num);
	}

	@Override
	public int selectBoardWriterMemNum(int sq_num) {
		return sysQnaBoardMapper.selectBoardWriterMemNum(sq_num);
	}
	
	@Override
	public String selectBoardWriterContent(int sq_num) {
		return sysQnaBoardMapper.selectBoardWriterContent(sq_num);
	}

}
