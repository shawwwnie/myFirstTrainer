package kr.spring.board.feed.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.feed.service.FeedService2;
import kr.spring.board.feed.vo.FeedVO;
import kr.spring.comment.feed.vo.FeedCommentVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class FeedAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 9;
	
	@Resource
	private MemberService memberService;
	@Resource
	private FeedService2 feedService;
	//=================게시물 읽어오기===============
	@RequestMapping("/boardFeed/myPersonalListAjax.do")
	@ResponseBody
	public Map<String,Object> getMyFeed(@RequestParam(value="pageNum",defaultValue="1") int currentPage, HttpSession session, Model model) {
		
		List<FeedVO> list = null;
		
		//session에서 로그인한 id의 mem_num & mam_auth 받기
		MemberVO memberVO = (MemberVO)session.getAttribute("user");
		
		int sessionMem_num = memberVO.getMem_num();
		int sessionMem_auth = memberVO.getMem_auth();
		
		model.addAttribute("mem_auth", sessionMem_auth);
		
		if(log.isDebugEnabled()) { 
			log.debug("/////"+memberVO);
		}
		
		//map에 mem_num put
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mem_num", sessionMem_num);
		map.put("mem_auth", sessionMem_auth);
		
		//총 글의 갯수
		int count = 0;

		//총 글의 갯수
		count = feedService.countingFeedList(map);
		if(log.isDebugEnabled()) { log.debug("<<검색된 피드 갯수>> : " + count); }

		//paging 처리
		PagingUtil page = new PagingUtil(currentPage,count,rowCount,10,"myFeed.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		//모든 피드 list에 담기
		list = feedService.myPersnolList(map);
		model.addAttribute("mem_auth", sessionMem_auth);

		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		mapJson.put("list",list);
		mapJson.put("count",count);
		mapJson.put("rowCount",rowCount);
	
		return mapJson;
		
	}
	
	@RequestMapping("/boardFeed/otherPersonalListAjax.do")
	@ResponseBody
	public Map<String,Object> getOtherFeed(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
											@RequestParam(value="mem_num") String mem_num, HttpSession session, Model model) {
		
		List<FeedVO> list = null;
		MemberVO user = (MemberVO)session.getAttribute("user");
		Map<String,Object> checkMap = new HashMap<String,Object>();

		//feed_num을 받아온것
		int num = Integer.parseInt(mem_num);
		int relation = 2;
		
		
		checkMap.put("user", user.getMem_num());
		checkMap.put("owner", num);
		
		FeedVO feed = feedService.checkFollowing(checkMap);
		
		
		//session에서 로그인한 id의 mem_num & mam_auth 받기
		MemberVO memberVO = memberService.selectMember_detail(num);//pic name 을 받아올 수있음 
		MemberVO member = memberService.selectCheckMember_detail(memberVO.getMem_id());// mem_auth를 받아 올 수있음
		if(member.getMem_auth() == 2) {
			memberVO = memberService.selectTrainer_detail(num);
			member = memberService.selectCheckTrainer_detail(memberVO.getMem_id());
		}
		memberVO.setMem_auth(member.getMem_auth());
		memberVO.setMem_num(num);
		
		log.debug("트레이닝 관계" + user.getMem_num()+","+ memberVO.getT_num() + "," + feed);
		//로그인한 유저와 피드글 유저와 의 관계 확인
		if(user.getMem_auth() == 1) {
			//일반 회원일 경우
			if(feed != null) {//팔로우하는 관계
				relation = 1;
			}
		}else if(user.getMem_auth() == 2) {
			//트레이너 회원일 경우
		}else {
			if (user.getMem_num() == memberVO.getT_num()) {// 트레이닝 관계
				relation = 0;
			}
			else if(feed != null) {//팔로우하는 관계
				relation = 1;
			}
		}
		
		int sessionMem_num = memberVO.getMem_num();
		int sessionMem_auth = memberVO.getMem_auth();
		
		model.addAttribute("mem_auth", sessionMem_auth);
		
		if(log.isDebugEnabled()) { 
			log.debug("/////"+memberVO);
		}
		
		//map에 mem_num put
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mem_num", sessionMem_num);
		map.put("mem_auth", sessionMem_auth);
		map.put("relation", relation);
		
		//총 글의 갯수
		int count = 0;

		//총 글의 갯수
		count = feedService.countingOtherFeedList(map);
		if(log.isDebugEnabled()) { log.debug("<<검색된 피드 갯수>> : " + count); }

		//paging 처리
		PagingUtil page = new PagingUtil(currentPage,count,rowCount,10,"myFeed.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		map.put("relation", relation);

		//모든 피드 list에 담기
		list = feedService.otherPersnolList(map);
		model.addAttribute("mem_auth", sessionMem_auth);

		Map<String,Object> mapJson = new HashMap<String,Object>();
		
		mapJson.put("list",list);
		mapJson.put("count",count);
		mapJson.put("rowCount",rowCount);
	
		return mapJson;
		
	}
	
	//=============사진 업로드하기================
	@RequestMapping("/boardFeed/updateMyPhoto.do")
	@ResponseBody
	public Map<String, String> processProfile(MemberVO memberVO, HttpSession session){
		Map<String, String> map = new HashMap<String, String>();
		
		MemberVO user = (MemberVO)session.getAttribute("user");

		System.out.println("프로필 수정 진입");
		if(user==null) {
			//로그인 되지 않은 경우
			map.put("result", "logout");
		}else {
			//로그인이 된 경우
			memberVO.setMem_num(user.getMem_num());
			log.debug("사진 업로드 하기 진입");
			if(user.getMem_auth() == 1) {
				feedService.updateProfile(memberVO);
			}else if(user.getMem_auth() == 2) {
				feedService.updateTrainerProfile(memberVO);
			}
			
			//이미지를 업로드 한 후 세션에 저장된 회원 정보의 이미지 이름 교체
			user.setMem_picName(memberVO.getMem_picName());
			map.put("result", "success");
		}
		return map;
	}
	
	//==============나의 한마디 업로드하기 ====================
	@RequestMapping("/boardFeed/updateIntro.do")
	@ResponseBody
	public Map<String, String> processIntro(HttpServletRequest req, HttpSession session){
		Map<String, String> map = new HashMap<String, String>();
		System.out.println("진입");
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user==null) {
			//로그인 되지 않은 경우
			map.put("result", "logout");
		}else {
			//로그인이 된 경우
			MemberVO memberVO = new MemberVO();
			memberVO.setMem_num(user.getMem_num());
			memberVO.setMem_intro(req.getParameter("af_intro"));
			System.out.println(memberVO);
			if(user.getMem_auth() == 1) {
				feedService.updateIntro(memberVO);
			}else if(user.getMem_auth() == 2) {
				feedService.updateTrainerIntro(memberVO);
			}
			
			
			//이미지를 업로드 한 후 세션에 저장된 회원 정보의 이미지 이름 교체
			user.setMem_intro(memberVO.getMem_intro());
			map.put("result", "success");
		}
		return map;
	}
	
	//=============댓글달아주기=======================

	@RequestMapping("/boardFeed/list_feedcomment.do")
	@ResponseBody
	public List<FeedCommentVO> list(@RequestParam int feed_num){		
		return feedService.selectListFeedComment(feed_num);
	}
	
	@RequestMapping("/boardFeed/submit_feedcomment.do")
	@ResponseBody
	public int Commentprocess(@RequestParam String comment,
						@RequestParam int feed_num,
						@RequestParam int mem_num){
		
		if(log.isDebugEnabled()) log.debug("<<자유게시판 댓글등록>> : " + feed_num + ":" + comment);
				
		FeedCommentVO feed_comment = new FeedCommentVO();
		feed_comment.setFeedc_comment(comment);
		feed_comment.setFeed_num(feed_num);		
		feed_comment.setMem_num(mem_num);
				
		return feedService.insertFeedComment(feed_comment);		
	}
	
	//==============댓글 수정하기 =====================
	@RequestMapping("/boardFeed/update_feedcomment.do")
	@ResponseBody
	public int updateComment(@RequestParam int feedc_num,@RequestParam String update_comment) {
		if(log.isDebugEnabled()) log.debug("<<자유게시판 댓글수정>> : " + feedc_num);
		FeedCommentVO feed_comment = feedService.selectFeedComment(feedc_num);
		feed_comment.setFeedc_comment(update_comment);
		return feedService.updateFeedComment(feed_comment);
	}
	
	//===========댓글 지우기==============
	@RequestMapping("/boardFeed/delete_feedcomment.do")
	@ResponseBody
	public int deleteComment(@RequestParam int feedc_num) {
		if(log.isDebugEnabled()) log.debug("<<자유게시판 댓글 삭제>> : " + feedc_num);
		return feedService.deleteFeedComment(feedc_num);
	}
	
	
}
