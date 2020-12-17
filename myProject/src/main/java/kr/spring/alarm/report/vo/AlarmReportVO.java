package kr.spring.alarm.report.vo;

public class AlarmReportVO {
	
	private int alarm_num;
	//0=자유,팁게시판 1=트레이너게시판
	private int board_type;
	//그 게시판의 번호
	private int board_num;
	//신고한 내용
	private String alarm;
	//신고한 사람
	private int mem_num;
	//신고당한사람
	private int reported_memnum;
	
	
	public int getAlarm_num() {
		return alarm_num;
	}
	public void setAlarm_num(int alarm_num) {
		this.alarm_num = alarm_num;
	}
	public int getBoard_type() {
		return board_type;
	}
	public void setBoard_type(int board_type) {
		this.board_type = board_type;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getAlarm() {
		return alarm;
	}
	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	public int getReported_memnum() {
		return reported_memnum;
	}
	public void setReported_memnum(int reported_memnum) {
		this.reported_memnum = reported_memnum;
	}
	@Override
	public String toString() {
		return "AlarmReportVO [alarm_num=" + alarm_num + ", board_type=" + board_type + ", board_num=" + board_num
				+ ", alarm=" + alarm + ", mem_num=" + mem_num + ", reported_memnum=" + reported_memnum + "]";
	}
	
}
