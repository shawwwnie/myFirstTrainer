<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout_memberView.css">

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

.training{
	margin-top:4px;
	background-image: url('../resources/images/Btn_stopTraining.png');
	height:41px;
}
.training:hover{
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
		<li>${member.mem_name}</li>
		
		<a><img class="mypage_texts" src="../resources/images/text_myTrainer.png"></a>
		<c:if test="${member.t_num==0}"><li>매칭된 트레이너가 없습니다.</li></c:if>
		<c:if test="${member.t_num!=0}"><li>${trainerId.mem_id}님과 트레이닝중	</li></c:if>
		
		<a><img class="mypage_texts" src="../resources/images/text_cell.png"></a>
		<li>${member.mem_cell}</li>
		
		<a><img class="mypage_texts" src="../resources/images/text_email.png"></a>
		<li>${member.mem_email}</li>
		
		<a><img class="mypage_texts" src="../resources/images/text_zipcode.png"></a>
		<li>${member.mem_zipcode}</li>
		
		<a><img class="mypage_texts" src="../resources/images/text_addr1.png"></a>
		<li>${member.mem_addr1}</li>
		
		<a><img class="mypage_texts" src="../resources/images/text_addr2.png"></a>
		<li>${member.mem_addr2}</li>
		
		<a><img class="mypage_texts" src="../resources/images/text_registerDate.png"></a>
		<li>${member.mem_date}</li>
	</ul>
   </div>
    <div class="right">
      <form:form class="re_form" action="login.do" commandName="memberVO">
	      <div class="form">
	        <input type="button" class="detail_modify_btn" onclick="location.href='update.do'" />
			<input type="button" class="password_modify_btn" onclick="location.href='updatePw.do'">
			<input type="button" class="delete_btn" onclick="location.href='delete.do'">
			<input type="button" id="cutRelation" class="training">			
	      </div>
      </form:form>
    </div>
  </div>
</div>

<script type="text/javascript">
var cutRelation = document.getElementById('cutRelation');

cutRelation.onclick=function(){
   var choice = window.confirm('트레이닝을 중단하시겠습니까?');
   if(choice){
	      location.href='${pageContext.request.contextPath}/trainerList/trainingDelete.do';
	   }
};
</script>