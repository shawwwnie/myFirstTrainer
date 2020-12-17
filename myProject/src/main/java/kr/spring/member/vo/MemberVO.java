package kr.spring.member.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {

	// 회원번호
	private int mem_num;

	// 회원 등급
	private int mem_auth;

	// 회원 아이디
	//정규표현식을 이용해서 유효성 체크
	@Pattern(regexp="^[A-Za-z0-9+]{4,10}$")
	private String mem_id;

	// 일반회원의 트레이너의 회원번호
	private int t_num;

	// 트레이너 회원의 근무지 주소(간단)
	private String g_addr;

	// 트레이너 회원의 경력
	private String career;

	// 트레이너 회원의 이력(경력제외)
	private String exp;

	// 회원의 성별
	@NotEmpty
	private String mem_gender;

	//회원의 이름
	@NotEmpty
	private String mem_name;

	//회원의 비밀번호
	@Size(min=4,max=15)
	private String mem_pw;

	//회원의 전화번호
	@NotEmpty
	private String mem_cell;

	//회원의 이메일
	@NotEmpty
	private String mem_email;

	//회원의 프로필 사진
	private byte[] mem_pic;

	//회원의 우편번호
	private String mem_zipcode;

	//회원의 주소
	private String mem_addr1;

	//회원의 상세주소
	private String mem_addr2;

	//회원의 자기소개
	private String mem_intro;

	//회원의 가입일
	private Date mem_date;
	
	//프로필사진 파일 명
	private String mem_picName;


	//비밀번호 변경시 현재 비밀번호를 저장하는 용도로 사용
	@Size(min=4,max=10)
	private String now_passwd;
  
	//비밀번호 일치 여부 체크 db와 연결할 필요 없이 이미 정보를 불러와 셋팅한 MemberVO에서 처리
	public boolean isCheckedPasswd(String userPasswd){
		/*0관리자, 1일반회원, 2트레이너, 3탈퇴회원*/
		//탈퇴 회원이 아닌 회원들
		if(mem_auth < 3 && mem_pw.equals(userPasswd)) {
			return true;
		}else if(mem_pw.equals(null)) {
			return false;
		}
		return false;
	}

	//이미지 BLOB 처리
	//blob은 Binary Large Object의 약자 즉, 이름에서 바이너리 형태로 큰 객체를 저장할 것이라 생각하면 됨
	//mybatis가 byte타입으로 테이블에 저장해주기에 작성한 코드
	public void setUpload(MultipartFile upload) throws IOException{
		//MultipartFile -> byte[]
		setMem_pic(upload.getBytes());
		//파일 이름
		setMem_picName(upload.getOriginalFilename());
	}

	/******************************Getter&Setter*************************************/

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

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public int getT_num() {
		return t_num;
	}

	public void setT_num(int t_num) {
		this.t_num = t_num;
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

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getMem_gender() {
		return mem_gender;
	}

	public void setMem_gender(String mem_gender) {
		this.mem_gender = mem_gender;
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
	
	public String getNow_passwd() {
		return now_passwd;
	}

	public void setNow_passwd(String now_passwd) {
		this.now_passwd = now_passwd;
	}
	
	public String getMem_picName() {
		return mem_picName;
	}

	public void setMem_picName(String mem_picName) {
		this.mem_picName = mem_picName;
	}

	/******************************Getter&Setter*************************************/


	/******************************toString*************************************/

	@Override
	public String toString() {
		return "MemberVO [mem_num=" + mem_num + ", mem_auth=" + mem_auth + ", mem_id=" + mem_id + ", t_num=" + t_num
				+ ", g_addr=" + g_addr + ", career=" + career + ", exp=" + exp + ", mem_gender=" + mem_gender
				+ ", mem_name=" + mem_name + ", mem_pw=" + mem_pw + ", mem_cell=" + mem_cell + ", mem_email="
				+ mem_email + ", mem_zipcode=" + mem_zipcode + ", mem_addr1=" + mem_addr1 + ", mem_addr2=" + mem_addr2
				+ ", mem_intro=" + mem_intro + ", mem_date=" + mem_date + ", mem_picName=" + mem_picName
				+ ", now_passwd=" + now_passwd + "]";
	}

	/******************************toString*************************************/

}
