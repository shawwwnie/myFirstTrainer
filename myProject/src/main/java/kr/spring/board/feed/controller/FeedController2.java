package kr.spring.board.feed.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.feed.service.FeedService2;
import kr.spring.board.feed.vo.FeedVO;
import kr.spring.board.tl.vo.TlBoardVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;

//조재희
@Controller
public class FeedController2 {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	FeedService2 feedService;
	
	@Resource
	MemberService memberService;
	
	//자바빈 초기화
	@ModelAttribute
	public FeedVO initCommand() {
		return new FeedVO();
	}
	
	/*게시물 목록(현재 로그인된 아이디와 클릭된 아이디의 관계 파악하여 list 목록을 호출)
	 * 	session_num과 profile_num이 같은 경우
	  	=> 나만보기, 팔로우공개, 트레이너공개, 전체공개가 보여진다
		본인이 본인 프로필 사진을 누를 경우
		session_num과 profile_num이 다르면서 following이 아닌 경우
		다른 사람의 프로필 사진을 누르면서 following이 아닌 경우 => 전체공개만 보여진다
		session_num과 profile_num이 다르면서 following이 맞는 경우
		다른 사람의 프로필 사진을 누르면서 following이 맞는 경우 => 팔로우공개, 전체공개가 보여진다
		session_num과 profile_num이 다르면서 training이 아닌 경우
		다른 사람의 프로필 사진을 누르면서 training이 아닌 경우 => 전체공개만 보여진다
		session_num과 profile_num이 다르면서 training이 맞는 경우
		다른 사람의 프로필 사진을 누르면서 training이 아닌 경우 => 팔로우공개, 트레이너공개, 전체공개가 보여진다
	 */
	//mypersonal 게시판 호출
	@RequestMapping(value="/boardFeed/feedList.do")
	public ModelAndView feedList(HttpSession session) {
		//회원번호를 얻기위해 세션에 저장된 회원 정보를 반환
	    MemberVO vo = (MemberVO)session.getAttribute("user");
	    MemberVO memberVO = new MemberVO();
	    log.debug("트레이너 호출 vo" + vo);
	    System.out.println(vo);
	    
	    //리스트 호출
	    List<FeedVO> followerList = null;
	    //리스트에 로그인한 회원의 mem_num 넣기
	    followerList = feedService.findFollower(vo.getMem_num());
	    
	  //리스트 호출
	    List<FeedVO> followMeList = null;
	    //리스트에 로그인한 회원의 mem_num 넣기
	    followMeList = feedService.findFollowMe(vo.getMem_num());
	    
	    if(vo.getMem_auth() == 1) {
	    	memberVO = memberService.selectMember_detail(vo.getMem_num());
	    	
	    }else if(vo.getMem_auth() == 2) {
	    	
	    	memberVO = memberService.selectTrainer_detail(vo.getMem_num());

		    log.debug("트레이너 호출 if문" + memberVO);
	    }else{
	    	memberVO = memberService.selectMember_detail(vo.getMem_num());
	    }
	    
	    log.debug("트레이너 호출 수행후" + memberVO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("myPersonalList");
		mav.addObject("member", memberVO);
		mav.addObject("followerList",followerList);
		mav.addObject("followMeList",followMeList);
		return mav;
	}  
	
	
	@RequestMapping(value="/boardFeed/otherFeedList.do")
	public ModelAndView otherFeedList(@RequestParam int mem_num, HttpSession session) {
		
		//맵 객체 생성
		Map<String,Object> map = new HashMap<String,Object>();
		//세션에서 로그인한 회원의 정보를 user에 담음
		MemberVO user = (MemberVO)session.getAttribute("user");
		//로그인한 회원의 mem_num을 user라는 이름으로 map 객체에 담음
		log.debug("user에 담긴 팔로우관계 확인 결과" + user);
		log.debug("mem_num에 담긴 팔로우관계 확인 결과" + mem_num);
		
		map.put("user", user.getMem_num());
		map.put("owner", mem_num);
		//쿼리문이 작동한 결과를 vo에 담음
		FeedVO vo = feedService.checkFollowing(map);
		
		log.debug("vo에 담긴 팔로우관계 확인 결과" + vo);
		
		//멤버 정보를 받아옴 
	    MemberVO memberVO = memberService.selectMember_detail(mem_num);//pic name 을 받아올 수있음

	    log.debug("트레이너 호출 수행후" + memberVO);
		MemberVO member = memberService.selectCheckMember_detail(memberVO.getMem_id());// mem_auth를 받아 올 수있음
		if(member.getMem_auth() == 2) {// 트레이너일 경우 트레이너 정보 받아옴
			memberVO = memberService.selectTrainer_detail(mem_num);
			member = memberService.selectCheckTrainer_detail(memberVO.getMem_id());
		}
		memberVO.setMem_auth(member.getMem_auth());
		memberVO.setMem_num(mem_num);

	    log.debug("트레이너 호출 수행후" + memberVO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("otherPersonalList");
		mav.addObject("member", memberVO);
		mav.addObject("member2", vo);
		
		return mav;
	}
	//게시물 등록 폼
	@RequestMapping(value="/boardFeed/feedWrite.do", method=RequestMethod.GET)
	public String feedWriteForm(){
		if(log.isDebugEnabled()) log.debug("<<피드 글쓰기 폼 진입 시도>>" );
		return "feedWrite";
	}

	//게시물 등록 처리
	@RequestMapping(value="/boardFeed/feedWrite.do",method=RequestMethod.POST)
	public String feedWriteSubmit(@Valid FeedVO feedVO, 
			BindingResult result, 
			HttpServletRequest request,
			HttpSession session,
			Model model) {

		if(log.isDebugEnabled()) log.debug("<<feedVO에 담긴것>> :" + feedVO);

		
		//회원번호, ID 셋팅
		MemberVO member = (MemberVO)session.getAttribute("user");
		feedVO.setMem_num(member.getMem_num());
		feedVO.setMem_id(member.getMem_id());
		
		//ip 셋팅
		feedVO.setFeed_ip(request.getRemoteAddr());
		
		
		if(log.isDebugEnabled()) log.debug("<<feedVO에 세션값 저장 후 마이 퍼스널 게시판 글 저장 시도>> :" + feedVO);
		
		 
		//글쓰기
		feedService.insertFeedBoard(feedVO);
		
		if(result.hasErrors()) return "feedWrite";
		model.addAttribute("message", "운동일지가 등록되었습니다.");
		model.addAttribute("url",request.getContextPath() + "/boardFeed/feedList.do");

		return "common/result";
	}
	
	//게시물 상세 페이지 진입 폼
	@RequestMapping("/boardFeed/feedDetail.do")
	public ModelAndView process(@RequestParam int feed_num, HttpSession session) {

		//회원번호를 얻기위해 세션에 저장된 회원 정보를 반환
	    MemberVO vo = (MemberVO)session.getAttribute("user");
	    MemberVO memberVO = memberService.selectMember_detail(vo.getMem_num());
		
		if(log.isDebugEnabled()) {
			log.debug("<<글 상세>> : " + feed_num);
		}
		
		FeedVO feed = feedService.selectFeedBoard(feed_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("feedDetail");
		mav.addObject("feed", feed);
		mav.addObject("member", memberVO);
		
		return mav;
	}
	//다른 사람의 feed상세 페이지 
	@RequestMapping("/boardFeed/otherFeedDetail.do")
	public ModelAndView otherFeedprocess(@RequestParam int feed_num) {

		//회원번호를 얻기위해 세션에 저장된 회원 정보를 반환
		FeedVO feed = feedService.selectFeedBoard(feed_num);
	    MemberVO memberVO = memberService.selectMember_detail(feed.getMem_num());
	    MemberVO member = memberService.selectCheckMember_detail(memberVO.getMem_id());// mem_auth를 받아 올 수있음
		if(member.getMem_auth() == 2) {
			memberVO = memberService.selectTrainer_detail(feed.getMem_num());
			member = memberService.selectCheckTrainer_detail(memberVO.getMem_id());
		}
		memberVO.setMem_auth(member.getMem_auth());
		memberVO.setMem_num(member.getMem_num());
		
		if(log.isDebugEnabled()) {
			log.debug("<<글 상세>> : " + feed_num);
			log.debug("//////////"+memberVO);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("otherFeedDetail");
		mav.addObject("feed", feed);
		mav.addObject("member", memberVO);
		
		return mav;
	}

	//게시물 수정 폼
	@RequestMapping(value="/boardFeed/feedUpdate.do",
			method=RequestMethod.GET)
	public String form(@RequestParam int feed_num,
			Model model) {

		FeedVO feed = feedService.selectFeedBoard(feed_num);

		model.addAttribute("feedVO", feed);

		return "feedModify";
	}

	//게시물 수정 처리
	@RequestMapping(value="/boardFeed/feedUpdate.do",
			        method=RequestMethod.POST)
	public String submitUpdate(@Valid FeedVO feedVO,
			             BindingResult result,
			             HttpServletRequest request,
			             HttpSession session,
			             Model model) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<글 정보 수정>> : " + feedVO);
		}
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "boardModify";
		}
		
		//회원 번호 셋팅
		MemberVO user = (MemberVO)session.getAttribute("user");
		feedVO.setMem_num(user.getMem_num());
		
		//ip 셋팅
		feedVO.setFeed_ip(request.getRemoteAddr());
		
		//글 수정
		feedService.updateFeedBoard(feedVO);
		
		//View에 표시할 메시지
		model.addAttribute("message", "글 수정 완료!!");
		model.addAttribute("url", 
				request.getContextPath()+"/boardFeed/feedList.do");
		
		//타일스 설정에 아래 뷰이름이 없으면 단독으로 JSP 호출
		return "common/result";
	}
	
	//게시물 삭제 
	@RequestMapping("/boardFeed/feedDelete.do")
	public String submitDelete(@RequestParam int feed_num,
			                   Model model,
			                   HttpServletRequest request) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<게시판 글 삭제>> : " + feed_num);
		}
		
