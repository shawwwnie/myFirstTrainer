package kr.spring.board.feed.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.feed.vo.FeedVO;
import kr.spring.comment.feed.vo.FeedCommentVO;
import kr.spring.member.vo.MemberVO;

//조재희
public interface FeedService2 {
	public List<FeedVO> myPersnolList(Map<String,Object> map);
	public int countingFeedList(Map<String,Object> map);
	public List<FeedVO> otherPersnolList(Map<String,Object> map);
	public int countingOtherFeedList(Map<String,Object> map);
	
	public void insertFeedBoard(FeedVO feedBoard);
	public FeedVO selectFeedBoard(Integer feedBoard_num);
	public void updateFeedBoard(FeedVO feedBoard);
	public void deleteFeedBoard(Integer feedBoard_num);

	public void insertFollow(FeedVO feedVO);
	public void deleteFollow(FeedVO feedVO);
	
	public FeedVO selectFeedDetail(Integer mem_num);
	
	public FeedVO checkFollowing(Map<String,Object> map);
	
	
	public List<FeedVO> findFollower(Integer mem_num);
	public List<FeedVO> findFollowMe(Integer mem_num);
	
//=============ajax==================
	public void updateProfile(MemberVO member);
	public void updateTrainerProfile(MemberVO member);
	public void updateIntro(MemberVO member);
	public void updateTrainerIntro(MemberVO member);
	
	public int insertFeedComment(FeedCommentVO feed_comment);
	public List<FeedCommentVO> selectListFeedComment(Integer feed_num);
	public FeedCommentVO selectFeedComment(Integer feedc_num);
	public int updateFeedComment(FeedCommentVO feed_comment);
	public int deleteFeedComment(int feedc_num);
}
