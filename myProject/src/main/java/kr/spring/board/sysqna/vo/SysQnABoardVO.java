package kr.spring.board.sysqna.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class SysQnABoardVO {

	private int sq_num;
	@NotEmpty
	private String sq_title;
	@NotEmpty
	private String sq_content;
	private int sq_hit;
	private Date sq_reg_date;
	private Date sq_modify_date;	
	private byte[] sq_file;
	private String sq_filename;
	private String sq_ip;
	private int mem_num;
	private int mem_auth;
	
	private String mem_id;
	//이미지도 같이 수정시 0, 이미지빼고 수정시 1
	private int isImgUpdate;
	
	//삭제한글을 제외하고 뷰에서 게시판 글 순서대로 나타나는 번호
	private int asc_rnum;
	
	//댓글 수
	private int commentNum;
		
	public int getAsc_rnum() {
		return asc_rnum;
	}

	public void setAsc_rnum(int asc_rnum) {
		this.asc_rnum = asc_rnum;
	}

	public int getIsImgUpdate() {
		return isImgUpdate;
	}

	public void setIsImgUpdate(int isImgUpdate) {
		this.isImgUpdate = isImgUpdate;
	}
	
	public void setUpload(MultipartFile upload) throws IOException{
		if(upload == null) return;
		setSq_file(upload.getBytes());
		setSq_filename(upload.getOriginalFilename());
	}
	
	public int getSq_num() {
		return sq_num;
	}	
	
	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public void setSq_num(int sq_num) {
		this.sq_num = sq_num;
	}
	public String getSq_title() {
		return sq_title;
	}
	public void setSq_title(String sq_title) {
		this.sq_title = sq_title;
	}
	public String getSq_content() {
		return sq_content;
	}
	public void setSq_content(String sq_content) {
		this.sq_content = sq_content;
	}
	public int getSq_hit() {
		return sq_hit;
	}
	public void setSq_hit(int sq_hit) {
		this.sq_hit = sq_hit;
	}
	public Date getSq_reg_date() {
		return sq_reg_date;
	}
	public void setSq_reg_date(Date sq_reg_date) {
		this.sq_reg_date = sq_reg_date;
	}
	public Date getSq_modify_date() {
		return sq_modify_date;
	}
	public void setSq_modify_date(Date sq_modify_date) {
		this.sq_modify_date = sq_modify_date;
	}
	public byte[] getSq_file() {
		return sq_file;
	}
	public void setSq_file(byte[] sq_file) {
		this.sq_file = sq_file;
	}
	public String getSq_filename() {
		return sq_filename;
	}
	public void setSq_filename(String sq_filename) {
		this.sq_filename = sq_filename;
	}
	public String getSq_ip() {
		return sq_ip;
	}
	public void setSq_ip(String sq_ip) {
		this.sq_ip = sq_ip;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getMem_auth() {
		return mem_auth;
	}

	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}

	
	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	@Override
	public String toString() {
		return "SysQnAVO [sq_num=" + sq_num + ", sq_title=" + sq_title + ", sq_content=" + sq_content + ", sq_hit="
				+ sq_hit + ", sq_reg_date=" + sq_reg_date + ", sq_modify_date=" + sq_modify_date + ", sq_filename="
				+ sq_filename + ", sq_ip=" + sq_ip + ", mem_num=" + mem_num + "commentNum=" + commentNum +"]";
	}
	
}
