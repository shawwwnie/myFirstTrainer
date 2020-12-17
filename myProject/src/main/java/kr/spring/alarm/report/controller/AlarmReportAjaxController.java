package kr.spring.alarm.report.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.free.dao.FreeBoardMapper;

@Controller
public class AlarmReportAjaxController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	FreeBoardMapper freeBoardMapper;
	
	//자유게시판에서 신고 수 초기
	@RequestMapping("/boardFree/alarmReset.do")
	@ResponseBody
	public void alarmReset(@RequestParam int board_num) {
		if(log.isDebugEnabled()) log.debug("<<자유게시판신고 초기화>> : " + board_num);
		freeBoardMapper.resetAlarmReport(board_num);		
	}
	
}
