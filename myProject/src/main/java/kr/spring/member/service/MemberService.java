package kr.spring.member.service;

import kr.spring.member.vo.MemberVO;

public interface MemberService {

	/*
	//일반회원 회원가입 정보 입력 관련 메서드 묶음
	public int selectMem_num(); //시퀀스 가져오고
	public void insertMember(MemberVO member); //공통 멤버테이블에 넣고 
	public void insertMember_detail(MemberVO member); //일반회원 회원정보 넣고
	 */
	public void insertMember_detail(MemberVO member);

	/*
	//트레이너 회원가입 정보 입력 관련 메서드 묶음
	public int selectMem_num(); //시퀀스 가져오고
	public void insertTrainer(MemberVO member); //공통 멤버테이블에 넣고 
	public void insertTrainer_detail(MemberVO member); //트레이너 회원정보 넣고 가 한 사이클
	 */
	public void insertTrainer_detail(MemberVO member);



	/*
	//일반회원 탈퇴관련 메서드 묶음
	//회원탈퇴시 auth값 변경
	//회원탈퇴시 탈퇴회원 삭제 
	public void deleteMember(Integer mem_num); //auth값 3(탈퇴회원)으로 변경
	public void deleteMember_detail(MemberVO member); //일반회원 탈퇴
	 */
	public void deleteMember_detail(Integer mem_num);

	/*	
 	//일반회원 탈퇴관련 메서드 묶음
	//회원탈퇴시 auth값 변경
	//회원탈퇴시 탈퇴회원 삭제 
 	public void deleteMember(Integer mem_num); //auth값 3(탈퇴회원)으로 변경
	public void deleteTrainer_detail(MemberVO member); //트레이너 탈퇴
	 */
	public void deleteTrainer_detail(Integer mem_num);

	//id찾기
	public MemberVO selectFindIdMember_detail(String cell, String email);
	public MemberVO selectFindIdTrainer_detail(String cell, String email);

	//pw찾기
	public MemberVO selectFindPwMember_detail(String id, String cell);
	public MemberVO selectFindPwTrainer_detail(String id, String cell);

	//로그인 처리와 아이디 중복체크 확인에도 쓰임
	public MemberVO selectCheckMember_detail(String id);
	public MemberVO selectCheckTrainer_detail(String id);

	//비밀번호 변경
	public void updateMemberPassword(MemberVO member);
	public void updateTrainerPassword(MemberVO member);

	//나머지 개인정보 변경
	public void updateMember_detail(MemberVO member);
	public void updateTrainer_detail(MemberVO member);

	//개인정보 마이페이지에 불러오기
	public MemberVO selectMember_detail(int mem_num);
	public MemberVO selectTrainer_detail(int mem_num);

	//프로필 사진 변경
	public void updateMemberPhoto(MemberVO member);
	public void updateTrainerPhoto(MemberVO member);
	
	//=========================================김다정
	//로그인시 알림 개수 불러오기
	public int selectNoticeCount(int mem_num);
	
}
