package kr.spring.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/main/main.do")
	public String getMain() {
		return "/main/main";
	}
	
	/*@RequestMapping("/homeTraining/hwList.do")
	public String getHometraining() {
		return "hwList";
	}*/
	
	@RequestMapping("/nutriment/nutrimentGuide.do")
	public String getNutrimentGuide() {
		return "nutrimentGuide";
	}
	
	/*@RequestMapping("")
	public String getTrainingDiary() {
	
		return "";
	}*/
	
	
	/*@RequestMapping("")
	public String geTrainer() {
		//return "";
	}*/
	
}





