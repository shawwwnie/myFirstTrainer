package kr.spring.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.member.dao.MemberMapper;
import kr.spring.member.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

	@Resource
	private MemberMapper memberMapper;

	//일반회원 회원가입 사이클
	@Override
	public void insertMember_detail(MemberVO member) {
		//3개의 메서드가 전부 성공해야지만 commit
		//아닐 경우 rollback
		member.setMem_num(memberMapper.selectMem_num());//시퀀스 적용하고
		memberMapper.insertMember(member);//사용자 공통인 member테이블에 저장하고
		memberMapper.insertMember_detail(member);//일반회원 테이블에 저장한다
	}

	//트에이너 회원가입 사이클
	@Override
	public void insertTrainer_detail(MemberVO member) {
		//3개의 메서드가 전부 성공해야지만 commit
		//아닐 경우 rollback
		member.setMem_num(memberMapper.selectMem_num());//시퀀스 적용하고
		memberMapper.insertTrainer(member);//사용자 공통인 member테이블에 저장하고
		memberMapper.insertTrainer_detail(member);//트에이너 테이블에 저장한다
	}

	//일반회원 로그인 처리와 아이디 중복체크 확인에 쓰일 메서드
	@Override
	public MemberVO selectCheckMember_detail(String id) {
		return memberMapper.selectCheckMember_detail(id);
	}

	//트레이너 로그인 처리와 아이디 중복체크 확인에 쓰일 메서드
	@Override
	public MemberVO selectCheckTrainer_detail(String id) {
		return memberMapper.selectCheckTrainer_detail(id);
	}

	//일반회원 id찾는 메서드
	@Override
	public MemberVO selectFindIdMember_detail(String cell, String email) {
		return memberMapper.selectFindIdMember_detail(cell, email);
	}

	//트레이너 id찾는 메서드
	@Override
	public MemberVO selectFindIdTrainer_detail(String cell, String email) {
		return memberMapper.selectFindIdTrainer_detail(cell, email);
	}

	//일반회원 pw찾는 메서드
	@Override
	public MemberVO selectFindPwMember_detail(String id, String cell) {
		return memberMapper.selectFindPwMember_detail(id, cell);

	}

	//트레이너 pw찾는 메서드
	@Override
	public MemberVO selectFindPwTrainer_detail(String id, String cell) {
		return memberMapper.selectFindPwTrainer_detail(id, cell);
	}

	@Override
	public void updateMemberPassword(MemberVO member) {
		memberMapper.updateMemberPassword(member);
	}

	@Override
	public void updateTrainerPassword(MemberVO member) {
		memberMapper.updateTrainerPassword(member);
	}
	
	@Override
	public void deleteMember_detail(Integer mem_num) {
		memberMapper.deleteMember(mem_num); //auth값 3(탈퇴회원)으로 변경
		memberMapper.deleteMember_detail(mem_num); //일반회원 탈퇴
	}

	@Override
	public void deleteTrainer_detail(Integer mem_num) {
		memberMapper.deleteMember(mem_num); //auth값 3(탈퇴회원)으로 변경
		memberMapper.deleteTrainer_detail(mem_num); //트레이너 탈퇴
	}

	@Override
	public void updateMember_detail(MemberVO member) {
		memberMapper.updateMember_detail(member);
	}

	@Override
	public void updateTrainer_detail(MemberVO member) {
		memberMapper.updateTrainer_detail(member);
	}

	@Override
	public MemberVO selectMember_detail(int mem_num) {
		return memberMapper.selectMember_detail(mem_num);
	}

	@Override
	public MemberVO selectTrainer_detail(int mem_num) {
		return memberMapper.selectTrainer_detail(mem_num);
	}

	@Override
	public void updateMemberPhoto(MemberVO member) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTrainerPhoto(MemberVO member) {
		// TODO Auto-generated method stub

	}
	
	//=========================김다정
	@Override
	public int selectNoticeCount(int mem_num) {
		return memberMapper.selectNoticeCount(mem_num);
	}

}
