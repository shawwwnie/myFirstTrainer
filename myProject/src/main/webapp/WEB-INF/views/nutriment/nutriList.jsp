<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javaScript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>    
<script type="text/javascript">

	$(document).ready(function(){
		
		$('#nutrimentSearchForm').submit(function(){
			<%-- 유효성 체크 --%>
			if($('#foodName').val() == ''){
				
				alert('검색어를 입력해주세요.');
				$('#foodName').focus();
				
				return false;
			}

		});

	});
</script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nutrimentSearch.css">
<div id="nutriList">
	<div id="main-div">
		<div id="sub-div">
		
		<!-- -------------------------Search Bar---------------------------- -->
			<div>
				<form id="nutrimentSearchForm" action="nutriSearch.do">
					<c:if test="${auth == 0}">
						<div id="search-bar" style="margin-left:45px;">
					</c:if>
					<c:if test="${auth != 0 || empty auth}">
						<div id="search-bar" style="margin-left:90px;">
					</c:if>
						<ul>
							<li>
								<input type="text" id="foodName" name="foodName" placeholder="영양성분이 궁금한 식품의 이름을 검색해보세요!"/>
							</li>
						</ul>
						<div id="button">
							<input type="submit" value="검색">
							<c:if test="${auth == 0}">
								<div class="aling-right">
									<input type="button" value="추가" onclick="location.href='nutrimentInsertForm.do'"/>
								</div>
							</c:if>
						</div>
				</form>
			</div>
			<!-- -------------------------Search Bar---------------------------- -->
			
			
			<!-- -------------------------Result Table-------------------------- -->
			<div id="result">
				<div>	
					<ul id="result-ul">
						<c:forEach var="nutriment" items="${list}">
							<li><a href="nutrimentDetail.do?food_num=${nutriment.food_num}">${nutriment.food_name}</a></li>
							<hr style="border: dashed 1px #9c9c9c;">
						</c:forEach>
					</ul>
				</div>
				<div id="paging" class="align-center">${pagingHtml}</div>
			</div>
			<!-- -------------------------Result Table-------------------------- -->
			
		</div>	
	</div>
</div>