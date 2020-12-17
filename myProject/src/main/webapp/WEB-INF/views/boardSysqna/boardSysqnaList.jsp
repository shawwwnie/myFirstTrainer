<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout_board.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$(".selectbox-options").on("click", ".init", function() {
		    $(this).closest("ul").children('li:not(.init)').slideToggle();
		    if($('.selectbox-option').hasClass('opened')){
		    	$('.selectbox-options').removeClass('opened');
		    }else{
		    	$('.selectbox-options').addClass('opened');
		    }
		});

 		var allOptions = $(".selectbox-options").children('li:not(.init)');
		$(".selectbox-options").on("click", "li:not(.init)", function() {
 		    allOptions.removeClass('selected');
		    $(this).addClass('selected'); 
		    $(".selectbox-options").children('.init').html($(this).html());
 		    allOptions.slideUp(); 
		});
 


	});
</script>
<div class="page-main-style">
	<h2>시스템 문의 게시판</h2>
	<form action="list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
						<option value="title">제목</option>
						<option value="id">ID</option>
						<option value="content">내용</option>
						<option value="all">전체</option>
				</select>
			</li>
			<li><input type="text" name="keyword" id="keyword"></li>
			<li><input type="submit" value="찾기" style="width:60px;">
				<input type="button" value="목록" onclick="location.href='list.do'" style="width:60px;">
			</li>
		</ul>
	</form>
	<div class="align-right">
		<c:if test="${empty user}">
			<input type="button" value="글쓰기" onclick="location.href='write.do'" disabled="disabled">
		</c:if>
		<c:if test="${!empty user}">
			<input type="button" value="글쓰기" onclick="location.href='write.do'">
		</c:if>
	</div>

	<c:if test="${count == 0}">
		<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	
	<c:if test="${count > 0}">
		<table>
			<tr>
				<th>번호</th>
				<th width="400">제목</th>
				<th>작성자</th>
				<th>최근수정일</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="boardSysQna" items="${list}">
				<tr>
					<td>${boardSysQna.asc_rnum}</td>
					<td style="color:grey;">
					
						<c:if test="${user.mem_num != boardSysQna.mem_num && user.mem_auth > 0}">
							<img src="${pageContext.request.contextPath}/resources/images/board/lock.png" style="max-width:16px;">비밀글 입니다
						</c:if>
						
						<c:if test="${user.mem_num == boardSysQna.mem_num}">
							<b><a href="detail.do?sq_num=${boardSysQna.sq_num}">
								${boardSysQna.sq_title} 
							</a></b>
						</c:if>
						
						<c:if test="${user.mem_auth == 0}">
							<b><a href="detail.do?sq_num=${boardSysQna.sq_num}">
								${boardSysQna.sq_title} 
							</a></b>
						</c:if>
						
						<c:if test="${boardSysQna.commentNum > 0}">
							<b>[${boardSysQna.commentNum}]</b>
						</c:if>
						
					</td>
					<td>${boardSysQna.mem_id}</td>
					<td>${boardSysQna.sq_modify_date}</td>
					<td>${boardSysQna.sq_hit}</td>
				</tr>
			</c:forEach>
		</table>
		
		<div class="boardListBelowPart">
			<div class="align-center">${pagingHtml}</div>
			<div class="boardPaging-selectbox">
				<ul class="selectbox-options">
				    <li class="init">${rowCount}개씩 보기</li>
				    <li><a href="list.do?keyfield=${keyfield}&keyword=${keyword}&pageNum=${pageNum}&rowCount=10">10개씩 보기</a></li>
				    <li><a href="list.do?keyfield=${keyfield}&keyword=${keyword}&pageNum=${pageNum}&rowCount=20">20개씩 보기</a></li>
				    <li><a href="list.do?keyfield=${keyfield}&keyword=${keyword}&pageNum=${pageNum}&rowCount=30">30개씩 보기</a></li>
				    <li><a href="list.do?keyfield=${keyfield}&keyword=${keyword}&pageNum=${pageNum}&rowCount=50">50개씩 보기</a></li>
				</ul>
			</div>
		</div>
	</c:if>
</div>

<!-- https://jsfiddle.net/amitabhaghosh197/f69o462r/ -->