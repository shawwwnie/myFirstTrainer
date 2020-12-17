<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout_login.css">

<style>
.login_btn{
   width:125px;
   height:30px;
   margin-left: 25%;
   margin-top: 15px;
   margin-bottom: 5px;
   background-image: url('../resources/images/Btn_login.png');
} 
.error{
	margin-top:4%;
	font-size: 12px;
	text-align: center;
	color:red;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$('#re_form').submit(function(){
			if($('#mem_id').val()==''){
	            $('#mem_id').attr('placeholder','아이디를 입력하세요');
	            $('#mem_id').focus();
	            return false;
			}
			if($('#mem_pw').val()==''){
	            $('#mem_pw').attr('placeholder','비밀번호를 입력하세요');
	            $('#mem_pw').focus();
	            return false;
			}
		});
	});	
</script>

<div class="page-main-style">
  <div class="container">
  
    <div class="left">
      <div class="login">Login</div>
      <div class="eula">로그인을 통해 마음에 드는 트레이너분께 트레이닝부터 식단 조절까지 받으면서 건강을 향한 첫 발걸음을 디딜 수 있습니다.</div>
    </div>
    
    <div class="right">
      <form:form id="re_form" action="login.do" commandName="memberVO">
      
         <div class="form">
         
         	<!-- id/pw 입력란 -->
         	
         	<label for="mem_id">ID</label>
         	<form:input class="input_id_pw" path="mem_id" id="mem_id"/>
         	<form:errors path="mem_id"/>
           	<label for="mem_pw" >Password</label>
           	<form:input type="password" class="input_id_pw" path="mem_pw" id="mem_pw"/>
           	<form:errors path="mem_pw"/><br>
         	
         	<!-- 일반회원/트레이너 선택란 -->    
         	<img class="texts_img" src="../resources/images/text_Member.png"> 
         	<input type="radio" class="radio_btn"name="mem_auth" value="1" checked="checked">
         	<img class="texts_img" src="../resources/images/text_Trainer.png">
         	<input type="radio" class="radio_btn" name="mem_auth" value="2">
         	
         	<!-- 에러메세지 -->
         	<form:errors element="div" class="error"/>
         	
         	<!-- 로그인 버튼 -->
         	<a><input type="submit" value="" class="login_btn"></a>
         	
         	<!-- 회원가입 + id/pw찾기 -->
           	<button type="button" class="r_f_btn" onclick="location.href='${pageContext.request.contextPath}/member/userType.do'">
           	<img src="../resources/images/Btn_Register.png">
           	</button>
           	<button type="button" class="r_f_btn" onclick="location.href='${pageContext.request.contextPath}/member/findIdPw.do'">
           	<img src="../resources/images/Btn_ID_PW.png">
           	</button>
           	
         </div>
      </form:form>
    </div>
    
  </div>
</div>