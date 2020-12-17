package kr.spring.board.tl.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.tl.vo.TlBoardVO;
import kr.spring.comment.tl.vo.TlBoardCommentVO;

public interface TlBoardService {
	//트레이너 리스트를 불러올 메서드
	public List<TlBoardVO> selectTrainerList();

	//트레이너 리스트의 개수를 불러올 메서드
	public int selectTrainerRowCount();
	
	//트레이너의 상세정보를 보여줄 메서드
	public TlBoardVO selectTrainerDetail(Integer mem_num);

	//트레이너의 프로필 사진을 업로드처리를 해줄 메서드
	public void updateTrainerProfile(TlBoardVO tlboardVO);
	
	//트레이너의 간략 자기소개를 수정해줄 메서드
	public void updateIntro(TlBoardVO tlBoardVO);
	
	/*----------------트랜젝션 처리 예정---------------------------------------*/
	//시퀀스 가져올 메서드
	//public int selectMat_num();
	
	//테이블에 트레이닝 신청 내역 저장할 메서드
	public void insertMatching(Map<String, Object> map);
	/*-------------------------------------------------------*/
	
	//트레이너의 myPage에 매칭 신청 내역 표시
	public List<TlBoardVO> matchingList(Map<String,Object> map);
	
	//매칭 내역 카운트
	public int matchingCount(int mem_num);
	
	//매칭 신청할 일반회원의 정보를 가져올 메서드
	public TlBoardVO selectMemberDetail(Integer mem_num);
	
	//매칭 신청온걸 거절할 메서드
	public void deleteMatchingCancle(Integer mem_num);
	
	//t_num에 트레이너의 mem_num을 엎데이트 해줄 메서드
	public void updateTNum(Map<String,Object> map);
	
	/*----------------트랜젝션 처리 예정---------------------------------------*/
	//training테이블에 연결할 시퀀스를 가져올 메서드 
	//public int selectTrainingNumber();
		
	//training 테이블에 트레이닝 관계 넣어줄 메서드
	public void insertTrainingTable(Map<String,Object> map);
	/*----------------트랜젝션 처리 예정---------------------------------------*/
	
	//관계 끊을시 t_num 삭제
	public void deleteTnum(Integer mem_num);
		
	//관계 끊을시 training테이블 데이터 삭제
	public void deleteTraining(Integer mem_num);
	
	//로그인한 일반회원이 매칭신청을 했는지 확인해줄 메서드
	public TlBoardVO selectMatchingInfo(Integer mem_num);

	/********************댓글 관련**********************/
	public void insertTlBoardComment(TlBoardCommentVO tlBoardCommentVO);
	
	public List<TlBoardCommentVO> selectTlBoardCommentList(Map<String,Object> map);
	
	public int selectTlBoardCommentCount(Map<String,Object> map);
	
	public TlBoardCommentVO selectOneComment(int tlc_num);
	
	public void updateTlBoardComment(TlBoardCommentVO tlBoardCommentVO);
	
	public void deleteTlBoardComment(int tlc_num);
	/********************댓글 관련**********************/
	
}
