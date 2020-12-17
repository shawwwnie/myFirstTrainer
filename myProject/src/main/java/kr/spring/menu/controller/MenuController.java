package kr.spring.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {

	//메인 헤더 메인 메뉴(5가지) 연결하는 controller
	
	//홈트레이닝

	@RequestMapping("/board/homeWorkout.do")
	public ModelAndView processWorkout(){

		return null;
	}

	//식단 버튼 - > 식단 가이드 페이지 호출
	@RequestMapping("/board/nutriment.do")
	public String getNutrimentGuide(){
		
		return "board/nutriment/guide";
	}

	//트레이닝 다이어리 -> 피드 페이지 호출
	@RequestMapping("/board/feed.do")
	public ModelAndView getFeed(){
		
		return null;
	}

	//게시판 -> 클릭시 팁게시판 호출?
	@RequestMapping("/board/boardTrainerList.do")
	public ModelAndView getTBoardList(){

		return null;
	}

	//트레이너
	@RequestMapping("/board/trainerList.do")
	public ModelAndView getTrainerList(){

		return null;
	}
}
