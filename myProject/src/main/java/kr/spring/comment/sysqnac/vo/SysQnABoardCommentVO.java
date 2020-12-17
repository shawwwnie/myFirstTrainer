package kr.spring.comment.sysqnac.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

public class SysQnABoardCommentVO {
	
	private int sqc_num;
	@NotEmpty
	private String sq_comment;
	private Date sqc_reg_date;
	private Date sqc_modify_date;
	private int sq_num;
	private int mem_num;
	private String mem_id;
	private int mem_auth;
	
	private String str_date;
	
	public int getSqc_num() {
		return sqc_num;
	}
	public void setSqc_num(int sqc_num) {
		this.sqc_num = sqc_num;
	}
	public String getSq_comment() {
		return sq_comment;
	}
	public void setSq_comment(String sq_comment) {
		this.sq_comment = sq_comment;
	}
	public Date getSqc_reg_date() {
		return sqc_reg_date;
	}
	public void setSqc_reg_date(Date sqc_reg_date) {
		this.sqc_reg_date = sqc_reg_date;
	}
	public Date getSqc_modify_date() {
		return sqc_modify_date;
	}
	public void setSqc_modify_date(Date sqc_modify_date) {
		this.sqc_modify_date = sqc_modify_date;
	}
	public int getSq_num() {
		return sq_num;
	}
	public void setSq_num(int sq_num) {
		this.sq_num = sq_num;
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
		return "SysQnABoardCommentVO [sqc_num=" + sqc_num + ", sq_comment=" + sq_comment + ", sqc_reg_date="
				+ sqc_reg_date + ", sqc_modify_date=" + sqc_modify_date + ", sq_num=" + sq_num + ", mem_num=" + mem_num
				+ ", mem_id=" + mem_id + ", mem_auth=" + mem_auth + "]";
	}
	
}
