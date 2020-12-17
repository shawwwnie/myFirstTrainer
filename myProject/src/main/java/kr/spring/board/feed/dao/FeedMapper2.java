package kr.spring.board.feed.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.feed.vo.FeedVO;
import kr.spring.comment.feed.vo.FeedCommentVO;
import kr.spring.member.vo.MemberVO;

//조재희
public interface FeedMapper2 {
	//내가 쓴 모든 글 받아오기
	public List<FeedVO> myPersnolList(Map<String,Object> map);
	//관계의 의하여 글 받아오기 (0:트레이너 , 1:팔로우 , 2:모르는 사람 )
	public List<FeedVO> otherPersnolList(Map<String,Object> map);
	
	//내가 쓴 글의 갯수 구하기
	@Select("select count(*) from feed where mem_num=#{mem_num}")
	public int countingFeedList(Map<String,Object> map);
	//관계의 의한 글 갯수 구하기 
	public int countingOtherFeedList(Map<String,Object> map);

	//피드등록
	@Insert("Insert into feed (feed_num, feed_type, feed_content, feed_file, feed_filename, feed_ip, mem_num, mem_id, feed_auth, feed_reg_date)"
			+" values(feed_num.nextval, #{feed_type}, #{feed_content}, #{feed_file}, #{feed_filename}, #{feed_ip}, #{mem_num}, #{mem_id}, #{feed_auth}, SYSDATE)")
	public void insertFeedBoard(FeedVO feedBoard);
	//피드선택(상세글)
	@Select("SELECT * FROM feed b JOIN member m USING(mem_num) WHERE feed_num = #{feed_num}")
	public FeedVO selectFeedBoard(Integer feedBoard_num);
	//mapper 피드 수정
	public void updateFeedBoard(FeedVO feedBoard);
	@Delete("DELETE FROM feed WHERE feed_num=#{feed_num}")
	public void deleteFeedBoard(Integer feedBoard_num);
	
	//==============================================
	//팔로우하기
	@Insert("Insert into follower (follower_num, mem_num,follower_to)"+
	" values(follower_number.nextval, #{mem_num}, #{follower_to})")
	public void insertFollow(FeedVO feedVO);
	
	//팔로우끊기
	@Delete("delete from follower where mem_num=#{mem_num} AND follower_to = #{follower_to}")
	public void deleteFollow(FeedVO feedVO);
	
	//팔로우 관계 확인
	@Select("SELECT * FROM follower WHERE mem_num=#{user} AND follower_to=#{owner}")
	public FeedVO checkFollowing(Map<String,Object> map);
	 
	//내가 팔로우하는사람 찾기
	@Select("select * from member m left outer join follower f ON m.mem_num = f.follower_to where f.mem_num=#{mem_num}")
	public List<FeedVO> findFollower(Integer mem_num);
	
	//나를 팔로우하는 사람 찾기
	@Select("select * from member m left outer join follower f ON m.mem_num = f.mem_num where f.follower_to=#{mem_num}")
	public List<FeedVO> findfollowMe(Integer mem_num);
	
	//=========================회원정보======
	@Update("update member_detail set mem_pic=#{mem_pic}, mem_picname=#{mem_picName} where mem_num=#{mem_num}")
	public void updateProfile(MemberVO member);
	@Update("update trainer_detail set mem_pic=#{mem_pic}, mem_picname=#{mem_picName} where mem_num=#{mem_num}")
	public void updateTrainerProfile(MemberVO member);
	
	@Update("update member_detail set mem_intro=#{mem_intro} where mem_num=#{mem_num}")
	public void updateIntro(MemberVO member);
	@Update("update trainer_detail set mem_intro=#{mem_intro} where mem_num=#{mem_num}")
	public void updateTrainerIntro(MemberVO member);
	
	@Select("select * FROM feed where mem_num=#{mem_num}")
	public FeedVO selectFeedDetail(Integer mem_num);
	
	//===================댓글 관련 =========================
	@Insert("INSERT INTO feed_comment (feedc_num, feedc_comment, feed_num, mem_num) VALUES (feedc_num_seq.nextval,#{feedc_comment},#{feed_num},#{mem_num})")
	public int insertFeedComment(FeedCommentVO feedc_comment);
	
	public List<FeedCommentVO> selectListFeedComment(Integer feed_num);
	
	@Select("SELECT * FROM feed_comment c JOIN member m USING(mem_num) WHERE c.feedc_num = #{feedc_num}")
	public FeedCommentVO selectFeedComment(Integer feedc_num);
	
	@Update("UPDATE feed_comment SET feedc_comment = #{feedc_comment}, feedc_modify_date = SYSDATE  WHERE feedc_num = #{feedc_num}")
	public int updateFeedComment(FeedCommentVO feedc_comment);
	
	@Delete("DELETE FROM feed_comment WHERE feedc_num = #{feedc_num}")
	public int deleteFeedComment(int feedc_num);
	
} 
