package kr.spring.board.tip.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

public class TipBoardVO {
	
	@NotEmpty
	private int free_num;
	@NotEmpty
	private String free_title;
	@NotEmpty
	private String free_content;
	@NotEmpty
	private int free_hit;
	@NotEmpty
	private Date free_reg_date;
	@NotEmpty
	private Date free_modify_date;
	
	private byte[] free_file;
	private String free_filename;
	
	@NotEmpty
	private String free_ip;
	@NotEmpty
	private int free_auth;
	@NotEmpty
	private int mem_num;
	
	
	public int getFree_num() {
		return free_num;
	}
	public void setFree_num(int free_num) {
		this.free_num = free_num;
	}
	public String getFree_title() {
		return free_title;
	}
	public void setFree_title(String free_title) {
		this.free_title = free_title;
	}
	public String getFree_content() {
		return free_content;
	}
	public void setFree_content(String free_content) {
		this.free_content = free_content;
	}
	public int getFree_hit() {
		return free_hit;
	}
	public void setFree_hit(int free_hit) {
		this.free_hit = free_hit;
	}
	public Date getFree_reg_date() {
		return free_reg_date;
	}
	public void setFree_reg_date(Date free_reg_date) {
		this.free_reg_date = free_reg_date;
	}
	public Date getFree_modify_date() {
		return free_modify_date;
	}
	public void setFree_modify_date(Date free_modify_date) {
		this.free_modify_date = free_modify_date;
	}
	public byte[] getFree_file() {
		return free_file;
	}
	public void setFree_file(byte[] free_file) {
		this.free_file = free_file;
	}
	public String getFree_filename() {
		return free_filename;
	}
	public void setFree_filename(String free_filename) {
		this.free_filename = free_filename;
	}
	public String getFree_ip() {
		return free_ip;
	}
	public void setFree_ip(String free_ip) {
		this.free_ip = free_ip;
	}
	public int getFree_auth() {
		return free_auth;
	}
	public void setFree_auth(int free_auth) {
		this.free_auth = free_auth;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
}
