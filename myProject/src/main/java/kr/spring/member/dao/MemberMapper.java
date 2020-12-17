package kr.spring.member.dao;



import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

public interface MemberMapper {

	//회원번호 정할 시퀀스
	@Select("SELECT member_seq.nextval FROM dual")
	public int selectMem_num(); 

	//member테이블에 일반회원 정보 입력
	@Insert("INSERT INTO member(mem_num,mem_id,mem_auth) VALUES (#{mem_num},#{mem_id},1)")
	public void insertMember(MemberVO member); 

	//member테이블에 트레이너 정보 입력
	@Insert("INSERT INTO member(mem_num,mem_id,mem_auth) VALUES (#{mem_num},#{mem_id},2)")
	public void insertTrainer(MemberVO member); 

	//일반회원 정보 입력
	@Insert("INSERT INTO member_detail (mem_num, mem_gender, mem_name, mem_pw, mem_cell, mem_email, mem_zipcode, mem_addr1, mem_addr2)"
			+ "VALUES (#{mem_num}, #{mem_gender}, #{mem_name}, #{mem_pw}, #{mem_cell}, #{mem_email}, #{mem_zipcode}, #{mem_addr1}, #{mem_addr2})")
	public void insertMember_detail(MemberVO member);

	//트레이너 정보 입력
	@Insert("INSERT INTO trainer_detail (mem_num, g_addr, career, mem_gender, exp, mem_name, mem_pw, mem_cell, mem_email, mem_zipcode, mem_addr1, mem_addr2)"+
			"VALUES (#{mem_num}, #{g_addr}, #{career}, #{mem_gender}, #{exp}, #{mem_name}, #{mem_pw}, #{mem_cell}, #{mem_email}, #{mem_zipcode}, #{mem_addr1}, #{mem_addr2})")
	public void insertTrainer_detail(MemberVO member);


	//id찾기
	//전화번호와 이메일정보가 같아야만 id 찾을 수 있음
	@Select("SELECT m.mem_id,m.mem_auth,d.mem_cell,d.mem_email FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num=d.mem_num WHERE d.mem_cell=#{mem_cell} AND d.mem_email=#{mem_email}")
	public MemberVO selectFindIdMember_detail(@Param("mem_cell")String cell, @Param("mem_email")String email);
	@Select("SELECT m.mem_id,m.mem_auth,d.mem_cell,d.mem_email FROM member m LEFT OUTER JOIN trainer_detail d ON m.mem_num=d.mem_num WHERE d.mem_cell=#{mem_cell} AND d.mem_email=#{mem_email}")
	public MemberVO selectFindIdTrainer_detail(@Param("mem_cell")String cell, @Param("mem_email")String email);

	//pw찾기
	//id와 전화번호가 같아야지만 pw를 찾을 수 있음
	@Select("SELECT m.mem_id,m.mem_auth,d.mem_cell,d.mem_pw FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num=d.mem_num WHERE d.mem_cell=#{mem_cell} AND m.mem_id=#{mem_id}")
	public MemberVO selectFindPwMember_detail(@Param("mem_id")String id, @Param("mem_cell")String cell);
	@Select("SELECT m.mem_id,m.mem_auth,d.mem_cell,d.mem_pw FROM member m LEFT OUTER JOIN trainer_detail d ON m.mem_num=d.mem_num WHERE d.mem_cell=#{mem_cell} AND m.mem_id=#{mem_id}")
	public MemberVO selectFindPwTrainer_detail(@Param("mem_id")String id, @Param("mem_cell")String cell);

