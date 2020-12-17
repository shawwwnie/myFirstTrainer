package kr.spring.board.feed.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.feed.service.FeedService1;
import kr.spring.board.feed.vo.FeedVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

//이상훈
@Controller
public class FeedController1 {
	//로거 생성
	private Logger log = Logger.getLogger(this.getClass());

	private int rowCount = 10;

	@Resource
	FeedService1 FeedService;

	//자바빈 초기화
	@ModelAttribute
	public FeedVO initCommand() {
		return new FeedVO();
	}

	//추천 게시판 리스트
	@RequestMapping("/boardFeed/feedReco.do")
	public String getRecoFeedView() {
		return "feedReco";
	}

	//게시판 목록
	@RequestMapping("/boardFeed/feedRecoAjax.do")
	@ResponseBody
	public Map<String,Object> getMyFeed(@RequestParam(value="pageNum",defaultValue="1") int currentPage, HttpSession session, Model model) {

		List<FeedVO> list = null;

		MemberVO memberVO = (MemberVO)session.getAttribute("user");

		int sessionMem_num = memberVO.getMem_num();
		
		int count = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mem_num", sessionMem_num);


		//총 글의 갯수 count에 저장
		count = FeedService.selectCount(map);

		if(log.isDebugEnabled()) {log.debug("<<총 글의 갯수>> : " + count);}

		//paging 처리
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, 10, "feedReco.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		//모든 피드 list에 담기
		list = FeedService.selectRecoList(map);
		if(log.isDebugEnabled()) {log.debug("<<list에 담긴 글 목록>> : " + list);}

		Map<String,Object> mapJson = new HashMap<String,Object>();

		mapJson.put("list",list);
		mapJson.put("count",count);
		mapJson.put("rowCount",rowCount);

		return mapJson;
	}

	//이미지 출력
	@RequestMapping("/boardFeed/imageView.do")
	public ModelAndView viewImage(@RequestParam int feed_num) {

		FeedVO feed = FeedService.selectRecoBoard(feed_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		//byte[]타입의 데이터
		mav.addObject("imageFile", feed.getFeed_file());
		mav.addObject("filename", feed.getFeed_filename());


		return mav;
	}

}
