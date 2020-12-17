package kr.spring.alarm.report.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.alarm.report.dao.AlarmReportMapper;
import kr.spring.alarm.report.vo.AlarmReportVO;

@Service("AlarmReportService")
public class AlarmReportServiceImpl implements AlarmReportService{
	
	@Resource
	AlarmReportMapper alarmReportMapper;
	
	
	@Override
	public void insertAlarmReport(AlarmReportVO reportVO) {
		alarmReportMapper.insertAlarmReport(reportVO);
	}

	@Override
	public AlarmReportVO selectIsReported(AlarmReportVO reportVO) {
		return alarmReportMapper.selectIsReported(reportVO);
	}

	
	
}