	//로그인 처리와 아이디 중복체크 확인에도 쓰임
	@Select("SELECT m.mem_num,m.mem_id,m.mem_auth,d.t_num,d.mem_gender,d.mem_name,d.mem_pw,d.mem_cell,d.mem_email,d.mem_pic,d.mem_zipcode,d.mem_addr1,d.mem_addr2,d.mem_intro,d.mem_date FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.mem_id=#{mem_id}")
	public MemberVO selectCheckMember_detail(String id);
	@Select("SELECT m.mem_num,m.mem_id,m.mem_auth,d.g_addr,d.career,d.mem_gender,d.exp,d.mem_name,d.mem_pw,d.mem_cell,d.mem_email,d.mem_pic,d.mem_zipcode,d.mem_addr1,d.mem_addr2,d.mem_intro,d.mem_date FROM member m LEFT OUTER JOIN trainer_detail d ON m.mem_num=d.mem_num WHERE m.mem_id=#{mem_id}")
	public MemberVO selectCheckTrainer_detail(String id);


	//비밀번호 변경
	@Update("UPDATE member_detail SET mem_pw=#{mem_pw} WHERE mem_num=#{mem_num}")
	public void updateMemberPassword(MemberVO member);
	@Update("UPDATE trainer_detail SET mem_pw=#{mem_pw} WHERE mem_num=#{mem_num}")
	public void updateTrainerPassword(MemberVO member);

	//나머지 개인정보 변경
	@Update("UPDATE member_detail SET mem_cell=#{mem_cell},mem_email=#{mem_email},mem_zipcode=#{mem_zipcode},mem_addr1=#{mem_addr1},mem_addr2=#{mem_addr2} WHERE mem_num=#{mem_num}")
	public void updateMember_detail(MemberVO member);
	@Update("UPDATE trainer_detail SET g_addr=#{g_addr},career=#{career},exp=#{exp},mem_cell=#{mem_cell},mem_email=#{mem_email},mem_zipcode=#{mem_zipcode},mem_addr1=#{mem_addr1},mem_addr2=#{mem_addr2} WHERE mem_num=#{mem_num}")
	public void updateTrainer_detail(MemberVO member);

	//개인정보 마이페이지에 불러오기
	@Select("SELECT m.mem_id,d.t_num,d.mem_gender,d.mem_name,d.mem_pw,d.mem_cell,d.mem_email,d.mem_pic,d.mem_picname,d.mem_zipcode,d.mem_addr1,d.mem_addr2,d.mem_intro,d.mem_date FROM member m LEFT OUTER JOIN member_detail d ON m.mem_num=d.mem_num WHERE m.mem_num=#{mem_num}")
	public MemberVO selectMember_detail(Integer mem_num);
	@Select("SELECT m.mem_id,d.g_addr,d.career,d.mem_gender,d.exp,d.mem_name,d.mem_pw,d.mem_cell,d.mem_email,d.mem_pic,d.mem_picname,d.mem_zipcode,d.mem_addr1,d.mem_addr2,d.mem_intro,d.mem_date FROM member m LEFT OUTER JOIN trainer_detail d ON m.mem_num=d.mem_num WHERE m.mem_num=#{mem_num}")
	public MemberVO selectTrainer_detail(Integer mem_num);

	//회원탈퇴시 auth값 변경
	@Update("UPDATE member SET mem_auth=3 WHERE mem_num=#{mem_num}")
	public void deleteMember(Integer mem_num);

	//회원탈퇴시 탈퇴회원 삭제
	@Delete("DELETE FROM member_detail WHERE mem_num=#{mem_num}")
	public void deleteMember_detail(int mem_num); 
	@Delete("DELETE FROM trainer_detail WHERE mem_num=#{mem_num}")
	public void deleteTrainer_detail(int mem_num); 

	//아직 안씀
	//프로필 사진 변경
	@Update("UPDATE member_detail SET mem_pic=#{mem_pic} WHERE mem_num=#{mem_num}")
	public void updateMemberPhoto(MemberVO member);
	@Update("UPDATE trainer_detail SET mem_pic=#{mem_pic} WHERE mem_num=#{mem_num}")
	public void updateTrainerPhoto(MemberVO member);
	
	//=================================김다정
	//로그인시 알림 온 개수 불러오기
	@Select("SELECT COUNT(*) FROM notice WHERE writer_memnum=#{mem_num}")
	public int selectNoticeCount(int mem_num);


}
