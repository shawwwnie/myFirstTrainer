package kr.spring.board.hw.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.hw.vo.HwBoardVO;

public interface HwBoardMapper {

	//ListView
	public List<HwBoardVO> selectHwList(Map<String, Object> map);

	//페이지 넘버
	public int selectHwRowCount(Map<String, Object> map);

	//글등록
	@Insert("INSERT INTO hwboard (hw_num,hw_title,hw_link,hw_part,hw_kcal) values(hwboard_seq.nextval, #{hw_title},#{hw_link},#{hw_part},#{hw_kcal})")
	public void insertBoard(HwBoardVO hwBoard_);

	//상세페이지
	@Select("SELECT * FROM hwboard WHERE hw_num=#{hw_num}")
	public HwBoardVO selectHwBoard(Integer hw_num);

	//글 수정
	public void updateHwBoard(HwBoardVO hwBoard);

	//글 삭제
	@Delete("DELETE FROM hwboard WHERE hw_num=#{hw_num}")
	public void deleteHwBoard(Integer hw_num);

}  
