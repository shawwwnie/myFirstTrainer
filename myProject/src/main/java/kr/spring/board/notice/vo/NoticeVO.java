package kr.spring.board.notice.vo;

import java.sql.Date;

public class NoticeVO {
	
	//알림의 고유 번호(notice_num_seq)
	private int notice_num;
	//알림을 보내는 기준(댓글이 달리는 게시판 번호 또는 첫번째 댓글에 두번째 댓글을 달때 첫번쨰 댓글번호)
	private int board_num;
	//알림 받는 사람의 멤버번호
	private int writer_memnum;
	//댓글을 단 사람(알림주게되는 사람)의 아이디
	private int reply_mem_num;
	//댓글이 달린 사람이 썼던 게시판 글
	private String writer_board;
	//댓글 내용
	private String board_comment;
	//등록한 날짜(default sysdate)
	private Date reg_date;
	//알림주는 내용(예시:자유게시판 글에 댓글을 달았습니다.)
	private String notice_comment;
	//이동 눌렀을때 이동하는 화면
	private String return_url;
	//앎림을 받은 사람이 확인을 누르면 그날자로 업데이트 됨(처음에는 널값으로 입력)
	private Date check_date;

	//댓글을 단 사람의 아이디
	private String mem_id;
	
	//알림 받는 사람의 아이디
	private String writer_memid;
	
	
	public String getWriter_memid() {
		return writer_memid;
	}
	public void setWriter_memid(String writer_memid) {
		this.writer_memid = writer_memid;
	}
	public int getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public int getWriter_memnum() {
		return writer_memnum;
	}
	public void setWriter_memnum(int writer_memnum) {
		this.writer_memnum = writer_memnum;
	}
	public int getReply_mem_num() {
		return reply_mem_num;
	}
	public void setReply_mem_num(int reply_mem_num) {
		this.reply_mem_num = reply_mem_num;
	}
	public String getBoard_comment() {
		return board_comment;
	}
	public void setBoard_comment(String board_comment) {
		this.board_comment = board_comment;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getNotice_comment() {
		return notice_comment;
	}
	public void setNotice_comment(String notice_comment) {
		this.notice_comment = notice_comment;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
	public Date getCheck_date() {
		return check_date;
	}
	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	public String getWriter_board() {
		return writer_board;
	}
	public void setWriter_board(String writer_board) {
		this.writer_board = writer_board;
	}
	
	@Override
	public String toString() {
		return "NoticeVO [notice_num=" + notice_num + ", board_num=" + board_num + ", writer_memnum=" + writer_memnum
				+ ", reply_mem_num=" + reply_mem_num + ", board_comment=" + board_comment + ", reg_date=" + reg_date
				+ ", notice_comment=" + notice_comment + ", return_url=" + return_url + ", check_date=" + check_date
				+ ", reply_mem_id=" + mem_id + "]";
	}	

}
