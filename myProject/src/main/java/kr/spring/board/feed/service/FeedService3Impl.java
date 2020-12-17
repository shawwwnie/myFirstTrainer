package kr.spring.board.feed.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.feed.dao.FeedMapper3;
import kr.spring.board.feed.vo.FeedVO;

@Service("FeedService3")
public class FeedService3Impl implements FeedService3{
	
	@Resource
	FeedMapper3 feedMapper3;

	@Override
	public List<FeedVO> selectFeedList(Map<String, Object> map) {
		
		return feedMapper3.selectFeedList(map);
	}

	@Override
	public int selectFeedCount(Map<String, Object> map) {
		
		return feedMapper3.selectFeedCount(map);
	}
	
	@Override
	public List<FeedVO> selectFeedListForT(Map<String, Object> map) {
		
		return feedMapper3.selectFeedListForT(map);
	}

	@Override
	public int selectFeedForTCount(Map<String, Object> map) {
		
		return feedMapper3.selectFeedForTCount(map);
	}
	
	@Override
	public List<FeedVO> selectMyMemberFeedList(Map<String, Object> map) {
		
		return feedMapper3.selectMyMemberFeedList(map);
	}

	@Override
	public int selectMyMemberFeedCount(Map<String, Object> map) {
		
		return feedMapper3.selectMyMemberFeedCount(map);
	}

	@Override
	public List<FeedVO> selectAllFeedList(Map<String, Object> map) {
		
		return feedMapper3.selectAllFeedList(map);
	}

	@Override
	public int selectAllFeedCount(Map<String, Object> map) {
		
		return feedMapper3.selectAllFeedCount(map);
	}

	

}
