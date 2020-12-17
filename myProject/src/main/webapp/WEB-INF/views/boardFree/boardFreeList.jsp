<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout_board.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("div.boardPaging-selectbox > ul").on("click", ".init", function() {
		    $(this).closest("div.boardPaging-selectbox > ul").children('li:not(.init)').slideToggle();
		    if($('.selectbox-option').hasClass('opened')){
		    	$('.selectbox-options').removeClass('opened');
		    }else{
		    	$('.selectbox-options').addClass('opened');
		    }
		});
		
		var allOptions = $("div.boardPaging-selectbox > ul").children('li:not(.init)');
		$("div.boardPaging-selectbox > ul").on("click", "li:not(.init)", function() {
		    allOptions.removeClass('selected');
		    $(this).addClass('selected');
		    $("div.boardPaging-selectbox > ul").children('.init').html($(this).html());
		    allOptions.slideUp();
		});

	});
</script>
<div class="page-main-style">
	<h2>자유 게시판 목록</h2>
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
		<!-- 관리자인 경우 신고글만 보기 추가 -->
		<c:if test="${user.mem_auth == 0}">
			<ul>
				<li><a href="list.do?alarm=0">전체보기	</a></li>
				<li><a href="list.do?alarm=1">신고 접수된 글만 보기</a></li>
			</ul>
		</c:if>
		
		<table class="table_freeboard">
			<tr>
				<th></th>
				<th width="400">제목</th>
				<th>작성자</th>
				<th>최근수정일</th>
				
				<th>조회수</th>
			</tr>
			<c:forEach var="boardFree" items="${list}">
				<tr>
					<td><img src="${pageContext.request.contextPath}/resources/images/board/dot.png" style="width:5px; position:relative; left:6px;"></td>
					
					<td style="text-align:left;">
					
						<%-- 신고가 10개 미만 글 --%>
						<c:if test="${boardFree.alarm < 10}">
							<a href="detail.do?free_num=${boardFree.free_num}">${boardFree.free_title} 
		
								<c:if test="${!empty boardFree.free_filename}">
									<img src="${pageContext.request.contextPath}/resources/images/board/clip.png" style="width:12px;">								
								</c:if>
								
								<c:if test="${boardFree.commentNum>0}">
									<b>[${boardFree.commentNum}]</b>
								</c:if>
								<!-- 관리자인 경우 신고 수 보이게 -->
								<c:if test="${user.mem_auth == 0 && boardFree.alarm > 0}">
									<b>[신고:${boardFree.alarm}]</b>
								</c:if>	
							</a>
						</c:if>
						
						<%-- 신고가 10개 이상 일반회원 트레이너 회원에게 보이는 글 --%>
						 <c:if test="${boardFree.alarm > 9 && user.mem_auth > 0}">
							<span style="color:grey;"><b>신고가 10번 이상 접수된 글입니다.</b></span>				
						</c:if>
						
						<%-- 신고가 10개 이상 관리자에게 보이는 글 --%>
						<c:if test="${boardFree.alarm > 9 && user.mem_auth == 0}">
							<a href="detail.do?free_num=${boardFree.free_num}">${boardFree.free_title} 
		
								<c:if test="${!empty boardFree.free_filename}">
									<img src="${pageContext.request.contextPath}/resources/images/board/clip.png" style="width:12px;">								
								</c:if>								
								<c:if test="${boardFree.commentNum>0}">
									<b>[${boardFree.commentNum}]</b>
								</c:if>
								<b style="color:red">[신고:${boardFree.alarm}]</b>
							</a>			
						</c:if>
					
					</td>
					
					<td>${boardFree.mem_id}</td>
					<td>${boardFree.free_modify_date}</td>
					<td>${boardFree.free_hit}</td>
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