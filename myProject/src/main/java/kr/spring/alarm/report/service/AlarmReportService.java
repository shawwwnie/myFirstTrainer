package kr.spring.alarm.report.service;

import kr.spring.alarm.report.vo.AlarmReportVO;

public interface AlarmReportService {

	public void insertAlarmReport(AlarmReportVO reportVO);
	public AlarmReportVO selectIsReported(AlarmReportVO reportVO);
	
}
