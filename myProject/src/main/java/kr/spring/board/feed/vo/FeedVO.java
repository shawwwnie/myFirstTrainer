package kr.spring.board.feed.vo;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class FeedVO {
	/*@@@@@@@@@@@@@@@@@feed 테이블@@@@@@@@@@@@@@@@@*/
	private int feed_num; //피드 글 번호 -> 시퀀스 생성
	private String mem_id;
	private int feed_type; //글 타입(1:식단 / 2:운동)
	private String feed_content; //글 내용
	private String feed_reg_date; //글 등록일
	private byte[] feed_file; // 업로드한 파일
	private String feed_filename; //업로드한 파일 명
	private String feed_ip; //ip주소
	private int mem_num; //업로드한 회원의 mem_num
	private int feed_auth; //글 공유범위 0:나만보기,1:트레이너만,2:팔로워만,3:전체공개
	/*@@@@@@@@@@@@@@@@@feed 테이블@@@@@@@@@@@@@@@@@*/
	
	/*@@@@@@@@@@@@@@@@@follower 테이블@@@@@@@@@@@@@@@@@*/
	private int follower_num; //팔로우 번호 ->시퀀스 생성
	private int follower_to; //팔로우 당하는 회원의 mem_num -> 로그인한 회원의 mem_num은 feed테이블에 필드 생성
	/*@@@@@@@@@@@@@@@@@follower 테이블@@@@@@@@@@@@@@@@@*/
	
	/*@@@@@@@@@@@@@@@@@training 테이블@@@@@@@@@@@@@@@@@*/
	private int training_num; //트레이닝 관계 번호 -> 시퀀스 생성
	private int training_to; //트레이닝 받는 회원의 mem_num -> 로그인한 회원의 mem_num은 feed테이블에 필드 생성
	/*@@@@@@@@@@@@@@@@@training 테이블@@@@@@@@@@@@@@@@@*/
	
	//이미지 업로드 파일 처리
	public void setFeedUpload(MultipartFile feedUpload)throws IOException{
		//MultipartFile -> byte[] 반환
		setFeed_file(feedUpload.getBytes());
		//파일명 구하기
		setFeed_filename(feedUpload.getOriginalFilename());
	}
	
	/*@@@@@@@@@@@@@@@@@ Getter&Setter @@@@@@@@@@@@@@@@@*/
	public int getFeed_num() {
		return feed_num;
	}

	public void setFeed_num(int feed_num) {
		this.feed_num = feed_num;
	}

	public int getFeed_type() {
		return feed_type;
	}

	public void setFeed_type(int feed_type) {
		this.feed_type = feed_type;
	}

	public String getFeed_content() {
		return feed_content;
	}

	public void setFeed_content(String feed_content) {
		this.feed_content = feed_content;
	}

	public String getFeed_reg_date() {
		return feed_reg_date;
	}

	public void setFeed_reg_date(String feed_reg_date) {
		this.feed_reg_date = feed_reg_date;
	}

	public byte[] getFeed_file() {
		return feed_file;
	}

	public void setFeed_file(byte[] feed_file) {
		this.feed_file = feed_file;
	}

	public String getFeed_filename() {
		return feed_filename;
	}

	public void setFeed_filename(String feed_filename) {
		this.feed_filename = feed_filename;
	}

	public String getFeed_ip() {
		return feed_ip;
	}

	public void setFeed_ip(String feed_ip) {
		this.feed_ip = feed_ip;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public int getFeed_auth() {
		return feed_auth;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public void setFeed_auth(int feed_auth) {
		this.feed_auth = feed_auth;
	}

	public int getFollower_num() {
		return follower_num;
	}

	public void setFollower_num(int follower_num) {
		this.follower_num = follower_num;
	}

	public int getFollower_to() {
		return follower_to;
	}

	public void setFollower_to(int follower_to) {
		this.follower_to = follower_to;
	}

	public int getTraining_num() {
		return training_num;
	}

	public void setTraining_num(int training_num) {
		this.training_num = training_num;
	}

	public int getTraining_to() {
		return training_to;
	}

	public void setTraining_to(int training_to) {
		this.training_to = training_to;
	}
	/*@@@@@@@@@@@@@@@@@ Getter&Setter @@@@@@@@@@@@@@@@@*/

	/*@@@@@@@@@@@@@@@@@ toString @@@@@@@@@@@@@@@@@*/
	@Override
	public String toString() {
		return "FeedVO [feed_num=" + feed_num + ", mem_id=" + mem_id + ", feed_type=" + feed_type + ", feed_content="
				+ feed_content + ", feed_reg_date=" + feed_reg_date + ", feed_filename=" + feed_filename + ", feed_ip="
				+ feed_ip + ", mem_num=" + mem_num + ", feed_auth=" + feed_auth + ", follower_num=" + follower_num
				+ ", follower_to=" + follower_to + ", training_num=" + training_num + ", training_to=" + training_to
				+ "]";
	}
	/*@@@@@@@@@@@@@@@@@ toString @@@@@@@@@@@@@@@@@*/
}
