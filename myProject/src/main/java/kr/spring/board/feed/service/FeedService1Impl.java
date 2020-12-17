package kr.spring.board.feed.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.board.feed.dao.FeedMapper1;
import kr.spring.board.feed.vo.FeedVO;

@Service("FeedService1")
public class FeedService1Impl implements FeedService1{

	@Resource
	FeedMapper1 feedMapper;

	@Override
	public List<FeedVO> selectRecoList(Map<String, Object> map) {
		return feedMapper.selectRecoList(map);
	}

	@Override
	public int selectCount(Map<String, Object> map) {
		return feedMapper.selectCount(map);
	}

	@Override
	public FeedVO selectRecoBoard(Integer feed_num) {
		return feedMapper.selectRecoBoard(feed_num);
	}

	@Override
	public void insertFollow(FeedVO feed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFollow(FeedVO feed) {
		// TODO Auto-generated method stub
		
	}
	
	
 
}
