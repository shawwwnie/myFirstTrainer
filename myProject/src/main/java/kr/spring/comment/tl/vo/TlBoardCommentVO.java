package kr.spring.comment.tl.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

public class TlBoardCommentVO {
	
	private int tlc_num;
	@NotEmpty
    private String tl_comment;
    private Date tlc_reg_date;
    private Date  tlc_modify_date;
    private String mem_id;
    private int tl_mem_num;
    private int writer_mem_num;
    String str_date;
    
    
	public String getStr_date() {
		return str_date;
	}
	public void setStr_date(String str_date) {
		this.str_date = str_date;
	}
	public int getTlc_num() {
		return tlc_num;
	}
	public void setTlc_num(int tlc_num) {
		this.tlc_num = tlc_num;
	}
	public String getTl_comment() {
		return tl_comment;
	}
	public void setTl_comment(String tl_comment) {
		this.tl_comment = tl_comment;
	}
	public Date getTlc_reg_date() {
		return tlc_reg_date;
	}
	public void setTlc_reg_date(Date tlc_reg_date) {
		this.tlc_reg_date = tlc_reg_date;
	}
	public Date getTlc_modify_date() {
		return tlc_modify_date;
	}
	public void setTlc_modify_date(Date tlc_modify_date) {
		this.tlc_modify_date = tlc_modify_date;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getTl_mem_num() {
		return tl_mem_num;
	}
	public void setTl_mem_num(int tl_mem_num) {
		this.tl_mem_num = tl_mem_num;
	}
	public int getWriter_mem_num() {
		return writer_mem_num;
	}
	public void setWriter_mem_num(int writer_mem_num) {
		this.writer_mem_num = writer_mem_num;
	}
	@Override
	public String toString() {
		return "TlBoardCommentVO [tlc_num=" + tlc_num + ", tl_comment=" + tl_comment + ", tlc_reg_date=" + tlc_reg_date
				+ ", tlc_modify_date=" + tlc_modify_date + ", mem_id=" + mem_id + ", tl_mem_num=" + tl_mem_num
				+ ", writer_mem_num=" + writer_mem_num + ", str_date=" + str_date + "]";
	}
	
	
    
    

}
