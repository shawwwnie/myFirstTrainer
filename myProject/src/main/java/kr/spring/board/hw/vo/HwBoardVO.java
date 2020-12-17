package kr.spring.board.hw.vo;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;

public class HwBoardVO {
	
	private int hw_num;		//글번호 
	@NotEmpty
	private String hw_title;		//글제목
	@NotEmpty
	private String hw_link;		//유튜브링크
	@NotEmpty
	private String hw_part;		//부위 
	@Range(min=80,max=300)
	private int hw_kcal;		//소모칼로리
	
	public int getHw_num() {
		return hw_num;
	}
	public void setHw_num(int hw_num) {
		this.hw_num = hw_num;
	}
	public String getHw_title() {
		return hw_title;
	}
	public void setHw_title(String hw_title) {
		this.hw_title = hw_title;
	}
	public String getHw_link() {
		return hw_link;
	}
	public void setHw_link(String hw_link) {
		this.hw_link = hw_link;
	}
	public String getHw_part() {
		return hw_part;
	}
	public void setHw_part(String hw_part) {
		this.hw_part = hw_part;
	}
	public int getHw_kcal() {
		return hw_kcal;
	}
	public void setHw_kcal(int hw_kcal) {
		this.hw_kcal = hw_kcal;
	}
	
	@Override
	public String toString() {
		return "HwBoardVO [hw_num=" + hw_num + ", hw_title=" + hw_title + ", hw_link=" + hw_link + ", hw_part="
				+ hw_part + ", hw_kcal=" + hw_kcal + "]";
	}
	
}
