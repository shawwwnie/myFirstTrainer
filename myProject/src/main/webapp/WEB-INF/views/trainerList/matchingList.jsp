<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
table{
	width:30%;
	margin:5px auto;
	border:1px solid #000;
	border-collapse:collapse;
	text-align:center;
	box-sizing: border-box;
	border-radious: 80px;
	
}
table td, table th{
	border:3px solid #0de9df;
	padding:5px;
}
</style>

	<c:if test="${count == 0}">
		<div>요청받은 트레이너 매칭 신청내역이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
		<table>
			<tr>
				<th colspan="3" style="font-size:20px;">트레이닝 신청 내역</th>
				
			</tr>
			<c:forEach var="list" items="${list}">
				<tr>
					<td colspan="3">
							<c:if test="${empty list.mem_picName}">
							<img src="../resources/images/cameraImage.jpg" width="100" height="100">
							</c:if>
							<c:if test="${!empty list.mem_picName}">
								<img src="${pageContext.request.contextPath}/trainerList/memberOfpic.do?mem_num=${list.mem_num}" 
								width="200" height="200">
							</c:if>
							<div style="text-align:center;">
							<a href="${pageContext.request.contextPath}/boardFeed/otherFeedList.do?mem_num=${list.mem_num}">
							${list.mat_id}</a> 님이 트레이닝 신청을 하셨습니다.
							</div>
					</td>
				</tr>
				<tr>
					<td><a href="matchingOk.do?mem_num=${list.mat_from}">수락</a></td>
					<td><a href="matchingCancle.do?mem_num=${list.mat_from}">거절</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
		
<div>
	<div id="paging" class="align-center">${pagingHtml}</div>
</div>