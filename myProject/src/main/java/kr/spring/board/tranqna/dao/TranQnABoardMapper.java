package kr.spring.board.tranqna.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.tranqna.vo.TranQnABoardVO;
import kr.spring.comment.tranqnac.vo.TranQnABoardCommentReplyVO;
import kr.spring.comment.tranqnac.vo.TranQnABoardCommentVO;

public interface TranQnABoardMapper {
	
	//Mapper
	public List<TranQnABoardVO> selectList(Map<String,Object> map);
		
	//Mapper
	public int selectRowCount(Map<String,Object> map);
	
	//Mapper
	public TranQnABoardVO selectBoardTranqna(Integer tq_num);
	
	@Insert("INSERT INTO board_tranqna (tq_num,tq_title,tq_content,tq_file,tq_filename,tq_ip,tq_type,mem_num) VALUES (board_tranqna_seq.nextval,#{tq_title},#{tq_content},#{tq_file},#{tq_filename},#{tq_ip},#{tq_type},#{mem_num})")
	public void insertBoardTranqna(TranQnABoardVO tranqnaBoard);
	
	//Mapper
	public void updateHitBoardTranqna(Integer tq_num);
	
	//Mapper
	public void updateBoardTranqna(TranQnABoardVO tranqnaBoard);
			
	//Mapper
	public void updateBoardTranqnaExImg(TranQnABoardVO tranqnaBoard);
	
	@Delete("DELETE FROM board_tranqna WHERE tq_num=#{tq_num}")
	public void deleteBoardTranqna(Integer tq_num);
	
	//댓글부분
	//Mapper
	public List<TranQnABoardCommentVO> selectListTranqnaComment(Integer tq_num);
	
	@Insert("INSERT INTO board_tranqna_comment (tqc_num, tq_comment, tq_num, mem_num) VALUES (tqc_num_seq.nextval,#{tq_comment},#{tq_num},#{mem_num})")
	public int insertTranqnaComment(TranQnABoardCommentVO tq_comment);
	
	@Select("SELECT * FROM board_tranqna_comment c JOIN member m USING(mem_num) WHERE c.tqc_num = #{tqc_num}")
	public TranQnABoardCommentVO selectOneTranqnaComment(Integer tqc_num);
	
	@Update("UPDATE board_tranqna_comment SET tq_comment = #{tq_comment}, tqc_modify_date = SYSDATE  WHERE tqc_num = #{tqc_num}")
	public int updateTranqnaComment(TranQnABoardCommentVO tq_comment);
	
	@Delete("DELETE FROM board_tranqna_comment WHERE tqc_num = #{tqc_num}")
	public int deleteTranqnaComment(int tqc_num);
	
	//댓글의댓글부분
	//Mapper
	public List<TranQnABoardCommentReplyVO> selectReplyComment(Integer tqc_num);
	
	//Mapper
	public int insertReplyComment(TranQnABoardCommentReplyVO replyComment);
	
	//Mapper
	public int deleteReplyComment(int rtqc_num);
	
	//댓글등록알림
	//댓글이 달리는 사람의 멤버번호
	@Select("SELECT mem_num FROM board_tranqna WHERE tq_num=#{tq_num}")
	public int selectBoardWriterMemNum(int tq_num);
	
	@Select("SELECT mem_num FROM board_tranqna_comment WHERE tqc_num=#{tqc_num}")
	public int selectBoardCommentWriterMemnum(int tqc_num);
	
	//댓글이 달리는 사람이 쓴 글
	@Select("SELECT tq_content FROM board_tranqna WHERE tq_num=#{tq_num}")
	public String selectBoardWriterContent(int tq_num);

	//게시판글 댓글의 댓글이 달리는 사람이 쓴 글
	@Select("SELECT tq_comment FROM board_tranqna_comment WHERE tqc_num=#{tqc_num}")
	public String selectBoardCommentWriterContent(int tqc_num);

}
