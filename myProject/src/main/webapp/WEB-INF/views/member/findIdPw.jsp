<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout_findIdPw.css">
 
<style>
.find_id_btn{
   margin-top:10px;
   margin-left:5px;
   width:200px;
   background-image: url('../resources/images/Btn_find_id.png');
}
.find_pw_btn{
   margin-top:10px;
   margin-left:5px;
   width:200px;
   background-image: url('../resources/images/Btn_find_pw.png');
}
</style>


<div class="page-main-style">
  <div class="container">
  
    <div class="left">
      <form:form class="form_left" action="findMemberId.do" commandName="memberVO">
      	<div class="form">
      		<a><img class="main_text_img" src="../resources/images/text_find_id.png"></a>
      		
      		<!-- 아이디찾기 핸드폰/이메일 입력란 -->
         	<label for="mem_cell"></label>
         	<form:input class="input_id_pw" path="mem_cell" placeholder="휴대전화번호"/>
         	
           	<label for="mem_email"></label>
           	<form:input  class="input_id_pw" path="mem_email" placeholder="이메일"/><br>
         	
         	<!-- 일반회원/트레이너 선택란 -->    
         	<img class="texts_img" src="../resources/images/text_Member.png">
         	<input type="radio" class="radio_btn"name="mem_auth" value="1" checked="checked">
         	<img class="texts_img" src="../resources/images/text_Trainer.png">
         	<input type="radio" class="radio_btn" name="mem_auth" value="2">
         	
         	<!-- 로그인 버튼 -->
         	<input type="submit" id="submit" value="" class="find_id_btn">
         	
         </div>
      </form:form>
    </div>
    
    <div class="right">
      <form:form class="form_left" action="findMemberPw.do" commandName="memberVO">
      
         <div class="form">
         	<a><img class="main_text_img" src="../resources/images/text_find_pw.png"></a>
         	
         	<!-- 비밀번호찾기 아이디/핸드폰 입력란 -->
         	<label for="mem_id"></label>
         	<form:input class="input_id_pw" path="mem_id" placeholder="아이디"/>
         	
           	<label for="mem_pw" ></label>
           	<form:input class="input_id_pw" path="mem_cell" placeholder="휴대전화번호"/><br>
         	
         	<!-- 일반회원/트레이너 선택란 -->    
         	<img class="texts_img" src="../resources/images/text_Member.png">
         	<input type="radio" class="radio_btn"name="mem_auth" value="1" checked="checked">
         	<img class="texts_img" src="../resources/images/text_Trainer.png">
         	<input type="radio" class="radio_btn" name="mem_auth" value="2">
         	
         	<!-- 로그인 버튼 -->
         	<input type="submit" id="submit" value="" class="find_pw_btn">
         	
         </div>
      </form:form>
    </div>
    
  </div>
</div>
