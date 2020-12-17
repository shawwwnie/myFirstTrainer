package kr.spring.comment.tranqnac.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

public class TranQnABoardCommentVO {

	@NotEmpty
	private int tqc_num;
	@NotEmpty
	private String tq_comment;
	private Date tqc_reg_date;
	private Date tpc_modify_date;
	private int tq_num;
	private int mem_num;
	
	//테이블에는 없으나 sql문으로 데이터 삽입
	private String mem_id;
	private int mem_auth;
	//댓글의 댓글 수
	private int countComment;
	//날짜 string으로 표기
	private String str_date;
	public int getTqc_num() {
		return tqc_num;
	}
	public void setTqc_num(int tqc_num) {
		this.tqc_num = tqc_num;
	}
	public String getTq_comment() {
		return tq_comment;
	}
	public void setTq_comment(String tq_comment) {
		this.tq_comment = tq_comment;
	}
	public Date getTqc_reg_date() {
		return tqc_reg_date;
	}
	public void setTqc_reg_date(Date tqc_reg_date) {
		this.tqc_reg_date = tqc_reg_date;
	}
	public Date getTpc_modify_date() {
		return tpc_modify_date;
	}
	public void setTpc_modify_date(Date tpc_modify_date) {
		this.tpc_modify_date = tpc_modify_date;
	}
	public int getTq_num() {
		return tq_num;
	}
	public void setTq_num(int tq_num) {
		this.tq_num = tq_num;
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
	public String getStr_date() {
		return str_date;
	}
	public void setStr_date(String str_date) {
		this.str_date = str_date;
	}
	
	@Override
	public String toString() {
		return "TranQnABoardCommentVO [tqc_num=" + tqc_num + ", tq_comment=" + tq_comment + ", tqc_reg_date="
				+ tqc_reg_date + ", tpc_modify_date=" + tpc_modify_date + ", tq_num=" + tq_num + ", mem_num=" + mem_num
				+ ", mem_id=" + mem_id + ", mem_auth=" + mem_auth + ", countComment=" + countComment + ", str_date="
				+ str_date + "]";
	}
	
}
