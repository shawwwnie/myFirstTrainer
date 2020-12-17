package kr.spring.board.free.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.free.vo.FreeBoardVO;
import kr.spring.comment.freec.vo.FreeBoardCommentVO;

public interface FreeBoardMapper {
	
	//Mapper
	public List<FreeBoardVO> selectList(Map<String,Object> map);
	
	//Mapper
	public int selectRowCount(Map<String,Object> map);
	
	@Insert("INSERT INTO board_free (free_num,free_title,free_content,free_file,free_filename,free_ip,free_type,mem_num) VALUES (board_free_seq.nextval,#{free_title},#{free_content},#{free_file},#{free_filename},#{free_ip},#{free_type},#{mem_num})")
	public void insertBoardFree(FreeBoardVO freeBoardVO);
	
	@Select("SELECT * FROM board_free b JOIN member m USING(mem_num) WHERE free_num = #{free_num}")
	public FreeBoardVO selectBoardFree(Integer free_num);
	
	@Update("UPDATE board_free SET free_hit=free_hit+1 WHERE free_num=#{free_num}")
	public void updateHitBoardFree(Integer free_num);

	//Mapper
	public void updateBoardFree(FreeBoardVO boardFree);
	
	//Mapper
	public void updateBoardFreeExImg(FreeBoardVO boardFree);
	
	@Delete("DELETE FROM board_free WHERE free_num=#{free_num}")
	public void deleteBoardFree(Integer free_num);
	
	@Delete("DELETE FROM board_free_comment WHERE  free_num=#{free_num}")
	public void deleteBoardCommentSet(Integer free_num);
	
	//댓글부분
	@Insert("INSERT INTO board_free_comment (freec_num, free_comment, free_num, mem_num) VALUES (freec_num_seq.nextval,#{free_comment},#{free_num},#{mem_num})")
	public int insertFreeComment(FreeBoardCommentVO free_comment);
	
	//Mapper
	public List<FreeBoardCommentVO> selectListFreeComment(Integer free_num);

	@Select("SELECT * FROM board_free_comment c JOIN member m USING(mem_num) WHERE c.freec_num = #{freec_num}")
	public FreeBoardCommentVO selectOneFreeComment(Integer freec_num);
	
	@Update("UPDATE board_free_comment SET free_comment = #{free_comment}, freec_modify_date = SYSDATE  WHERE freec_num = #{freec_num}")
	public int updateFreeComment(FreeBoardCommentVO free_comment);
	
	@Delete("DELETE FROM board_free_comment WHERE freec_num = #{freec_num}")
	public int deleteFreeComment(int freec_num);
	
	//댓글등록알림
	//댓글이 달리는 사람의 멤버번호
	@Select("SELECT mem_num FROM board_free WHERE free_num=#{free_num}")
	public int selectBoardWriterMemNum(int free_num);
	//댓글이 달리는 사람이 쓴 글
	@Select("SELECT free_content FROM board_free WHERE free_num=#{free_num}")
	public String selectBoardWriterContent(int free_num);
	
	//신고버튼 눌렀을때
	@Update("UPDATE board_free SET alarm=alarm+1 WHERE free_num=#{board_num}")
	public void updateAlarmReport(int board_num);
	
	//신고 수 초기화하기
	@Update("UPDATE board_free SET alarm=0 WHERE free_num=#{board_num}")
	public void resetAlarmReport(int board_num);
	
}
