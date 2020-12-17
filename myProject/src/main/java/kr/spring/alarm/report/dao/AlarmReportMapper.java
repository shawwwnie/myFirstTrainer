package kr.spring.alarm.report.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.alarm.report.vo.AlarmReportVO;

public interface AlarmReportMapper {
	
	//신고데이터 넣기
	@Insert("INSERT INTO alarmreport (alarm_num,board_type,board_num,alarm,mem_num,reported_memnum) VALUES (alarm_num_seq.nextval,#{board_type},#{board_num},#{alarm},#{mem_num},#{reported_memnum})")
	public void insertAlarmReport(AlarmReportVO reportVO);
	
	//중복신고 확인하기
	@Select("SELECT * FROM alarmreport WHERE BOARD_TYPE=#{board_type} AND BOARD_NUM=#{board_num}")
	public AlarmReportVO selectIsReported(AlarmReportVO reportVO);
	

}
