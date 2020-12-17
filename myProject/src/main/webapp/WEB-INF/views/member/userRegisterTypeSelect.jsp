<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout_userType.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#button1').click(function(){
		alert("휴대전화 번호와 이메일은 아이디/비밀번호 찾기 시 사용하실 정보입니다. 필수입력사항을 기재해주세요.");
	});
	$('#button2').click(function(){
		alert("휴대전화 번호와 이메일은 아이디/비밀번호 찾기 시 사용하실 정보입니다. 필수입력사항을 기재해주세요.");
	});
});
</script>
<div class="page-main-style">
   <div class="align-center">
      <h2 class="type_text"><img src="../resources/images/text_Member_type_select.png"></h2>
      
      <!-- 일반회원 버튼 -->
      <button type="button" id="button1" class="type_select_btn" 
      onclick="location.href='${pageContext.request.contextPath}/member/memberRegister.do'">
      <img src="../resources/images/Btn_member_register.png">
      </button>
      
      <!-- 트레이너 버튼 -->
      <button type="button" id="button2" class="type_select_btn" 
      onclick="location.href='${pageContext.request.contextPath}/member/trainerRegister.do'">
      <img src="../resources/images/Btn_trainer_register.png">
      </button>
   </div>
</div>