		//글 삭제
		feedService.deleteFeedBoard(feed_num);
		
		model.addAttribute("message", "글 삭제 완료!!");
		model.addAttribute("url", 
				request.getContextPath()+"/boardFeed/feedList.do");
		
		return "common/result";
	}
	//이미지 출력
	@RequestMapping("/boardFeed/photoView.do")
	public ModelAndView viewFeedImage(@RequestParam int feed_num)	{

		FeedVO feedVO = feedService.selectFeedBoard(feed_num);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", feedVO.getFeed_file());
		mav.addObject("filename", feedVO.getFeed_filename());

		return mav;

	}
	//팔로우 하기
	@RequestMapping("/boardFeed/insertFollow.do")
	public String insertFollow(@RequestParam int mem_num, HttpSession session, Model model) {
		//feedVO에 담긴것
		if(log.isDebugEnabled()) log.debug("<<상세정보창에 포함되어있는 회원번호>> :" + mem_num);
		FeedVO feedVO = new FeedVO();
		//로그인한 회원 정보를 memberVO에 담기
		MemberVO member = (MemberVO)session.getAttribute("user");
		//글 상세정보에 있는 mem_num을 follower_to로 셋팅
		feedVO.setFollower_to(mem_num);
		//로그인한 회원의 mem_num을 feedVO에 담기
		feedVO.setMem_num(member.getMem_num());
		
		//정보 처리 후 feedFO에 담긴것 출력
		if(log.isDebugEnabled()) log.debug("<<정보처리 후 feedVO에 담긴것>> :" + feedVO);
		
		feedService.insertFollow(feedVO);
		//안내메세지 노출
		model.addAttribute("message", "팔로우 완료");
		model.addAttribute("url", "/MyFirstTrainer/boardFeed/otherFeedList.do?mem_num="+mem_num);
		return "common/result";
	}
	
	//팔로우 끊기
	@RequestMapping("boardFeed/deleteFollow.do")
	public String deleteFollow(@Valid FeedVO feedVO, BindingResult result, HttpServletRequest request, HttpSession session, Model model) {
		//feedVO에 담긴것
		if(log.isDebugEnabled()) log.debug("<<feedVO에 담긴것>> :" + feedVO);
	
		//로그인한 회원 정보를 memberVO에 담기
		MemberVO member = (MemberVO)session.getAttribute("user");
		//기존 feedVO에 담겨있는 mem_num을 follower_to로 셋팅
		feedVO.setFollower_to(feedVO.getMem_num());
		//로그인한 회원의 mem_num을 feedVO에 담기
		feedVO.setMem_num(member.getMem_num());
		
		//정보 처리 후 feedFO에 담긴것 출력
		if(log.isDebugEnabled()) log.debug("<<정보처리 후 feedVO에 담긴것>> :" + feedVO);
		
		//mem_num과 follower_to가 같은 것을 찾아서 삭제
		feedService.deleteFollow(feedVO);
		
		//안내메세지 노출
		model.addAttribute("message", "언팔로우 완료");
		model.addAttribute("url", request.getContextPath()+"/boardFeed/feedList.do");
		
		return "common/result";
	}

	


}
