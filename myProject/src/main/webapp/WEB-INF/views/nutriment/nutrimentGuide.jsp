<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
	div#nutrimentGuide{
		width:100%;
		position:relative;
		margin-bottom:50px; 
		font-family: "montserrat",sans-serif;
	}
	
	div#container{
		width:1900px;
		height:800px;
		position:relative;
		margin: 0 auto;
	}
	div#bgImage{
		width:1900px;
		height:800px;
		background-image : url('../resources/images/nutriment/guideBG.png');
		background-repeat: no-repeat;
		position:relative;
	}
	
	div#textAndButton{
		width:1150px;
		position:absolute;
		top:400px;
		background-color: rgba(0,0,0,0.3);
		padding-left:50px;
		color:#fff;
	}
	div#buttons > input{
		  text-align: center;
		  box-sizing: border-box;
		  border-radius:15px;
		  font-family: "montserrat",sans-serif;
		  margin : 30px 10px 10px;
		  float:right;
		  height:30px; 
	}

</style>

<div id="nutrimentGuide">
	<div id="container">
		<div id="bgImage">
		</div>
		<div id="textAndButton">
			<h2 style="font-size: 20pt;">우리는 건강하게 먹고 있을까요?</h2>
			<span style="font-size: 13pt;">우리 현대인들은 과한 탄수화물과 지방을 섭취하고 있습니다. </br>과도하게 섭취하는 그 영양소들도
			건강한 탄수화물, 건강한 지방이 아닌 것을 선택하는 경우가 많습니다. </br> 
			그 반대로 미네랄과 식이섬유의 경우엔 현대인의 평균 섭취량이 권장량 보다 적은 수준입니다.</br>
			그렇다면 우리는 어떤 음식을 또, 얼만큼 먹어야 할까요?</br>
			My First Trainer에서는 회원님의 키와 몸무게, 연령과 성별에 따른 기초대사량과
			내가 목표하는 타입에 따른 일일 권장 영양소를 제안해드립니다.</br>
			내가 필요한 영양소가 어떤 음식에 얼만큼 들어있는지도 영양성분 검색 페이지에서 꼭 검색해보세요!</br></span>
			<div id="buttons">
				<input type="button" value="> 영양성분 검색하러 가기" onclick="location.href='${pageContext.request.contextPath}/nutriment/nutriList.do'">
				<input type="button" value="> 일일권장 영양소 계산하러 가기" onclick="location.href='${pageContext.request.contextPath}/nutriment/nutrimentCal.do'">
			</div>
		</div>
	</div>
</div>