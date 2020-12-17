package kr.spring.board.sysqna.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.sysqna.vo.SysQnABoardVO;
import kr.spring.comment.sysqnac.vo.SysQnABoardCommentVO;

public interface SysQnABoardMapper {	
	
	//Mapper
	public List<SysQnABoardVO> selectList(Map<String,Object> map);
	
	//Mapper
	public int selectRowCount(Map<String,Object> map);
	
	@Insert("INSERT INTO board_sysqna (sq_num,sq_title,sq_content,sq_file,sq_filename,sq_ip,mem_num) VALUES (sq_num_seq.nextval,#{sq_title},#{sq_content},#{sq_file},#{sq_filename},#{sq_ip},#{mem_num})")
	public void insertBoardSysqna(SysQnABoardVO sysqnaBoardVO);
	
	@Select("SELECT * FROM board_sysqna b JOIN member m USING(mem_num) WHERE sq_num = #{sq_num}")
	public SysQnABoardVO selectBoardSysqna(Integer sq_num);
	
	@Update("UPDATE board_sysqna SET sq_hit=sq_hit+1 WHERE sq_num=#{sq_num}")
	public void updateHitBoardSysqna(Integer sq_num);

	//Mapper
	public void updateBoardSysqna(SysQnABoardVO sysqnaBoardVO);
	
	//Mapper
	public void updateBoardSysqnaExImg(SysQnABoardVO sysqna);
	
	@Delete("DELETE FROM board_sysqna WHERE sq_num=#{sq_num}")
	public void deleteBoardSysqna(Integer sq_num);
	
	//댓글부분
	@Insert("INSERT INTO board_sysqna_comment (sqc_num, sq_comment, sq_num, mem_num) VALUES (sqc_num_seq.nextval,#{sq_comment},#{sq_num},#{mem_num})")
	public int insertSysqnaComment(SysQnABoardCommentVO sq_comment);
	
	//Mapper
	public List<SysQnABoardCommentVO> selectListSysqnaComment(Integer sq_num);
	
	@Select("SELECT * FROM board_sysqna_comment c JOIN member m USING(mem_num) WHERE c.sqc_num = #{sqc_num}")
	public SysQnABoardCommentVO selectOneSysqnaComment(Integer sqc_num);
	
	@Update("UPDATE board_sysqna_comment SET sq_comment = #{sq_comment}, sqc_modify_date = SYSDATE  WHERE sqc_num = #{sqc_num}")
	public int updateSysqnaComment(SysQnABoardCommentVO sq_comment);
	
	@Delete("DELETE FROM board_sysqna_comment WHERE sqc_num = #{sqc_num}")
	public int deleteSysqnaComment(int sqc_num);
	
	//댓글알림기능에 필요한 글쓴 사람의 멤버번호
	@Select("SELECT mem_num FROM board_sysqna WHERE sq_num=#{sq_num}")
	public int selectBoardWriterMemNum(int sq_num);
	
	//댓글이 달리는 사람이 쓴 글
	@Select("SELECT sq_content FROM board_sysqna WHERE sq_num=#{sq_num}")
	public String selectBoardWriterContent(int sq_num);

}
