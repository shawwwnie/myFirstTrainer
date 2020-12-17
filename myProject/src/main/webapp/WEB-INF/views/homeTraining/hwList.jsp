<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout_hwList.css">
<style>
.search_btn{
	height: 30px;
	width: 30px;
	margin-left: 5px;
	background-image: url('../resources/images/feed/Btn_search.png');
	border: none;
	margin-bottom: -10px;
}
.search_btn:hover{
	transform: scale(0.96);
	cursor:pointer;
} 
</style>
<div class="page-main-style">
	<h2 class="hw_title">홈 트레이닝 추천 영상!</h2>
	
	<form action="hwList.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield" class="search_type">
					<option class="search_types" value="hw_title">제목</option>
					<option class="search_types" value="hw_part">부위</option>
					<option class="search_types" value="all">전체</option>
				</select>
				<input type="text" class="search_field" name="keyword" id="keyword">
				<input type="submit" class="search_btn" value="" > 
				<input type="button" class="list_btn" value="전체 목록 보기" onclick="location.href='hwList.do'">
			</li>
		</ul>
	</form>
	
	<div class="align-right">
		<c:if test="${user.mem_auth == 3}"> <!-- 관리자 모드로 들어왔을 경우에 버튼 노출 mem_auth == 0(관리자) -->
		<input type="button" value="글쓰기" 
		       onclick="location.href='hwBoardWrite.do'">
		</c:if>
	</div>
	
	<c:if test="${count == 0}">
		<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}"> 
		<div class="class-boxes">
			<c:forEach var="hwBoard" items="${list}">
					<div class="gallery-box-container">
						<a href="hwDetail.do?hw_num=${hwBoard.hw_num}" class="gallery-box">
							<span class="gallery-box__img-container">
								<img width="460" height="300" src="https://img.youtube.com/vi/${hwBoard.hw_link}/0.jpg">
							</span>
							<span class="gallery-box__text-wrapper">
								<span class="gallery-box__text">
									${hwBoard.hw_title}
								</span>
							</span>
						</a>
					</div>
			</c:forEach>
		</div>
		<c:if test="${count == 1}"></c:if>
		<c:if test="${count > 1}"><div class="align-center">${pagingHtml}</div></c:if>
	</c:if>
</div>
