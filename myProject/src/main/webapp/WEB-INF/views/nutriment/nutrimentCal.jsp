<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nutrimentCal.css">
<script type="text/javaScript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script> 
<script type="text/javascript">

	$(document).ready(function(){	
		
		$('#nutrimentCal').submit(function(){
			<%-- 유효성 체크 --%>
			if($('#height').val() == ''){
			
				alert('나의 키를 입력해주세요.');
				$('#height').focus();
	
				return false;
			}
			if($('#weight').val() == ''){
				
				alert('나의 체중을 입력해주세요.');
				$('#weight').focus();
	
				return false;
			}
			if($('#age').val() == ''){
				
				alert('나의 나이를 입력해주세요.');
				$('#age').focus();
	
				return false;
			}
	
		});
	
	});


</script>

<div id="nutriCal">
	<h2>일일 권장 영양소 계산기</h2>
	<div id="bg1">
		<div id="bg2">
			<div id="formdiv">
				<form:form id="nutrimentCal" action="nutrimentCal.do">
					<ul>
						<li>
							<label for="height">[나의 키]</label><br>
							<input type="number" id="height" name="height" value="${height}"/>
						</li>
						<li>
							<label for="weight">[나의 체중]</label><br>
							<input type="number" id="weight" name="weight" value="${weight}"/>
						</li>
						<li>
							<label for="age">[나의 나이]</label><br>
							<input type="number" id="age" name="age" value="${age}"/>
						</li>
						<li> <label for="gender">[성별]</label><br>
							<ul style="display:inline-flex;">
								<li>
									<input type="radio" id="gender-f" name="gender" value="f" checked="checked">여성
								</li>
								<li>
					  				<input type="radio" id="gender-m" name="gender" value="m">남성<br>
					  			</li>
			  				</ul>
			  			</li>
						<li> <label for="type">[나의 타입]</label><br>
							<div style="border:1px solid black;  border-radius:15px; margin:10px;">
							<ul>
								<li>
									<input type="radio" id="fast" name="type" value="fast" checked="checked">빠른 체중 감량<br>
								</li>
								<li>
					  				<input type="radio" id="normal" name="type" value="normal">적당한 체중 감량<br>
					  			</li>
					  			<li>
					  				<input type="radio" id="healthy" name="type" value="healthy">건강한 식습관<br>
					  			</li>
					  			<li>
					  				<input type="radio" id="low" name="type" value="low">저강도 운동<br>
					  			</li>
					  			<li>
					  				<input type="radio" id="high" name="type" value="high">고강도 운동<br>
					  			</li>
			  				</ul>
			  			</div>
			  		</li>
				</ul>
				<div class="align-center">
					<input type="submit" value="계산해 보기">
				</div>
			</form:form>
			</div>
		</div>
	</div>
</div>

<div id="nutriCalResult">
	<div id="bg1">
		<div id="bg2">
			<c:if test="${!empty type}">
				<div id="type-image">
					<c:if test="${type.equals('fast')}">
						<%-- <img alt="" src="${pageContext.request.contextPath}/resources/images/type/fast.png"> --%>
						<div id="fast-bar">
							<div id="carb"><p>탄수화물 30%<p></div><div id="protein"><p>단백질 50%</p></div><div id="fat"><p>지방20%</p></div>
						</div>
					</c:if> 
					<c:if test="${type.equals('normal')}">
						<%-- <img alt="" src="${pageContext.request.contextPath}/resources/images/type/normal.png"> --%>
						<div id="normal-bar">
							<div id="carb"><p>탄수화물 40%<p></div><div id="protein"><p>단백질 40%</p></div><div id="fat"><p>지방20%</p></div>
						</div>
					</c:if> 
					<c:if test="${type.equals('healthy')}">
						<%-- <img alt="" src="${pageContext.request.contextPath}/resources/images/type/healthy.png"> --%>
						<div id="healthy-bar">
							<div id="carb"><p>탄수화물 50%<p></div><div id="protein"><p>단백질 30%</p></div><div id="fat"><p>지방20%</p></div>
						</div>
					</c:if> 
					<c:if test="${type.equals('low')}">
						<%-- <img alt="" src="${pageContext.request.contextPath}/resources/images/type/low.png"> --%>
						<div id="low-bar">
							<div id="carb"><p>탄수화물 30%<p></div><div id="protein"><p>단백질</p></div><div id="fat"><p>지방20%</p></div>
							<p id="pro-info"> [ 단백질 섭취량 : (내 몸무게 X 1.5)g ] </p>
						</div>
					</c:if> 
					<c:if test="${type.equals('high')}">
						<%-- <img alt="" src="${pageContext.request.contextPath}/resources/images/type/high.png"> --%>
						<div id="high-bar">
							<div id="carb"><p>탄수화물 40%<p></div><div id="protein"><p>단백질</p></div><div id="fat"><p>지방20%</p></div>
							<p id="pro-info"> [ 단백질 섭취량 : (내 몸무게 X 2.0)g ] </p>
						</div>
					</c:if>
				</div>
				
				<div style="margin-left: 30px;">
					<p> 내 키와 몸무게에 따른 기초 대사량 : <label id="bmr"><u>${bmr}</u> Kcal</label> <br>
					내 기초 대사량에 따른 일일 권장 섭취량 : <label id="bmr"><u>${dayCal}</u> Kcal</label> <br>
					기초 대사량과 설정 목표에 따른 회원님의 일일 권장 탄단지는 <br>
					탄수화물 <label id="carb"><u>${carb}</u></label>g / 단백질 <label id="prot"><u>${protein}</u></label>g / 지방 <label id="fat"><u>${fat}</u></label>g 입니다.</p>
					<p style="color:red; text:10px;"> 본 수치는 추정치 이며, 권장 사항일 뿐,<br> 자세한 사항은 전문가와 상담하기를 권장합니다.</p>
			
				</div> 
			</c:if>
			<c:if test="${empty type}">
				<div id="non-result">
					<hr style="width:300px; border:solid 1px #0de9df; margin-top:60%;">
					<p> 
					나의 일일 권장 섭취량을 계산해 보세요!
					</p>
					<hr style="width:300px; border:solid 1px #0de9df;">
				</div> 
			</c:if>
			
		</div>
	</div>
</div>