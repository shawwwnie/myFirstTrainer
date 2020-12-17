package kr.spring.alarm.report.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.alarm.report.service.AlarmReportService;
import kr.spring.alarm.report.vo.AlarmReportVO;
import kr.spring.board.free.service.FreeBoardService;

@Controller
public class AlarmReportController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	AlarmReportService alarmReportService;
	
	@Resource
	FreeBoardService freeBoardService;
	
	@ModelAttribute
	public AlarmReportVO initCommand() {
		return new AlarmReportVO();
	}

	@RequestMapping("/alarmReport/report.do")
	public String alarmListView(@RequestParam(value="board_num") int board_num, Model model) {
		model.addAttribute("board_num", board_num);
		return "alarmReport/report";
	}
	
	@RequestMapping(value="/alarmReport/submitReport.do",method=RequestMethod.POST)
	public String alarmReport(AlarmReportVO reportVO, Model model, HttpServletRequest request) {
		if(log.isDebugEnabled()) log.debug("<<신고신청>> : " + reportVO);
		
		int board_num = reportVO.getBoard_num();
		int reported_memnum = -1;
		
		//중복 신고인지 확인
		AlarmReportVO isReportVO = alarmReportService.selectIsReported(reportVO);
		if(isReportVO != null) {
			return "alarmReport/isReported";
		}else {
		
			//자유게시판인 경우
			if(reportVO.getBoard_type()==0) {
				freeBoardService.updateAlarmReport(board_num);
				reported_memnum = freeBoardService.selectBoardWriterMemNum(board_num);
			}
			reportVO.setReported_memnum(reported_memnum);	
			alarmReportService.insertAlarmReport(reportVO);
			return "alarmReport/submit";
		}
	}
	
}
