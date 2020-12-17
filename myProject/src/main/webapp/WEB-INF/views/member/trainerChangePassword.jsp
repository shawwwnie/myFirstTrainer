<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout_tainerChangePw.css">
 
<style>
.password_modify_btn{
	margin-top:4px;
	background-image: url('../resources/images/btn_password_modify.png');
	height:40px;
}
.detail_modify_btn{
	margin-top:4px;
	background-image: url('../resources/images/btn_detail_modify.png');
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
   background-image: url('../resources/images/Btn_modify.png');
}
.Btn_home{
   width:140px;
   height:50px;
   margin-bottom: 5px;
   background-image: url('../resources/images/Btn_modify_home.png');
}

</style>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//비밀번호 변경 체크
		$('#mem_pw').keyup(function(){
			if($('#confirm_passwd').val()!='' &&
					 $('#confirm_passwd').val()!=$(this).val()){
				$('#message_id').text('비밀번호 불일치').css('color','red');
			}else if($('#confirm_passwd').val()!='' &&
					$('#confirm_passwd').val()==$(this).val()){
				$('#message_id').text('비밀번호 일치').css('color','#000');
			}
		});
		
		$('#confirm_passwd').keyup(function(){
			if($('#mem_pw').val()!='' &&
					 $('#mem_pw').val()!=$(this).val()){
				$('#message_id').text('비밀번호 불일치').css('color','red');
			}else if($('#mem_pw').val()!='' &&
					$('#mem_pw').val()==$(this).val()){
				$('#message_id').text('비밀번호 일치').css('color','#000');
			}
		});
		
		$('#change_form').submit(function(){
			if($('#now_passwd').val()==''){
				alert('현재 비밀번호를 입력하세요');
				$('#now_passwd').focus();
				return false;
			}
			if($('#mem_pw').val()==''){
				alert('변경 비밀번호를 입력하세요');
				$('#mem_pw').focus();
				return false;
			}
			if($('#confirm_passwd').val()==''){
				alert('변경 비밀번호 확인을 입력하세요');
				$('#confirm_passwd').focus();
				return false;
			}
			if($('#mem_pw').val()!=$('#confirm_passwd').val()){
				alert('변경 비밀번호와 변경 비밀번호 확인 불일치');
				return false;
			}
		});
		$('#member_modify-form').submit(function(){
			var choice = window.confirm('비밀번호를 변경 하시겠습니까?');
			if(choice){
				alert('변경완료!');
			}else{
				return false;
			}
		});
	});
</script>

<div class="mypage_style">
  <div class="container">
    <div class="left">
    
    <form:form id="member_modify-form" action="changePassword.do" commandName="memberVO">
    	<ul>
	      	<a><img class="mypage_texts" src="../resources/images/text_now_pw.png"></a>
			<li><form:input type="password" path="now_passwd" placeholder="현재 비밀번호 입력"/></li>
			
			<a><img class="mypage_texts" src="../resources/images/text_modify_pw.png"></a>
			<li><form:input type="password" id="mem_pw" path="mem_pw" placeholder="변경할 비밀번호"/></li>
			
			<a><img class="mypage_texts" src="../resources/images/text_confirm_pw.png"></a>
			<li><input type="password" id="confirm_passwd"  placeholder="변경할 비밀번호 확인"/></li>
			
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
