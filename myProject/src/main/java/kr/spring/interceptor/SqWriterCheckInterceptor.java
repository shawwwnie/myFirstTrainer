package kr.spring.interceptor;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.spring.board.sysqna.service.SysQnABoardService;
import kr.spring.board.sysqna.vo.SysQnABoardVO;
import kr.spring.member.vo.MemberVO;

public class SqWriterCheckInterceptor extends HandlerInterceptorAdapter {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private SysQnABoardService sysQnABoardService;
	 
	@Override
	public boolean preHandle(HttpServletRequest request,
			                 HttpServletResponse response,
			                 Object handler)throws Exception{
		
		if(log.isDebugEnabled()) {
			log.debug("<<로그인 아이디와 작성자 아이디 일치 여부 체크>>");
		}
		
		//작성자의 회원 번호 구하기
		int sq_num = Integer.parseInt(request.getParameter("sq_num"));
		SysQnABoardVO board = sysQnABoardService.selectBoardSysqna(sq_num);
		
		//로그인 회원 번호 구하기
		HttpSession session = request.getSession();
		MemberVO user = (MemberVO)session.getAttribute("user");
		
		if(log.isDebugEnabled()) {
			log.debug("<<로그인 회원 번호>> " + user.getMem_num());
			log.debug("<<작성자 회원 번호>> " + board.getMem_num());
		}
		
		//로그인 회원 번호와 작성자 회원 번호 일치 여부 체크
		if(user==null || user.getMem_num() != board.getMem_num()) {
			if(log.isDebugEnabled()) {
				log.debug("<<로그인 아이디와 작성자 아이디 불일치>>");
			}
		
			//로그인 아이디와 작성자 아이디가 불일치할 때는 경고 페이지 호출
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher(
							"/WEB-INF/views/common/notice.jsp");
			dispatcher.forward(request, response);
			
			return false;			
		}
		if(log.isDebugEnabled()) {
			log.debug("<<로그인 아이디와 작성자 아이디 일치>>");
		}
		
		return true;
	}
}
