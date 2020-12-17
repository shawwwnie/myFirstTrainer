package kr.spring.board.tranqna.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class TranQnABoardVO {
	
	private int tq_num;
	
	@NotEmpty
	private String tq_title;
	@NotEmpty
	private String tq_content;

	private int tq_hit;
	private Date tq_reg_date;
	private Date tq_modify_date;
	private byte[] tq_file;
	private String tq_filename;
	private String tq_ip;
	
	//0은 전체공개, 1은 트레이너에게만공개
	private int tq_type;
	
	private int mem_num;
	private String mem_id;
	private int mem_auth;
	
	//이미지도 같이 수정시 0, 이미지빼고 수정시 1
	private int isImgUpdate;
	
	//댓글 수
	private int commentNum;

	
	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public void setUpload(MultipartFile upload) throws IOException{
		if(upload == null) return;
		setTq_file(upload.getBytes());
		setTq_filename(upload.getOriginalFilename());
	}
	
	public int getTq_num() {
		return tq_num;
	}

	public void setTq_num(int tq_num) {
		this.tq_num = tq_num;
	}

	public String getTq_title() {
		return tq_title;
	}

	public void setTq_title(String tq_title) {
		this.tq_title = tq_title;
	}

	public String getTq_content() {
		return tq_content;
	}

	public void setTq_content(String tq_content) {
		this.tq_content = tq_content;
	}

	public int getTq_hit() {
		return tq_hit;
	}

	public void setTq_hit(int tq_hit) {
		this.tq_hit = tq_hit;
	}

	public Date getTq_reg_date() {
		return tq_reg_date;
	}

	public void setTq_reg_date(Date tq_reg_date) {
		this.tq_reg_date = tq_reg_date;
	}

	public Date getTq_modify_date() {
		return tq_modify_date;
	}

	public void setTq_modify_date(Date tq_modify_date) {
		this.tq_modify_date = tq_modify_date;
	}

	public byte[] getTq_file() {
		return tq_file;
	}

	public void setTq_file(byte[] tq_file) {
		this.tq_file = tq_file;
	}

	public String getTq_filename() {
		return tq_filename;
	}

	public void setTq_filename(String tq_filename) {
		this.tq_filename = tq_filename;
	}

	public String getTq_ip() {
		return tq_ip;
	}

	public void setTq_ip(String tq_ip) {
		this.tq_ip = tq_ip;
	}

	public int getTq_type() {
		return tq_type;
	}

	public void setTq_type(int tq_type) {
		this.tq_type = tq_type;
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

	public int getIsImgUpdate() {
		return isImgUpdate;
	}

	public void setIsImgUpdate(int isImgUpdate) {
		this.isImgUpdate = isImgUpdate;
	}

	@Override
	public String toString() {
		return "TranQnAVO [tq_num=" + tq_num + ", tq_title=" + tq_title + ", tq_content=" + tq_content + ", tq_hit="
				+ tq_hit + ", tq_reg_date=" + tq_reg_date + ", tq_modify_date=" + tq_modify_date + ", tq_filename="
				+ tq_filename + ", tq_ip=" + tq_ip + ", tq_type=" + tq_type + ", mem_num=" + mem_num + ", mem_id="
				+ mem_id + ", mem_auth=" + mem_auth + ", isImgUpdate=" + isImgUpdate + "]";
	}
	
}
