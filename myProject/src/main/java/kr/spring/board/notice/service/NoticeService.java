package kr.spring.board.notice.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.notice.vo.NoticeVO;

public interface NoticeService {

	public List<NoticeVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public void insertNoticeVO(NoticeVO notice);
	
	public void confirmOneNotice(Integer notice_num);
	public void updateNoticeCheckdate(NoticeVO notice);
	public void confirmAllNotice(Integer writer_memnum);
}
