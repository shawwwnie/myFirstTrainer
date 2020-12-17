package kr.spring.comment.tranqnac.vo;

import java.sql.Date;

public class TranQnABoardCommentReplyVO {
	
	private int rtqc_num;
	private String rtqc_comment;
	private Date rtqc_date;
	private int tqc_num;
	private int mem_num;
	
	private String mem_id;
	private int mem_auth;
	
	private String str_date;

	public int getRtqc_num() {
		return rtqc_num;
	}

	public void setRtqc_num(int rtqc_num) {
		this.rtqc_num = rtqc_num;
	}

	public String getRtqc_comment() {
		return rtqc_comment;
	}

	public void setRtqc_comment(String rtqc_comment) {
		this.rtqc_comment = rtqc_comment;
	}

	public Date getRtqc_date() {
		return rtqc_date;
	}

	public void setRtqc_date(Date rtqc_date) {
		this.rtqc_date = rtqc_date;
	}

	public int getTqc_num() {
		return tqc_num;
	}

	public void setTqc_num(int tqc_num) {
		this.tqc_num = tqc_num;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getMem_auth() {
		return mem_auth;
	}

	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}

	public String getStr_date() {
		return str_date;
	}

	public void setStr_date(String str_date) {
		this.str_date = str_date;
	}

	@Override
	public String toString() {
		return "TranQnABoardCommentReplyVO [rtqc_num=" + rtqc_num + ", rtqc_comment=" + rtqc_comment + ", rtqc_date="
				+ rtqc_date + ", tqp_num=" + tqc_num + ", mem_num=" + mem_num + ", mem_id=" + mem_id + ", mem_auth="
				+ mem_auth + ", str_date=" + str_date + "]";
	}

	

}
