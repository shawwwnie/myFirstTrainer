<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nutrimentInsert.css">
<div id="nutriInsertForm">
	<h2>영양 성분 수정</h2>
	<div id="main-div">
		<div id="sub-div">
			<form:form commandName="nutrimentVO" action="nutrimemtUpdate.do">
			<form:hidden path="food_num"/>
				<ul>
					<li>
						<label for=food_name>식품 이름</label><br>
						<form:input path="food_name" placeholder="${nutrimenVO.food_name}"/><br>
						<form:errors path="food_name" cssClass="error-color"/>
					</li>
					<li>
						<label for="food_g">단위 그램(g)</label><br>
						<form:input path="food_g" placeholder="${nutrimenVO.food_g}"/>g<br>
						<form:errors path="food_g" cssClass="error-color"/>
					</li>
					<li>
						<label for="food_kcal">총 열량</label><br>
						<form:input path="food_kcal"/>kcal<br>
						<form:errors path="food_kcal" cssClass="error-color"/>
					</li>
					<li>
						<label for="food_fat">총 지방</label><br>
						<form:input path="food_fat" placeholder="${nutrimenVO.food_fat}"/>g<br>
						<form:errors path="food_fat" cssClass="error-color"/>
					</li>
					<li>
						<label for="food_col">콜레스테롤</label><br>
						<form:input path="food_col" placeholder="${nutrimenVO.food_col}"/>mg<br>
						<form:errors path="food_col" cssClass="error-color"/>
					</li>
					<li>
						<label for="food_na">나트륨 </label><br>
						<form:input path="food_na" placeholder="${nutrimenVO.food_na}"/>mg<br>
						<form:errors path="food_na" cssClass="error-color"/>
					</li>
					<li>
						<label for="food_carb">총 탄수화물</label><br>
						<form:input path="food_carb" placeholder="${nutrimenVO.food_carb}"/>g<br>
						<form:errors path="food_carb" cssClass="error-color"/>
					</li>
					<li>
						<label for="food_df">식이섬유</label><br>
						<form:input path="food_df" placeholder="${nutrimenVO.food_df}"/>g<br>
						<form:errors path="food_df" cssClass="error-color"/>
					</li>
					<li>
						<label for="food_sugars">당분</label><br>
						<form:input path="food_sugars" placeholder="${nutrimenVO.food_sugars}"/>g
						<form:errors path="food_sugars" cssClass="error-color"/>
					</li>
					<li>
						<label for="food_protein">단백질</label><br>
						<form:input path="food_protein" placeholder="${nutrimenVO.food_protein}"/>g<br>
						<form:errors path="food_protein" cssClass="error-color"/>
					</li>
		
				</ul>
				<div class="align-center">
					<input type="submit" value="등록">
					<input type="button" value="목록" onclick="location.href='nutriList.do'">
				</div>
			</form:form>
		</div>
	</div>
</div>