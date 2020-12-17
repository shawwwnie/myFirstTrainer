<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout_trainerModify.css">
 
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
   background-image: url('../resources/images/Btn_modify.png');
}
.Btn_home{
   width:140px;
   height:50px;
   margin-bottom: 5px;
   background-image: url('../resources/images/Btn_modify_home.png');
}
.btn_zipcode{
  height:15px;
  background-image: url('../resources/images/Btn_findZip.png');
  margin-left: 10px;
}
.btn_zipcode:hover{
  text-decoration: none;
  color: #000;
  font-family: "montserrat",sans-serif;
  font-size: 14px;
  transition: 0.8s;
  display: block;
  cursor:pointer;
  transform: scale(0.90);
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$('#member_modify-form').submit(function(){
			var choice = window.confirm('수정하시겠습니까?');
			if(choice){
				alert('수정완료!');
			}else{
				return false;
			}
		});
	});
</script>
<!-- 우편번호 찾기 API -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function findCode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var mem_addr1 = ''; // 주소 변수
            var mem_addrExtra = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
            	mem_addr1 = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
            	mem_addr1 = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                	mem_addrExtra += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                	mem_addrExtra += (mem_addrExtra !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(mem_addrExtra !== ''){
                	mem_addrExtra = ' (' + mem_addrExtra + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("mem_addrExtra").value = mem_addrExtra;
            
            } else {
                document.getElementById("mem_addrExtra").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('mem_zipcode').value = data.zonecode;
            document.getElementById("mem_addr1").value = mem_addr1;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("mem_addr2").focus();
        }
    }).open();
}
</script>
<div class="mypage_style">
  <div class="container">
    <div class="left">
    
    <form:form id="member_modify-form" action="update.do" commandName="memberVO">
    	<ul>
	      	<li>
	      		<a><img class="mypage_texts" src="../resources/images/text_name.png"></a>
	      	</li>
			<li>
				<form:input path="mem_name" value="${trainer.mem_name}" readonly="true"/>
			</li>
			
			<li>
				<a><img class="mypage_texts" src="../resources/images/text_cell.png"></a>
			</li>
			<li>
				<form:input type="text" id="memModify" path="mem_cell" placeholder="${trainer.mem_cell}"/>
			</li>
			
			<li>
				<a><img class="mypage_texts" src="../resources/images/text_email.png"></a>
			</li>
			<li>
				<form:input type="text" id="memModify" path="mem_email" placeholder="${trainer.mem_email}"/>
			</li>
			
			<li>
				<a><img class="mypage_texts" src="../resources/images/text_g_addr.png"></a>
			</li>
			<li>
				<form:input type="text" id="memModify" path="g_addr" placeholder="${trainer.g_addr}"/>
			</li>
			
			<li>
				<a><img class="mypage_texts" src="../resources/images/text_exp.png"></a>
			</li>
			<li>
				<form:input type="text" id="memModify" path="exp" placeholder="${trainer.exp}"/>
			</li>
			
			<li>
				<a><img class="mypage_texts" src="../resources/images/text_career.png"></a>
			</li>
			<li>
				<form:input type="text" id="memModify" path="career" placeholder="${trainer.career}"/>
			</li>
			
			<li>
				<a><img class="mypage_texts" src="../resources/images/text_zipcode.png"></a>
			</li>
			<li>
				<form:input type="text" id="mem_zipcode" path="mem_zipcode" placeholder="${trainer.mem_zipcode}"/>
				<form:input type="button" class="btn_zipcode" value="" onclick="findCode()" path=""/>
			</li>
			
			<li>
				<a><img class="mypage_texts" src="../resources/images/text_addr1.png"></a>
			</li>
			<li>
				<form:input type="text" id="mem_addr1" path="mem_addr1" placeholder="${trainer.mem_addr1}"/>
			</li>
			
			<li>
				<a><img class="mypage_texts" src="../resources/images/text_addr2.png"></a>
			</li>
			<li>
				<form:input type="text" id="mem_addr2" path="mem_addr2" placeholder="${trainer.mem_addr2}"/>
			</li>
			
			<li>
				<a><img class="mypage_texts" src="../resources/images/text_registerDate.png"></a>
			</li>
			<li>
				<form:input type="text" id="memModify" path="mem_date" value="${trainer.mem_date}" readonly="true"/>
			</li>
			
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