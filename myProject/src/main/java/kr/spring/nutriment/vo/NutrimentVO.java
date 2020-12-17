package kr.spring.nutriment.vo;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;

public class NutrimentVO {
	
	private int food_num; //음식 넘버(PK)
	@NotEmpty
	private String food_name; //음식 이름
	@Range(min=0,max=1000)
	private int food_g; //음식 중량
	@Range(min=0,max=10000)
	private int food_kcal; //칼로리
	@Range(min=0,max=1000)
	private int food_fat; //총지방
	@Range(min=0,max=1000)
	private int food_col; //콜레스테롤
	@Range(min=0,max=1000)
	private int food_na; //나트륨
	@Range(min=0,max=1000)
	private int food_carb; //총 탄수량
	@Range(min=0,max=1000)
	private int food_df; //식이섬유
	@Range(min=0,max=1000)
	private int food_sugars; // 당
	@Range(min=0,max=1000)
	private int food_protein; //단백질
	
	
	
	public int getFood_num() {
		return food_num;
	}
	public void setFood_num(int food_num) {
		this.food_num = food_num;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public int getFood_g() {
		return food_g;
	}
	public void setFood_g(int food_g) {
		this.food_g = food_g;
	}
	public int getFood_kcal() {
		return food_kcal;
	}
	public void setFood_kcal(int food_kcal) {
		this.food_kcal = food_kcal;
	}
	public int getFood_fat() {
		return food_fat;
	}
	public void setFood_fat(int food_fat) {
		this.food_fat = food_fat;
	}
	public int getFood_col() {
		return food_col;
	}
	public void setFood_col(int food_col) {
		this.food_col = food_col;
	}
	public int getFood_na() {
		return food_na;
	}
	public void setFood_na(int food_na) {
		this.food_na = food_na;
	}
	public int getFood_carb() {
		return food_carb;
	}
	public void setFood_carb(int food_carb) {
		this.food_carb = food_carb;
	}
	public int getFood_df() {
		return food_df;
	}
	public void setFood_df(int food_df) {
		this.food_df = food_df;
	}
	public int getFood_sugars() {
		return food_sugars;
	}
	public void setFood_sugars(int food_sugars) {
		this.food_sugars = food_sugars;
	}
	public int getFood_protein() {
		return food_protein;
	}
	public void setFood_protein(int food_protein) {
		this.food_protein = food_protein;
	}
	@Override
	public String toString() {
		return "NutrimentVO [food_num=" + food_num + ", food_name=" + food_name + ", food_g=" + food_g + ", food_kcal="
				+ food_kcal + ", food_fat=" + food_fat + ", food_col=" + food_col + ", food_na=" + food_na
				+ ", food_carb=" + food_carb + ", food_df=" + food_df + ", food_sugars=" + food_sugars
				+ ", food_protein=" + food_protein + "]";
	}
}
