<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout_trainerDelete.css">
 
<style>

.detail_modify_btn{
	margin-top:4px;
	background-image: url('../resources/images/btn_detail_modify.png');
	height:40px;
}

.password_modify_btn{
	margin-top:4px;
	background-image: url('../resources/images/btn_password_modify.png');
	height:40px;
}

.delete_btn{
	margin-top:4px;
	background-image: url('../resources/images/btn_delete_member.png');
	height:26px;
}
.update_btn{
   width:140px;
   height:50px;
   margin-bottom: 5px;
   background-image: url('../resources/images/Btn_delete.png');
}
.Btn_home{
   width:140px;
   height:50px;
   margin-bottom: 5px;
   background-image: url('../resources/images/Btn_modify_home.png');
}
</style>
<%-- <div class="page-main-style">
	<h2>일반회원 탈퇴</h2>
	<form:form action="delete.do" commandName="memberVO">
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="mem_id">아이디</label>
				<form:input path="mem_id"/>
				<form:errors path="mem_id" cssClass="error-color"/>        
			</li>
			<li>
				<label for="mem_pw">비밀번호</label>
				<form:password path="mem_pw"/>
				<form:errors path="mem_pw" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="홈으로"
			    onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div> --%>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$('#member_modify-form').submit(function(){
			var choice = window.confirm('정말 탈퇴하시겠습니까?');
			if(choice){
				alert('탈퇴완료!');
			}else{
				return false;
			}
		});
	});
</script>

<div class="mypage_style">
  <div class="container">
    <div class="left">
    
    <form:form id="member_modify-form" action="delete.do" commandName="memberVO">
    	<ul>
	      	<a><img class="mypage_texts" src="../resources/images/text_id.png"></a>
			<li><form:input path="mem_id" placeholder="아이디 입력"/></li>
			
			<a><img class="mypage_texts" src="../resources/images/text_pw.png"></a>
			<li><form:input type="password" id="memModify" path="mem_pw" placeholder="비밀번호 입력"/></li>
			
			
			<input type="submit" value="" class="update_btn">
			
		</ul>
		
		
	</form:form>
	
   </div>
    <div class="right">
      <form:form class="re_form" action="login.do" commandName="memberVO">
	      <div class="form">
	        <input type="button"  class="detail_modify_btn" onclick="location.href='update.do'" />
			<input type="button" class="password_modify_btn" onclick="location.href='updatePw.do'">
			<input type="button" class="delete_btn" onclick="location.href='delete.do'">
	      </div>
      </form:form>
    </div>
  </div>
</div>
