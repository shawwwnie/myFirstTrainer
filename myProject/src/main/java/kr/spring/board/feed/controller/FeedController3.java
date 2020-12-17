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

import kr.spring.board.feed.service.FeedService3;
import kr.spring.board.feed.vo.FeedVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class FeedController3 {
	
	//로거 생성
	private Logger log = Logger.getLogger(this.getClass());
	
	private int rowCount = 10;
	
	//Service
	@Resource
	FeedService3 feedService3;
	
	//자바빈(VO) 초기화
	@ModelAttribute
	public FeedVO initCommand() {
		return new FeedVO();
	}
	
	
	//전체 피드 리스트
	@RequestMapping("/boardFeed/myFeed.do")
	public String getMyFeedView() {
		return "myFeed";
	}

	//전체 피드 리스트
	@RequestMapping("/boardFeed/myFeedAjax.do")
	@ResponseBody
	public Map<String,Object> getMyFeed(@RequestParam(value="pageNum",defaultValue="1") int currentPage, HttpSession session, Model model) {
		
		List<FeedVO> list = null;
		
		//session에서 로그인한 id의 mem_num & mam_auth 받기
		MemberVO memberVO = (MemberVO)session.getAttribute("user");
		
		
		int sessionMem_num = memberVO.getMem_num();
		int sessionMem_auth = memberVO.getMem_auth();
		
		model.addAttribute("mem_auth", sessionMem_auth);
		
		if(log.isDebugEnabled()) { log.debug("<<로그인한 회원의 mem_num>> : " + sessionMem_num);}
		if(log.isDebugEnabled()) { log.debug("<<로그인한 회원의 mem_auth>> : " + sessionMem_auth);}
		
		//map에 mem_num put
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mem_num", sessionMem_num);
		map.put("mem_auth", sessionMem_auth);
		
		//총 글의 갯수
		int count = 0;
		
		if(sessionMem_auth == 0) { //관리자 로그인시
			
			//총 글의 갯수
			count = feedService3.selectAllFeedCount(map);
			if(log.isDebugEnabled()) { log.debug("<<검색된 피드 갯수>> : " + count); }
			
			//paging 처리
			PagingUtil page = new PagingUtil(currentPage,count,rowCount,10,"myFeed.do");
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			
			//모든 피드 list에 담기
			list = feedService3.selectAllFeedList(map);
			
			
		} else if (sessionMem_auth == 1) { //일반회원 로그인시
			
			//총 글의 갯수
			count = feedService3.selectFeedCount(map);
			if(log.isDebugEnabled()) { log.debug("<<검색된 피드 갯수>> : " + count); }
			
			//paging 처리
			PagingUtil page = new PagingUtil(currentPage,count,rowCount,10,"myFeed.do");
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			
			//모든 피드 list에 담기
			list = feedService3.selectFeedList(map);
			
		} else if (sessionMem_auth == 2) { //트레이너 로그인시
			
			//총 글의 갯수
			count = feedService3.selectFeedForTCount(map);
			if(log.isDebugEnabled()) { log.debug("<<검색된 피드 갯수>> : " + count); }
			
			//paging 처리
			PagingUtil page = new PagingUtil(currentPage,count,rowCount,10,"myFeed.do");
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			
			//모든 피드 list에 담기
			list = feedService3.selectFeedListForT(map);
			
		}
		
		model.addAttribute("mem_auth", sessionMem_auth);
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		mapJson.put("list",list);
		mapJson.put("count",count);
		mapJson.put("rowCount",rowCount);
	
		return mapJson;
		
	}
	
	//(트레이너)내 회원 피드 리스트 받아오기
	@RequestMapping("/boardFeed/myMembersFeed.do")
	public ModelAndView getMyMembersFeed(HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		//회원 등급 체크
		//session에서 로그인한 id의 mem_num & mam_auth 받기
		MemberVO memberVO = (MemberVO)session.getAttribute("user");		
		int mem_auth = memberVO.getMem_auth();
				
		if(mem_auth == 1) { //일반회원 접근시
			mav.addObject("message", "트레이너회원 전용 페이지입니다.");
			mav.addObject("url","myFeed.do");
			mav.setViewName("common/result");
			
			return mav;
			
		} else { //그외
			mav.setViewName("myMembersFeed");
			return mav;
			
		}
		
	}
	
	//(트레이너)내 회원 피드 리스트 받아오기
	@RequestMapping("/boardFeed/myMembersFeedAjax.do")
	@ResponseBody
	public Map<String,Object> getMyMembersFeed(@RequestParam(value="pageNum",defaultValue="1") int currentPage, HttpSession session, Model model) {
		
		List<FeedVO> list = null;
		int count = 0;
		
		//session에서 로그인한 id의 mem_num & mam_auth 받기위해 memberVO 받기
		MemberVO memberVO = (MemberVO)session.getAttribute("user");
	
		//로그인한 회원의 mem_auth 받기
		int sessionMem_auth = memberVO.getMem_auth();			
		if(log.isDebugEnabled()) { log.debug("<<로그인한 회원의 mem_auth>> : " + sessionMem_auth);}
		
		//회원 등급 체크
		if(sessionMem_auth != 2) { //로그인한 회원의 등급이 트레이너가 아닐시
			
			Map<String,Object> mapJson = new HashMap<String,Object>();
 			
			String massage = "트레이너 회원 전용 페이지 입니다.";
			mapJson.put("message",massage);
			
			return mapJson;
			
		} else { //로그인한 회원의 등급이 트레이너일시
			
			//로그인한 회원의 mem_num 받기
			int sessionMem_num = memberVO.getMem_num();
			if(log.isDebugEnabled()) { log.debug("<<로그인한 회원의 mem_num>> : " + sessionMem_num);}
			
			//map에 mem_num put
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("mem_num", sessionMem_num);
			map.put("mem_auth", sessionMem_auth);
			
			//model
			model.addAttribute("memberVO", memberVO);
			
			
			//총 글의 갯수
			count = feedService3.selectMyMemberFeedCount(map);
			if(log.isDebugEnabled()) { log.debug("<<검색된 피드 갯수>> : " + count); }
			
			//paging 처리
			PagingUtil page = new PagingUtil(currentPage,count,rowCount,10,"myMembersFeed.do");
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			
			//모든 피드 list에 담기
			list = feedService3.selectMyMemberFeedList(map);
			
			Map<String,Object> mapJson = new HashMap<String,Object>();
			mapJson.put("list",list);
			mapJson.put("count",count);
			mapJson.put("rowCount",rowCount);
		
			return mapJson;
			
		}
		
	}
	




}