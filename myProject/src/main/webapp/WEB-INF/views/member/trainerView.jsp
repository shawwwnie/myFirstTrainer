<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout_trainerView.css">
 
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
.matchingFrom{
	margin-top:4px;
	background-image: url('../resources/images/Btn_applyList.png');
	height:39px;
}
.matchingFrom:hover{
	transform:scale(1.05);
	cursor:pointer;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>

<div class="mypage_style">
  <div class="container">
    <div class="left">
      <ul>
     	<a><img class="mypage_texts" src="../resources/images/text_name.png"></a>
		<li>${trainer.mem_name}</li>
		
		<a><img class="mypage_texts" src="../resources/images/text_cell.png"></a>
		<li>${trainer.mem_cell}</li>
		
		<a><img class="mypage_texts" src="../resources/images/text_email.png"></a>
		<li>${trainer.mem_email}</li>
		
		<a><img class="mypage_texts" src="../resources/images/text_g_addr.png"></a>
		<li>${trainer.g_addr}</li>
		
		<a><img class="mypage_texts" src="../resources/images/text_exp.png"></a>
		<li>${trainer.exp}</li>
		
		<a><img class="mypage_texts" src="../resources/images/text_career.png"></a>
		<li>${trainer.career}</li>
		
		<a><img class="mypage_texts" src="../resources/images/text_zipcode.png"></a>
		<li>${trainer.mem_zipcode}</li>
		
		<a><img class="mypage_texts" src="../resources/images/text_addr1.png"></a>
		<li>${trainer.mem_addr1}</li>
		
		<a><img class="mypage_texts" src="../resources/images/text_addr2.png"></a>
		<li>${trainer.mem_addr2}</li>
		
		<a><img class="mypage_texts" src="../resources/images/text_registerDate.png"></a>
		<li>${trainer.mem_date}</li>
	</ul>
   </div>
    <div class="right">
      <form:form class="re_form" action="login.do" commandName="memberVO">
	      <div class="form">
	        <input type="button" class="detail_modify_btn" onclick="location.href='update.do'" />
			<input type="button" class="password_modify_btn" onclick="location.href='updatePw.do'">
			<input type="button" class="delete_btn" onclick="location.href='delete.do'">
			<input type="button" class="matchingFrom" onclick="location.href='${pageContext.request.contextPath}/trainerList/matchingList.do'">
	      </div>
      </form:form>
    </div>
  </div>
</div>
