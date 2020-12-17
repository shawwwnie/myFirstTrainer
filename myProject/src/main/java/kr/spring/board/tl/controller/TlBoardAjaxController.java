package kr.spring.board.tl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.board.notice.service.NoticeService;
import kr.spring.board.notice.vo.NoticeVO;
import kr.spring.board.tl.service.TlBoardService;
import kr.spring.board.tl.vo.TlBoardVO;
import kr.spring.comment.tl.vo.TlBoardCommentVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.PagingUtil;

@Controller
public class TlBoardAjaxController {
	
	private Logger log = Logger.getLogger(this.getClass());

	//트레이너 프로필 사진 변경은 trainer_detail테이블에 수정해야함
	@Resource
	private TlBoardService tlBoardService;
	
	//알림 서비스
	@Resource
	NoticeService noticeService;

	//프로필 사진 업데이트
	@RequestMapping("/trainerList/updatePic.do")
	@ResponseBody
	public Map<String,String> processProfile(TlBoardVO tlboardVO, HttpSession session){
		Map<String,String> map = new HashMap<String,String>();

		//로그인 되어있는지 아닌지 확인해야함
		MemberVO user = (MemberVO)session.getAttribute("user");

		if(user==null) {
			//로그인이 되지 않은 경우
			map.put("result", "logout");
		}else {
			//로그인이 된 경우
			tlboardVO.setMem_num(user.getMem_num());
			tlBoardService.updateTrainerProfile(tlboardVO);

			//이미지를 업로드한 후 세션에 저장된 회원 정보의 이미지 이름을 교체
			user.setMem_picName(tlboardVO.getMem_picName());	

			map.put("result", "success");
		}

		return map;
	}
	
	/****************************************댓글 서비스************************************************/
	//댓글 리스트
	@RequestMapping("/trainerList/trainer_comment.do")
	@ResponseBody
	public Map<String,Object> list(@RequestParam int tl_mem_num, @RequestParam(value="pageNum",defaultValue="1") int currentPage){
		//SQL문 ?에 들어갈 값 : int tl_mem_num
		
		List<TlBoardCommentVO> list = null;
		
		//SQL문 ?에 들어갈 값을 map에 담기
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tl_mem_num",tl_mem_num);
		
		//총 댓글의 갯수
		int count = tlBoardService.selectTlBoardCommentCount(map);
		if(log.isDebugEnabled()) { log.debug("<<검색된 댓글 갯수>> : " + count); }
		
		//paging 처리
		PagingUtil page = new PagingUtil(currentPage,count,10,10,"trainerListDetail.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		//모든 댓글 list에 담기
		list = tlBoardService.selectTlBoardCommentList(map);
		
		if(log.isDebugEnabled()) { log.debug("<<검색된 댓글>> : " + list);} 
		
		
		//ajax 처리를 위해 리턴해 줄 map
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("list", list);
		mapJson.put("count",count);
		mapJson.put("rowCount", 10);
		mapJson.put("pagingHtml", page.getPagingHtml());
		
		return mapJson;
	}
	
	
	//댓글 입력
	@RequestMapping("/trainerList/submit_tlcomment.do")
	@ResponseBody
	public Map<String,Object> process(@RequestParam String comment,@RequestParam int tl_mem_num,@RequestParam int writer_mem_num,HttpSession session){
		
		//글의 주인(트레이너 리스트 디테일 주인) : 트레이너(tl_mem_num)
		//댓글 다는 사람 : 로그인한 사람(session의 mem_num)
		
		/*//로그인한 사람의 정보 얻기 
		MemberVO memberVO = (MemberVO)session.getAttribute("user");
		int mem_num = memberVO.getMem_num();*/
		

		if(log.isDebugEnabled()) {log.debug("<<트레이너 상세정보 댓글등록>> 트레이너 번호 : " + tl_mem_num + "댓글 내용 :" + comment);}
		
		//댓글등록
		int writer_memNum = tl_mem_num; //알림 받을 사람 mem_num
		NoticeVO notice = new NoticeVO();
		notice.setWriter_memnum(writer_memNum); //글 쓴 사람 즉 트레이너 디테일 주인 트레이너
		notice.setReply_mem_num(writer_mem_num); //댓글 쓴 사람 즉 로그인한 유
		notice.setBoard_comment(comment);
		notice.setNotice_comment("내 트레이너 상세정보에 댓글을 등록했습니다.");
		notice.setReturn_url("0");
		notice.setWriter_board("-");
		noticeService.insertNoticeVO(notice);
		if(log.isDebugEnabled()) log.debug("<<알림전달>> :" + notice);
		
		//댓글 등록하기
		TlBoardCommentVO tlBoardCommentVO = new TlBoardCommentVO();
		tlBoardCommentVO.setTl_comment(comment);
		tlBoardCommentVO.setTl_mem_num(tl_mem_num);		
		tlBoardCommentVO.setWriter_mem_num(writer_mem_num);
				
		tlBoardService.insertTlBoardComment(tlBoardCommentVO);
		
		//ajax 처리를 위해 리턴해 줄 map
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("result", true);
		
		return mapJson; 
	}
		
	//댓글 수정
	@RequestMapping("/trainerList/update_tlcomment.do")
	@ResponseBody
	public Map<String,Object> updateComment(@RequestParam int tlc_num, @RequestParam String update_comment) {
		
		if(log.isDebugEnabled()) {log.debug("<<트레이너 상세 댓글 수정>> 댓글 번호 : " + tlc_num);}
		
		TlBoardCommentVO tlBoardCommentVO = tlBoardService.selectOneComment(tlc_num);
		tlBoardCommentVO.setTl_comment(update_comment);
		tlBoardService.updateTlBoardComment(tlBoardCommentVO);
		
		//ajax 처리를 위해 리턴해 줄 map
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("result", true);
		
		return mapJson;
	}
	
	//댓글 삭제
	@RequestMapping("/trainerList/delete_tlcomment.do")
	@ResponseBody
	public Map<String,Object> deleteComment(@RequestParam int tlc_num) {
		
		if(log.isDebugEnabled()) log.debug("<<트레이너 상세 댓글 삭제>> 댓글 번호 : " + tlc_num);
		
		tlBoardService.deleteTlBoardComment(tlc_num);
		
		//ajax 처리를 위해 리턴해 줄 map
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("result", true);
		
		return mapJson;
	}
	
	/****************************************댓글 서비스************************************************/
	
}











