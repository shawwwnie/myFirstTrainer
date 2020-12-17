<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main-style">
	<h2>트레이닝영상 정보 수정</h2>
	<form:form commandName="hwBoardVO" action="hwBoardWrite.do"
	                        enctype="multipart/form-data">
	    <form:hidden path="hw_num"/>   
		<ul>
			<li>
				<label for="hw_title">제목</label>
				<form:input path="hw_title"/>
			</li>
			<li>
				<label for="hw_part">부위</label>
				<form:input path="hw_part"/>
			</li>
			<li>
				<label for="hw_kcal">소모 칼로리</label>
				<input type="number" id="hw_kcal" name="hw_kcal" value="${hwBoardVO.hw_kcal}"/>
			</li>
			<li>
				<label for="hw_link">링크</label>
				<form:textarea path="hw_link"/>
			</li>
		</ul>	
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록"
			       onclick="location.href='hwList.do'">
		</div>
	</form:form>
</div>
