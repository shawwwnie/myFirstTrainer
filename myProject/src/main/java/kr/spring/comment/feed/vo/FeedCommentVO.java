package kr.spring.comment.feed.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

public class FeedCommentVO {
	private int feedc_num;
	@NotEmpty
	private String feedc_comment;
	private Date feedc_reg_date;
	private Date feedc_modify_date;
	private int feed_num;
	private int mem_num;
	private String mem_id;
	private int mem_auth;
	
	private int countComment;
	private String str_date;
	
	public String getStr_date() {
		return str_date;
	}
	public void setStr_date(String str_date) {
		this.str_date = str_date;
	}
	public int getCountComment() {
		return countComment;
	}
	public void setCountComment(int countComment) {
		this.countComment = countComment;
	}
	public int getFeedc_num() {
		return feedc_num;
	}
	public void setFeedc_num(int feedc_num) {
		this.feedc_num = feedc_num;
	}
	public String getFeedc_comment() {
		return feedc_comment;
	}
	public void setFeedc_comment(String feedc_comment) {
		this.feedc_comment = feedc_comment;
	}
	public Date getFeedc_reg_date() {
		return feedc_reg_date;
	}
	public void setFeedc_reg_date(Date feedc_reg_date) {
		this.feedc_reg_date = feedc_reg_date;
	}
	public Date getFeedc_modify_date() {
		return feedc_modify_date;
	}
	public void setFeedc_modify_date(Date feedc_modify_date) {
		this.feedc_modify_date = feedc_modify_date;
	}
	public int getFeed_num() {
		return feed_num;
	}
	public void setFeed_num(int feed_num) {
		this.feed_num = feed_num;
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
}
