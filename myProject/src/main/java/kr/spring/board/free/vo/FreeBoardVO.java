package kr.spring.board.free.vo;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import kr.spring.comment.freec.vo.FreeBoardCommentVO;

public class FreeBoardVO {	

	private int free_num;
	
	@NotEmpty
	private String free_title;
	@NotEmpty
	private String free_content;

	private int free_hit;
	private Date free_reg_date;
	private Date free_modify_date;
	private byte[] free_file;
	private String free_filename;
	private String free_ip;
	//0은 자유게시판, 1은 팁게시판
	private int free_type;
	private int mem_num;
	private String mem_id;
	private int mem_auth;
	//이미지도 같이 수정시 0, 이미지빼고 수정시 1
	private int isImgUpdate;
	
	private int commentNum;
	
	private List<FreeBoardCommentVO> commentList;	
	
	//신고한 수
	private int alarm;
	
	public void setUpload(MultipartFile upload) throws IOException{
		if(upload == null) return;
		setFree_file(upload.getBytes());
		setFree_filename(upload.getOriginalFilename());
	}
	
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
	public int getFree_type() {
		return free_type;
	}
	public void setFree_type(int free_type) {
		this.free_type = free_type;
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
	

	public List<FreeBoardCommentVO> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<FreeBoardCommentVO> commentList) {
		this.commentList = commentList;
	}
	
	public int getIsImgUpdate() {
		return isImgUpdate;
	}

	public void setIsImgUpdate(int isImgUpdate) {
		this.isImgUpdate = isImgUpdate;
	}
	
	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	
	public int getAlarm() {
		return alarm;
	}

	public void setAlarm(int alarm) {
		this.alarm = alarm;
	}

	@Override
	public String toString() {
		return "FreeBoardVO [free_num=" + free_num + ", free_title=" + free_title + ", free_content=" + free_content
				+ ", free_hit=" + free_hit + ", free_reg_date=" + free_reg_date + ", free_modify_date="
				+ free_modify_date + ", free_filename=" + free_filename + ", free_ip=" + free_ip + ", free_type="
				+ free_type + ", mem_num=" + mem_num + "]";
	}
		
}
