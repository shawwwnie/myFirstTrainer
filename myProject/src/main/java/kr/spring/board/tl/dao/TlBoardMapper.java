package kr.spring.board.tl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.tl.vo.TlBoardVO;
import kr.spring.comment.sysqnac.vo.SysQnABoardCommentVO;
import kr.spring.comment.tl.vo.TlBoardCommentVO;

public interface TlBoardMapper {
	//트레이너 리스트를 불러올 메서드
	@Select("select * from trainer_detail")
	public List<TlBoardVO> selectTrainerList();
	
	//트레이너 리스트의 개수를 불러올 메서드
	@Select("select count(*) from trainer_detail")
	public int selectTrainerRowCount();
	
	//트레이너의 상세정보를 보여줄 메서드
	@Select("select * from trainer_detail where mem_num=#{mem_num}")
	public TlBoardVO selectTrainerDetail(Integer mem_num);
	
	//트레이너의 프로필 사진을 업로드처리를 해줄 메서드
	@Update("update trainer_detail set mem_pic=#{mem_pic},mem_picName=#{mem_picName} where mem_num=#{mem_num}")
	public void updateTrainerProfile(TlBoardVO tlBoardVO);
	
	//트레이너의 간략 자기소개를 수정해줄 메서드
	@Update("update trainer_detail set mem_intro=#{mem_intro} where mem_num=#{mem_num}")
	public void updateIntro(TlBoardVO tlBoardVO);
	
	//회원이 지금 매칭된 트레이너가 있는지 확인하기 위한 메서드
	@Select("Select t_num FROM member_detail WHERE mem_num=#{member_num}")
	public int checkTnum(Integer member_num);
	
	//시퀀스 번호를 가저올 메서드
	@Select("select mat_seq.nextval from dual") 
	public int selectMat_num();
	
	//매칭 테이블에 정보 입력
	@Insert("insert into matching values(#{mat_num},#{mem_num},#{member_id},#{member_num},#{trainer_num})")
	public void insertMatching(Map<String, Object> map);
	
	//트레이너의 myPage에 매칭 신청 내역 표시
	@Select("SELECT * FROM(SELECT a.*, rownum rnum FROM(SELECT * from matching m JOIN member_detail d ON m.mem_num=d.mem_num where mat_to=#{mem_num} order by mat_num)a) WHERE rnum>=#{start} and rnum <= #{end}")
	public List<TlBoardVO> matchingList(Map<String,Object> map);
	
	//매칭 신청 내역 카운트
	@Select("select count(*) from matching where mat_to=#{mem_num}")
	public int matchingCount(int mem_num);
	
	//매칭 신청할 일반회원의 정보를 가져올 메서드
	@Select("select m.mem_pic,m.mem_picName from member_detail m, matching c where m.mem_num = c.mem_num and c.mem_num=#{mem_num}")
	public TlBoardVO selectMemberDetail(Integer mem_num);
	
	//매칭 신청온걸 거절할 메서드
	@Delete("delete from matching where mat_from = #{mem_num}")
	public void deleteMatchingCancle(Integer mem_num);
	
	//t_num에 트레이너의 mem_num을 엎데이트 해줄 메서드
	@Update("update member_detail set t_num=#{t_num} where mem_num=#{mem_num}")
	public void updateTNum(Map<String,Object> map);
	
	//training테이블에 연결할 시퀀스를 가져올 메서드
	@Select("select training_number.nextval from dual") 
	public int selectTrainingNumber();
	
	//training 테이블에 트레이닝 관계 넣어줄 메서드
	@Insert("insert into training values(#{training_num},#{t_num},#{mem_num})")
	public void insertTrainingTable(Map<String,Object> map);
	
	//관계 끊을시 t_num 삭제
	@Update("update member_detail set t_num = null where mem_num=#{mem_num}")
	public void deleteTnum(Integer mem_num);
	
	//관계 끊을시 training테이블 데이터 삭제
	@Delete("delete training where training_to=#{mem_num}")
	public void deleteTraining(Integer mem_num);
	
	//로그인한 일반회원이 매칭신청을 했는지 확인해줄 메서드
	@Select("select * from matching where mat_from=#{mem_num}")
	public TlBoardVO selectMatchingInfo(Integer mem_num);
	
	
	/*************************************댓글 관련**********************************************/
	//댓글 쓰기
	@Insert("INSERT INTO board_tl_comment (tlc_num, tl_comment, tl_mem_num, writer_mem_num) VALUES (tlc_num_seq.nextval,#{tl_comment},#{tl_mem_num},#{writer_mem_num})")
	public void insertTlBoardComment(TlBoardCommentVO tlBoardCommentVO);
	
	//댓글 리스트	 
	public List<TlBoardCommentVO> selectTlBoardCommentList(Map<String,Object> map);
	
	//댓글 수 카운트
	@Select("SELECT COUNT(*) FROM (SELECT * FROM board_tl_comment c JOIN member m ON c.writer_mem_num=m.mem_num ) WHERE tl_mem_num=#{tl_mem_num}")
	public int selectTlBoardCommentCount(Map<String,Object> map);
	
	//댓글 수정 삭제를 위한 한개의 특정 댓글 선택
	@Select("SELECT * FROM board_tl_comment c JOIN member m ON c.writer_mem_num=m.mem_num WHERE c.tlc_num = #{tlc_num}")
	public TlBoardCommentVO selectOneComment(int tlc_num);
	
	//댓글 수정
	@Update("UPDATE board_tl_comment SET tl_comment = #{tl_comment}, tlc_modify_date = SYSDATE  WHERE tlc_num = #{tlc_num}")
	public void updateTlBoardComment(TlBoardCommentVO tlBoardCommentVO);
	
	//댓글 삭제
	@Delete("DELETE FROM board_tl_comment WHERE tlc_num = #{tlc_num}")
	public void deleteTlBoardComment(int tlc_num);
	
	/*************************************댓글 관련**********************************************/
	
}













