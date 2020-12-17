package kr.spring.nutriment.service;

import java.util.List;
import java.util.Map;

import kr.spring.nutriment.vo.NutrimentVO;

public interface NutrimentService {
	
	public List<NutrimentVO> selectNutriment(Map<String,Object> map);
	public int count(Map<String,Object> map);
	public void insertNutriment(NutrimentVO vo);
	public void deleteNutriment(int food_num);
	public void updateNutriment(NutrimentVO nutrimentVO);
	public NutrimentVO selectNutriDetail(int food_num);

}
