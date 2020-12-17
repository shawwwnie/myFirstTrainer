package kr.spring.board.tl.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.feed.service.FeedService2;
import kr.spring.board.feed.vo.FeedVO;
import kr.spring.board.notice.service.NoticeService;
import kr.spring.board.notice.vo.NoticeVO;
import kr.spring.board.tl.service.TlBoardService;
import kr.spring.board.tl.vo.TlBoardVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class TlBoardController {

	//로그 처리
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private TlBoardService tlBoardService;
	
	@Resource
	private FeedService2 feedService;
	
	@Resource
	private NoticeService noticeService;

	//자바빈(VO) 초기화
	//서버 유효성 체크시 필수로 필요
	@ModelAttribute
	public TlBoardVO initCommand() {
		return new TlBoardVO();
	}
	
	@ModelAttribute
	public FeedVO initCommandFeed() {
		return new FeedVO();
	}
	
	@ModelAttribute
	public NoticeVO initCommandNotice() { 
		return new NoticeVO(); 
	}


	//트레이너리스트를 눌렀을경우 들어올 페이지
	//트레이너리스트를 호출
	@RequestMapping("/trainerList/trainerList.do")
	public ModelAndView formTrainerList(
			@RequestParam(value="pageNum", defaultValue="1") int currentPage) {
		//로그인 상태일 경우 자신이 팔로우한 트레이너 리스트를 상단에 표시
		//비 로그인 상태일 경우 팔로우한 트레이너 리스트는 볼 수 없음

		//key와 value를 받아 배열로 사용할 map 선언
		Map<String,Object> map = new HashMap<String,Object>();

		//총 글의 갯수를 가져옴
		int count = tlBoardService.selectTrainerRowCount();

		//로그정보로 count 개수 잘들어왔는지 확인
		if(log.isDebugEnabled()) {
			log.debug("<<<count>>> :" + count);
		}

		//페이징 설정
		PagingUtil page = new PagingUtil(currentPage,count,10,10,"trainerList.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<TlBoardVO> list = null;

		//트레이너 리스트 정보가 있을 경우
		if(count > 0) {
			list = tlBoardService.selectTrainerList();

			//list가 제데로 담겼는지 로그정보로 확인
			if(log.isDebugEnabled()) {
				log.debug("<<<트레이너 리스트 목록>>> : " + list);
			}
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("trainerList");//definition 설정명
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}

	//트레이너 리스트 페이지에서 원하는 트레이너를 눌렀을 시 작동할 메서드
	//트레이너 상세정보페이지를 보여줄 메서드
	@RequestMapping(value="/trainerList/trainerListDetail.do", method=RequestMethod.GET)
	public String formTrainerListDetail(@RequestParam int mem_num, Model model, HttpSession session) {

		//트레이너리스트 페이지에서 선택한 트레이너 mem_num이 잘 전송되었는지 확인
		if(log.isDebugEnabled()) {
			log.debug("<<<트레이너 mem_num 번호>>> :" + mem_num);
		}
		//mem_num인 트레이너의 정보를 가져오는 메서드 필요
		TlBoardVO vo = tlBoardService.selectTrainerDetail(mem_num);

		//읽어온 정보를 로그를 통해 보여줌
		if(log.isDebugEnabled()) {
			log.debug("<<<트레이너 상세 정보>>> : " + vo);
		}
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		//읽어온 정보를 로그를 통해 보여줌
		if(log.isDebugEnabled()) {
			log.debug("<<<로그인 유저의 정보>>> : " + user);
		}
		
		//로그인한 유저가 해당 트레이너에게 매칭신청을 보냈는지 확인하기 위한 메서드
		TlBoardVO vo2 = tlBoardService.selectMatchingInfo(user.getMem_num());

		//읽어온 정보를 모델을 정해서 request에 저장
		model.addAttribute("trainer", vo);
		model.addAttribute("user", user);
		model.addAttribute("matching",vo2);

		//definition 설정명을 호출
		return "trainerListDetail";
	}
	
	//트레이너 상세정보 페이지에서 트레이닝 신청하기 버튼 눌렀을 시 작동할 메서드
	@RequestMapping(value="/trainerList/matching.do", method=RequestMethod.GET)
	public String matching(@RequestParam int mem_num,Model model,HttpSession session) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		//트레이너상세정보 페이지에서 선택한 트레이너 mem_num이 잘 전송되었는지 확인
		if(log.isDebugEnabled()) {
			log.debug("<<<매칭할 트레이너 mem_num 번호>>> :" + mem_num);
		}
		
		TlBoardVO tlboardVO = new TlBoardVO();
		
		//가져온 트레이너 mem_num을 vo에 셋팅
		tlboardVO.setMem_num(mem_num); //트레이너 list에서 받아온 mem_num(트레이닝 신청 당하는 mem_num)
		map.put("trainer_num", mem_num);
		
		//로그인한 회원의 mem_num
		MemberVO memberVO = (MemberVO) session.getAttribute("user");
		
		if(log.isDebugEnabled()) {
			log.debug("<<<로그인한 회원의 정보 알아보기>>> :" + memberVO);
		}
		
		//로그인 상태가 아닌 경우
		if(memberVO == null) {
			return "redirect:/member/login.do";
		}else {//누구든 로그인을 한 상태일 경우
		
			map.put("mem_num", memberVO.getMem_num());
			map.put("member_id", memberVO.getMem_id());
			map.put("member_num", memberVO.getMem_num());
			
			
			if(log.isDebugEnabled()) {
				log.debug("<<<매칭하기위해 세션에서 가져온 로그인한 회원의 번호>>> :" + memberVO.getMem_num());
			}
		
			//2가지로 나뉨
			if(memberVO.getMem_auth() == 1) {//일반회원일 경우
				
				int tNum = memberVO.getT_num();
				
				if(log.isDebugEnabled()) {
					log.debug("<<<회원의 매칭된 트레이너 회원 번호>>> :" + tNum);
				} 
				
				if(tNum == 0) {//매칭상대가 없는경우
					//매칭테이블에 신청 내역 저장
					tlBoardService.insertMatching(map);
					
					//알림추가 트레이너한테 매칭신청왔다고 알림 보내기
					NoticeVO noticeVO = new NoticeVO();
					noticeVO.setWriter_memnum(mem_num); //트레이너 멤넘
					noticeVO.setReply_mem_num(memberVO.getMem_num()); //신청보낸 회원 멤넘
					noticeVO.setBoard_comment("트레이닝 신청 도착");
					noticeVO.setNotice_comment("트레이닝 신청을 하였습니다.");
					noticeVO.setWriter_board("-");
					noticeVO.setReturn_url("trainerList/matchingList.do?mem_num=" + mem_num);
					noticeService.insertNoticeVO(noticeVO);  
					
					return "redirect:/trainerList/trainerList.do";
					
				}else{//매칭상대가 있는 경우
					return "redirect:/trainerList/trainerList.do";
				}
			}else{//일반회원이 아닌 나머지 피플
				//여기는 성사가 되면 안되니까 
				return "redirect:/trainerList/trainerList.do";
			}
		}
	}


	//트레이너 상세정보 페이지에서 프로필 사진 버튼을 눌렀을 시 작동할 메서드
	//프로필사진 수정 페이지를 보여줄 메서드
	@RequestMapping(value="/trainerList/prUpdate.do", method=RequestMethod.GET)
	public String formPrUpdate(HttpSession session, Model model) {

		//세션에서 현재 로그인한 회원의 정보를 가져옴
		//로그인 할 때 MemberVO타입으로 세션을 부여해줘서 가져올 때도 MemberVO타입으로 가져와야함
		MemberVO user = (MemberVO)session.getAttribute("user");

		//읽어온 정보를 로그를 통해 보여줌
		if(log.isDebugEnabled()) {
			log.debug("<<<로그인한 유저 정보>>> : " + user);
		}

		//로그인한 회원의 mem_num으로 쿼리문 검색해서 trainer_detail의 정보를 가저옴
		TlBoardVO tlboardVO = tlBoardService.selectTrainerDetail(user.getMem_num());

		//읽어온 정보를 로그를 통해 보여줌
		if(log.isDebugEnabled()) {
			log.debug("<<<로그인한 회원의 테이블 정보>>> : " + tlboardVO);
		}

		//테이블에서 가저온 로그인한 회원 정보를 모델에 추가
		model.addAttribute("trainer", tlboardVO);

		//definition 설정명을 호출
		return "prUpdate";
	}
	
	//트레이너 리스트페이지에서 자기소개 수정 버튼을 눌렀을 시 작동할 메서드
	//자기소개 수정폼을 불러올 메서드
	@RequestMapping(value="/trainerList/introUpdate.do",method=RequestMethod.GET)
	public String formIntroUpdate() {
		
		//definition 설정명 호출
		return "introUpdate";
	}
	
	//트레이너의 자기소개변졍을 처리해줄 메서드
	@RequestMapping(value="/trainerList/introUpdate.do",method=RequestMethod.POST)
	public String submitIntroUpdate(TlBoardVO tlboardVO, HttpSession session) {
		
		//폼에서 입력한 자기소개 내용
		//읽어온 정보를 로그를 통해 보여줌
		if(log.isDebugEnabled()) {
			log.debug("<<<폼에서 작성한 intro>>> : " + tlboardVO);
		}
		
		//세션에서 회원정보 가져옴
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		//읽어온 정보를 로그를 통해 보여줌
		if(log.isDebugEnabled()) {
			log.debug("<<<세션션션션>>> : " + user);
		}
		
		//세션에서 가져온 mem_num을 tlBoardVO에 셋팅
		tlboardVO.setMem_num(user.getMem_num());
		
		//intro수정
		tlBoardService.updateIntro(tlboardVO);
		
		return "redirect:/trainerList/trainerList.do";
	}
	

	//이미지를 출력해줄 메서드
	@RequestMapping("/trainerList/photoOutPut.do")
	public ModelAndView outPutImage(HttpSession session) {

		//세션에서 현재 로그인한 회원의 정보를 가져옴
		MemberVO user = (MemberVO)session.getAttribute("user");

		//읽어온 정보를 로그를 통해 보여줌
		if(log.isDebugEnabled()) {
			log.debug("<<<프로필과 자기소개 변경 페이지에서 세션정보>>> : " + user);
		}

		//로그인한 회원의 테이블 정보를 가저옴
		TlBoardVO vo = tlBoardService.selectTrainerDetail(user.getMem_num());

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", vo.getMem_pic());
		mav.addObject("filename", vo.getMem_picName());

		return mav;
	}

	//트레이닝을 신청한 일반회원의 프로필 사진을 출력해줄 메서드
	@RequestMapping("/trainerList/memberOfpic.do")
	public ModelAndView memberOfpic(@RequestParam int mem_num) {
		
		//로그인한 일반회원의 mem_num으로 회원정보 전부 다 가져오기
		TlBoardVO vo= tlBoardService.selectMemberDetail(mem_num);
		
		if(log.isDebugEnabled()) {log.debug("<<<로그인한 일반회원의 정보를 쿼리문에서 가져옴>>> :" + vo);}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", vo.getMem_pic());
		mav.addObject("filename", vo.getMem_picName());
		
		
		return mav;
	}
	
	//누구나 볼 수 있음
	//이미지를 출력해줄 메서드
	@RequestMapping("/trainerList/trainerImage.do")
	public ModelAndView outPutImage2(@RequestParam int mem_num) {

		//로그인한 회원의 테이블 정보를 가저옴
		TlBoardVO vo = tlBoardService.selectTrainerDetail(mem_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", vo.getMem_pic());
		mav.addObject("filename", vo.getMem_picName());

		return mav;
	}
	
	//매칭신청온거 리스트 뽑는 메서드
	@RequestMapping("/trainerList/matchingList.do")
	public ModelAndView matchingList(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
									 HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		Map<String,Object> map = new HashMap<String,Object>();
		
		//현재 페이지에는 트레이너 밖에 들어올 수 없음 즉 여기서 mem_num은 트레이너꺼임
		//로그인한 아이디의 mem_num 구하기
		MemberVO memberVO = (MemberVO)session.getAttribute("user");
		//map에 mem_num 넣기
		map.put("mem_num", memberVO.getMem_num());
		
		//받은 매칭 신청의 개수
		int count = tlBoardService.matchingCount(memberVO.getMem_num());
		if(log.isDebugEnabled()) { log.debug("<<검색된 트레이닝 신청 개수>> : " + count); }
		
		//paging 처리
		PagingUtil page = new PagingUtil(currentPage,count,10,10,"matchingList.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<TlBoardVO> list = null;
		if(count > 0) {
			list = tlBoardService.matchingList(map);
			if(log.isDebugEnabled()) { log.debug("<<검색된 트레이닝 신청 리스트>> : " + list); }
		}
		mav.addObject("result",1);
		mav.addObject("list", list);
		mav.addObject("count", count);
		mav.addObject("pagingHtml", page.getPagingHtml());
		mav.setViewName("matchingList");
		
		return mav;
		
	}
	
	//매칭신청 거절을 눌렀을 경우 작동할 메서드
	@RequestMapping("/trainerList/matchingCancle.do")
	public String matchingCancle(@RequestParam int mem_num, HttpSession session) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		//알림추가
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setWriter_memnum(mem_num);
		noticeVO.setReply_mem_num(user.getMem_num());
		noticeVO.setBoard_comment("거절 완료");
		noticeVO.setNotice_comment("트레이너 신청을 거절하였습니다.");
		noticeVO.setReturn_url("0"); 
		noticeVO.setWriter_board("-");
		noticeService.insertNoticeVO(noticeVO);  
		
		tlBoardService.deleteMatchingCancle(mem_num);
		
		
		return "redirect:/trainerList/matchingList.do";
	}

	//매칭신청 수락을 눌렀을 경우 작동할 메서드
	@RequestMapping("/trainerList/matchingOk.do")
	public String matchingOk(@RequestParam int mem_num, HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		/*
		 * 1.현재 로그인중인 트레이너의 mem_num을 가져온다
		 * 2.매개변수로 받은 mem_num은 매칭신청한 일반회원의 mem_num
		 * 3.member_detail테이블에서 t_num에 트레이너의 mem_num을 업데이트
		 * 4.training 테이블 속성들 (insert 해줘야함)
		 *     training_num(시퀀스) 
		 *     mem_num(로그인중인 트레이너의 mem_num)
		 *     traing_to (트레이닝 받을 일반회원의 mem_num)
		 *  
		 * 5.follow 관계맺기
		 * 6.매칭테이블에 매칭성공한 일반회원 찾아 신청 내역 [제거or변경]한 후에 알림보냄? 
		 */
		
		//현재 로그인 중인 트레이너의 mem_num을 얻기위해 세션에서 가져옴
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(log.isDebugEnabled()) { log.debug("<<세션에로그인한 트레이너 정보 확인>> : " + user); }
		
		map.put("t_num", user.getMem_num());
		map.put("mem_num", mem_num);
		
		if(log.isDebugEnabled()) { log.debug("<<map에 잘 들어갔는지 확인>> : " + map); }

		//t_num 변경헤줄 메서드 호출					
		tlBoardService.updateTNum(map);
		
		//training테이블에 insert해줄 메서드					  
		tlBoardService.insertTrainingTable(map);
		
		//팔로우 테이블에 데이터 추가
		// mem_num 일반회원 mem_num / user.getMem_num() 로그인한 트레이너회원의 mem_num
		
		FeedVO feedVO1 = new FeedVO(); //멤버 정보 저장용
		FeedVO feedVO2 = new FeedVO(); //트레이너 정보 저장용
		
		//follow_from : mem_num / follow_to : user.getMem_num()
		//일반회원이 트레이너한테 팔로우 신청할 경우
		feedVO1.setMem_num(mem_num);
		feedVO1.setFollower_to(user.getMem_num());
		
		//follow_from : user.getMem_num() / follow_to : mem_num
		//트레이너가 일반회원한테 팔로우 신청할 경우
		feedVO2.setMem_num(user.getMem_num());
		feedVO2.setFollower_to(mem_num);
		
		feedService.insertFollow(feedVO1);
		feedService.insertFollow(feedVO2);
		
		
		//알림 to 회원 
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setWriter_memnum(mem_num);
		noticeVO.setReply_mem_num(user.getMem_num());
		noticeVO.setBoard_comment("수락 완료");
		noticeVO.setNotice_comment("트레이너 신청을 수락하였습니다.");
		noticeVO.setReturn_url("0");
		noticeVO.setWriter_board("-");
		noticeService.insertNoticeVO(noticeVO);  
		
		//매칭테이블에서 삭제
		tlBoardService.deleteMatchingCancle(mem_num);
			
		return "redirect:/trainerList/matchingList.do"; 
	}
	
	//관계 끊기
	@RequestMapping("trainerList/trainingDelete.do")
	public String trainingDelete(HttpSession session, Model model) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(user.getT_num() != 0) { //연결된 트레이너가 있는 경우
			
			//팔로우 테이블에 데이터 삭제
			// user.getMem_num 일반회원 mem_num / user.getT_num() 트레이너회원의 mem_num
			
			FeedVO feedVO1 = new FeedVO(); //멤버 정보 저장용
			FeedVO feedVO2 = new FeedVO(); //트레이너 정보 저장용
			
			//follow_from : mem_num / follow_to : user.getMem_num()
			//일반회원이 트레이너한테 팔로우 신청할 경우
			feedVO1.setMem_num(user.getT_num());
			feedVO1.setFollower_to(user.getMem_num());
			
			//follow_from : user.getMem_num() / follow_to : mem_num
			//트레이너가 일반회원한테 팔로우 신청할 경우
			feedVO2.setMem_num(user.getMem_num());
			feedVO2.setFollower_to(user.getT_num()); 
			
			feedService.deleteFollow(feedVO1);
			feedService.deleteFollow(feedVO2);
			
			//매칭테이블에서 삭제
			tlBoardService.deleteTnum(user.getMem_num());
			tlBoardService.deleteTraining(user.getMem_num());
			
			
			//result 페이지 셋팅
			model.addAttribute("message", "트레이닝 관계 해제가 완료되었습니다.");
			model.addAttribute("url", "/MyFirstTrainer/member/myPage.do");
			
			return "common/result";
		} else {
			//result 페이지 셋팅
			model.addAttribute("message", "현재 회원님과 매칭된 트레이너가 없습니다.");
			model.addAttribute("url", "/MyFirstTrainer/member/myPage.do");
			
			return "common/result";
		}
		
	}


}
