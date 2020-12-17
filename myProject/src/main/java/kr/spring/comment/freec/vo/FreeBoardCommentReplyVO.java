package kr.spring.comment.freec.vo;

import java.sql.Date;

public class FreeBoardCommentReplyVO {
	
	private int rfreec_num;
	private String rfreec_comment;
	private Date rfreec_date;
	private int freec_num;
	private int mem_num;
	private String mem_id;
	private int mem_auth;
	
	private String str_date;

	public int getRfreec_num() {
		return rfreec_num;
	}

	public void setRfreec_num(int rfreec_num) {
		this.rfreec_num = rfreec_num;
	}

	public String getRfreec_comment() {
		return rfreec_comment;
	}

	public void setRfreec_comment(String rfreec_comment) {
		this.rfreec_comment = rfreec_comment;
	}

	public Date getRfreec_date() {
		return rfreec_date;
	}

	public void setRfreec_date(Date rfreec_date) {
		this.rfreec_date = rfreec_date;
	}

	public int getFreec_num() {
		return freec_num;
	}

	public void setFreec_num(int freec_num) {
		this.freec_num = freec_num;
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
		return "FreeBoardCommentReplyVO [rfreec_num=" + rfreec_num + ", rfreec_comment=" + rfreec_comment
				+ ", rfreec_date=" + rfreec_date + ", freec_num=" + freec_num + ", mem_num=" + mem_num + ", mem_id="
				+ mem_id + ", mem_auth=" + mem_auth + ", str_date=" + str_date + "]";
	}

	
}
