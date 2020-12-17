package kr.spring.comment.freec.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

public class FreeBoardCommentVO {
	
	private int freec_num;
	@NotEmpty
	private String free_comment;
	private Date freec_reg_date;
	private Date freec_modify_date;
	private int free_num;
	private int mem_num;
	private String mem_id;
	private int mem_auth;
	//댓글의 댓글 수
	private int countComment;
	
	private String str_date;
		
	
	public String getStr_date() {
		return str_date;
	}
	public void setStr_date(String str_date) {
		this.str_date = str_date;
	}
	public int getFreec_num() {
		return freec_num;
	}
	public void setFreec_num(int freec_num) {
		this.freec_num = freec_num;
	}
	public String getFree_comment() {
		return free_comment;
	}
	public void setFree_comment(String free_comment) {
		this.free_comment = free_comment;
	}
	public Date getFreec_reg_date() {
		return freec_reg_date;
	}
	public void setFreec_reg_date(Date freec_reg_date) {
		this.freec_reg_date = freec_reg_date;
	}
	public Date getFreec_modify_date() {
		return freec_modify_date;
	}
	public void setFreec_modify_date(Date freec_modify_date) {
		this.freec_modify_date = freec_modify_date;
	}
	public int getFree_num() {
		return free_num;
	}
	public void setFree_num(int free_num) {
		this.free_num = free_num;
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
	public int getCountComment() {
		return countComment;
	}
	public void setCountComment(int countComment) {
		this.countComment = countComment;
	}
	@Override
	public String toString() {
		return "FreeBoardCommentVO [freec_num=" + freec_num + ", free_comment=" + free_comment + ", freec_reg_date="
				+ freec_reg_date + ", freec_modify_date=" + freec_modify_date + ", free_num=" + free_num + ", mem_num="
				+ mem_num + ", mem_id=" + mem_id + ", mem_auth=" + mem_auth + ", cofcnumber=" + countComment + "]";
	}
	
	

}
