package kr.spring.board.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;

import kr.spring.board.notice.vo.NoticeVO;


public interface NoticeMapper {

	public List<NoticeVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	
	public void insertNoticeVO(NoticeVO notice);
	
	//확인 누를 시 알림 한 줄만 삭제
	@Delete("DELETE FROM notice WHERE notice_num=#{notice_num}")
	public void confirmOneNotice(Integer notice_num);
	
	//이동 누를 시 게시판에 있는 댓글목록들 한꺼번에 삭제
	@Delete("DELETE FROM notice WHERE board_num=#{board_num} AND writer_memnum=#{writer_memnum}")
	public void updateNoticeCheckdate(NoticeVO notice);
	
	//알림전체 확인 누를 시 전체 알림 삭제
	@Delete("DELETE FROM notice WHERE writer_memnum=#{writer_memnum}")
	public void confirmAllNotice(Integer writer_memnum);

	
}
