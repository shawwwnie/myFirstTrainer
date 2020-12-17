<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nutrimentCal.css">    
<div id="nutriCalResult">
	<div id="bg1">
		<div id="bg2">
			<c:if test="${!empty type}">
				<div id="type-image">
					<c:if test="${type.equals('fast')}">
						<%-- <img alt="" src="${pageContext.request.contextPath}/resources/images/type/fast.png"> --%>
						<div id="fast">
							<div id="carb">탄수화물 30%</div><div id="protein">단백질 50%</div><div id="fat">지방20%</div>
						</div>
					</c:if> 
					<c:if test="${type.equals('normal')}">
						<%-- <img alt="" src="${pageContext.request.contextPath}/resources/images/type/normal.png"> --%>
						<div id="normal">
							<div id="carb"></div><div id="protein"></div><div id="fat"></div>
						</div>
					</c:if> 
					<c:if test="${type.equals('healthy')}">
						<%-- <img alt="" src="${pageContext.request.contextPath}/resources/images/type/healthy.png"> --%>
						<div id="healthy">
							<div id="carb"></div><div id="protein"></div><div id="fat"></div>
						</div>
					</c:if> 
					<c:if test="${type.equals('low')}">
						<%-- <img alt="" src="${pageContext.request.contextPath}/resources/images/type/low.png"> --%>
						<div id="low">
							<div id="carb"></div><div id="protein"></div><div id="fat"></div>
						</div>
					</c:if> 
					<c:if test="${type.equals('high')}">
						<%-- <img alt="" src="${pageContext.request.contextPath}/resources/images/type/high.png"> --%>
						<div id="high">
							<div id="carb"></div><div id="protein"></div><div id="fat"></div>
						</div>
					</c:if>
				</div>
				
				<div style="margin-left: 30px;">
					<p> 내 키와 몸무게에 따른 기초 대사량 : <label id="bmr"><u>${bmr}</u></label> <br>
					내 기초 대사량에 따른 일일 권장 섭취량 : <label id="bmr"><u>${dayCal}</u></label> <br>
					기초 대사량과 설정 목표에 따른 회원님의 일일 권장 탄단지는 <br>
					탄수화물 <label id="carb"><u>${carb}</u></label>g / 단백질 <label id="prot"><u>${protein}</u></label>g / 지방 <label id="fat"><u>${fat}</u></label>g 입니다.</p>
					<p> 본 수치는 추정치 이며, 권장 사항일 뿐,<br> 자세한 사항은 전문가와 상담하기를 권장합니다.</p>
			
				</div> 
			</c:if>
			<c:if test="${empty type}">
				<div style="margin-left: 30px;">
					<p> 
					나의 일일 권장 섭취량을 계산해 보세요!
					</p>
			
				</div> 
			</c:if>
			
		</div>
	</div>
</div>