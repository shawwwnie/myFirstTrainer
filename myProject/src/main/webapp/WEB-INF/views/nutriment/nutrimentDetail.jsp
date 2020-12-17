<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nutrimentCal.css">
<div id="nutrimentDetail">
	<div id="bg1">
		<div id="bg2">
		<input type="hidden" value="${NutrimentVO.food_num}"> 	
			<table>
					<tr>
						<td>음식 이름</td>
						<td>${NutrimentVO.food_name}</td>
					</tr>
					<tr>
						<td>제공량</td>
						<td>${NutrimentVO.food_g} g</td>
					</tr>
					<tr>
						<td>제공량 당 Kcal</td>
						<td>${NutrimentVO.food_kcal} kcal</td>
					</tr>
					<tr>
						<td>총 탄수량</td>
						<td>${NutrimentVO.food_carb} g</td>
					</tr>
					<tr>
						<td>식이섬유</td>
						<td>${NutrimentVO.food_df} g</td>
					</tr>
					<tr>
						<td>당</td>
						<td>${NutrimentVO.food_sugars} g</td>
					</tr>
					<tr>
						<td>단백질</td>
						<td>${NutrimentVO.food_protein} g</td>
					</tr>
					<tr>
						<td>총 지방</td>
						<td>${NutrimentVO.food_fat} g</td>
					</tr>
					<tr>
						<td>콜레스테롤</td>
						<td>${NutrimentVO.food_col} mg</td>
					</tr>
					<tr>
						<td>나트륨</td>
						<td>${NutrimentVO.food_na} mg</td>
					</tr>
			</table>
		
			<div id="button">
				<input type="button" value="목록으로" onclick="history.back(-1);">
				<c:if test="${auth == 0}">
					<div>
						<input type="button" value="수정" id="btn_modify" onclick="location.href='nutrimentUpdate.do?food_num=${NutrimentVO.food_num}'"/>
					</div>
					<div>
						<input type="button" value="삭제" id="btn_delete"/>
					</div>
					<script>
					var btn_delete = document.getElementById('btn_delete');
					btn_delete.onclick=function(){
						var choice = window.confirm('삭제하시겠습니까?');
						if(choice){
							location.href='nutrimentDelete.do?food_num=${NutrimentVO.food_num}';
						}
					};
					</script>
				</c:if>
			</div>
		</div>
	</div>
</div>