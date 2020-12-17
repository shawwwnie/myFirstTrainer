package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
  
@Controller
public class MemberAjaxController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private MemberService memberService;

	@RequestMapping("/member/confirmMemberId.do")
	//ajax문자열을 읽을 수 있게 해줌
	@ResponseBody
	//일반회원 아이디 중복 체크해주는 메서드
	public Map<String,String> process(@RequestParam("id") String id){

		if(log.isDebugEnabled()) {
			log.debug("<<<일반회원 아이디 중복 체크>>> : " + id);
		}

		Map<String,String> map = new HashMap<String,String>();
		MemberVO member = memberService.selectCheckMember_detail(id);

		//member에 id가 있으면
		if(member!=null) {
			//아이디 중복
			map.put("result", "idDuplicated");
		}else {
			//아이디 미중복
			map.put("result", "idNotFound");
		}

		return map;
	}

	@RequestMapping("/member/confirmTrainerId.do")
	@ResponseBody
	//트레이너 아이디 중복 체크해주는 메서드
	public Map<String,String> process2(
			@RequestParam("id") String id){
		if(log.isDebugEnabled()) {
			log.debug("<<트레이너 아이디 중복 체크>> : " + id);
		}

		Map<String,String> map =
				new HashMap<String,String>();

		MemberVO member = memberService.selectCheckTrainer_detail(id);
		if(member!=null) {
			//아이디 중복
			map.put("result", "idDuplicated");
		}else {
			//아이디 미중복
			map.put("result", "idNotFound");
		}

		return map;
	}

	/*//프로필 사진 업데이트
	@RequestMapping("/member/updateMyPhoto.do")
	@ResponseBody
	public Map<String,String> processProfile(MemberVO memberVO, HttpSession session){
		Map<String,String> map = new HashMap<String,String>();

		//로그인 되어있는지 아닌지 확인해야함
		//인터셉터로 처리하면 
		MemberVO user = (MemberVO)session.getAttribute("user");

		if(user==null) {
			//로그인이 되지 않은 경우
			map.put("result", "logout");
		}else {
			//로그인이 된 경우
			memberVO.setMem_num(user.getMem_num());
			memberService.updateProfile(memberVO);

			//이미지를 업로드한 후 세션에 저장된 회원 정보의 이미지 이름을 교체
			user.setPhotoname(memberVO.getPhotoname());

			map.put("result", "success");
		}

		return map;
	}*/

}











