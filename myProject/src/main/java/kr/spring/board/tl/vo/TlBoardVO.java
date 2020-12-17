package kr.spring.board.tl.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class TlBoardVO {
	/*-------------------------------------------------------------*/
	/*member 테이블*/
	private int mem_num;// 회원번호
	@Pattern(regexp="^[A-Za-z0-9+]{4,10}$")//정규표현식을 이용해서 유효성 체크
	private String mem_id;// 회원 아이디
	private int mem_auth;// 회원 등급
	/*-------------------------------------------------------------*/

	/*-------------------------------------------------------------*/
	/*trainer_detail 테이블*/
	private String g_addr;// 트레이너 회원의 근무지 주소(간단)
	private String career;// 트레이너 회원의 경력
	@NotEmpty
	private String mem_gender;	// 회원의 성별
	private String exp;// 트레이너 회원의 이력(경력제외)
	@NotEmpty
	private String mem_name;//회원의 이름
	@Size(min=4,max=15)
	private String mem_pw;//회원의 비밀번호
	@NotEmpty
	private String mem_cell;//회원의 전화번호
	@NotEmpty
	private String mem_email;//회원의 이메일
	private byte[] mem_pic;//회원의 프로필 사진
	private String mem_zipcode;//회원의 우편번호
	private String mem_addr1;//회원의 주소
	private String mem_addr2;//회원의 상세주소
	private String mem_intro;//회원의 자기소개
	private Date mem_date;//회원의 가입일
	private String mem_picName;//회원의 프로필 사진 파일명
	/*-------------------------------------------------------------*/
	
	/*-------------------------------------------------------------*/
	/*member_detail 테이블 중 trainer_detail과 속성이 다른 것만*/
	private int t_num;
	/*-------------------------------------------------------------*/
		
	/*-------------------------------------------------------------*/
	/*matching 테이블 중 mem_num은 이미 있어서 안씀*/
	private int mat_num;
	private String mat_id;
	private int mat_from;
	private int mat_to;
	/*-------------------------------------------------------------*/

	//프로필 사진 처리를 위한 이미지 BLOB 처리
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFIle -> byte[]
		setMem_pic(upload.getBytes());
		setMem_picName(upload.getOriginalFilename());
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
	public String getG_addr() {
		return g_addr;
	}
	public void setG_addr(String g_addr) {
		this.g_addr = g_addr;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getMem_gender() {
		return mem_gender;
	}
	public void setMem_gender(String mem_gender) {
		this.mem_gender = mem_gender;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_pw() {
		return mem_pw;
	}
	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}
	public String getMem_cell() {
		return mem_cell;
	}
	public void setMem_cell(String mem_cell) {
		this.mem_cell = mem_cell;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public byte[] getMem_pic() {
		return mem_pic;
	}
	public void setMem_pic(byte[] mem_pic) {
		this.mem_pic = mem_pic;
	}
	public String getMem_zipcode() {
		return mem_zipcode;
	}
	public void setMem_zipcode(String mem_zipcode) {
		this.mem_zipcode = mem_zipcode;
	}
	public String getMem_addr1() {
		return mem_addr1;
	}
	public void setMem_addr1(String mem_addr1) {
		this.mem_addr1 = mem_addr1;
	}
	public String getMem_addr2() {
		return mem_addr2;
	}
	public void setMem_addr2(String mem_addr2) {
		this.mem_addr2 = mem_addr2;
	}
	public String getMem_intro() {
		return mem_intro;
	}
	public void setMem_intro(String mem_intro) {
		this.mem_intro = mem_intro;
	}
	public Date getMem_date() {
		return mem_date;
	}
	public void setMem_date(Date mem_date) {
		this.mem_date = mem_date;
	}
	public String getMem_picName() {
		return mem_picName;
	}
	public void setMem_picName(String mem_picName) {
		this.mem_picName = mem_picName;
	}

	public int getMat_num() {
		return mat_num;
	}

	public void setMat_num(int mat_num) {
		this.mat_num = mat_num;
	}

	public int getMat_from() {
		return mat_from;
	}

	public void setMat_from(int mat_from) {
		this.mat_from = mat_from;
	}

	public int getMat_to() {
		return mat_to;
	}

	public void setMat_to(int mat_to) {
		this.mat_to = mat_to;
	}
	
	public String getMat_id() {
		return mat_id;
	}

	public void setMat_id(String mat_id) {
		this.mat_id = mat_id;
	}

	public int getT_num() {
		return t_num;
	}

	public void setT_num(int t_num) {
		this.t_num = t_num;
	}

	@Override
	public String toString() {
		return "TlBoardVO [mem_num=" + mem_num + ", mem_id=" + mem_id + ", mem_auth=" + mem_auth + ", g_addr=" + g_addr
				+ ", career=" + career + ", mem_gender=" + mem_gender + ", exp=" + exp + ", mem_name=" + mem_name
				+ ", mem_pw=" + mem_pw + ", mem_cell=" + mem_cell + ", mem_email=" + mem_email + ", mem_zipcode="
				+ mem_zipcode + ", mem_addr1=" + mem_addr1 + ", mem_addr2=" + mem_addr2 + ", mem_intro=" + mem_intro
				+ ", mem_date=" + mem_date + ", mem_picName=" + mem_picName + ", t_num=" + t_num + ", mat_num="
				+ mat_num + ", mat_id=" + mat_id + ", mat_from=" + mat_from + ", mat_to=" + mat_to + "]";
	}

	
}
