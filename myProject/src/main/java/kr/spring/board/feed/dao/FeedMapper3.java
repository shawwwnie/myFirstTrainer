package kr.spring.board.feed.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.board.feed.vo.FeedVO;

//홍아현
public interface FeedMapper3 {
	
	/*****관리자용 모든 피드의 수 제외한 SQL문 전부 .xml에 기재*****/
	
	//(일반회원)전체 피드 리스트 받아오기(팔로워의 팔로워 공개 이상 글 + 내가 쓴 글 중 팔로워 공개 이상 글)
	public List<FeedVO> selectFeedList(Map<String,Object> map);
	//(일반회원)전체 피드의 수 카운트
	public int selectFeedCount(Map<String,Object> map);
	
	
	//(트레이너)전체 피드 리스트 받아오기
	//(팔로워(+내회원)의 팔로워 공개 이상 글 + 내 회원의 트레이너 공개 이상 글 +  내가 쓴 글 중 팔로워 공개 이상 글)
	public List<FeedVO> selectFeedListForT(Map<String,Object> map);
	//(트레이너)전체 피드의 수 카운트
	public int selectFeedForTCount(Map<String,Object> map);

	//(트레이너)내회원의 피드만 리스트 받아오기 (내 회원의 전체공개+팔로워공개+트레이너 공개 글)
	public List<FeedVO> selectMyMemberFeedList(Map<String,Object> map);
	//내 회원의 피드의 수 카운트
	public int selectMyMemberFeedCount(Map<String,Object> map);
	
	//(관리자) 모든 피드 보기
	public List<FeedVO> selectAllFeedList(Map<String,Object> map);
	//(관리자) 모든 피드의 수 카운트
	@Select("SELECT COUNT(*) FROM feed")
	public int selectAllFeedCount(Map<String,Object> map);
	
}
