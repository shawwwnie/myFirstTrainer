package kr.spring.board.notice.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.notice.dao.NoticeMapper;
import kr.spring.board.notice.vo.NoticeVO;

@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService {
	
	@Resource
	NoticeMapper noticeMapper;

	@Override
	public List<NoticeVO> selectList(Map<String, Object> map) {
		return noticeMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return noticeMapper.selectRowCount(map);
	}

	@Override
	public void insertNoticeVO(NoticeVO notice) {
		noticeMapper.insertNoticeVO(notice);
	}

	@Override
	public void confirmOneNotice(Integer notice_num) {
		noticeMapper.confirmOneNotice(notice_num);
	}
	
	@Override
	public void updateNoticeCheckdate(NoticeVO notice) {
		noticeMapper.updateNoticeCheckdate(notice);
	}
	
	@Override
	public void confirmAllNotice(Integer writer_memnum) {
		noticeMapper.confirmAllNotice(writer_memnum);
	}
}
