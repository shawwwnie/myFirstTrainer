<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Permanent+Marker" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout_memberRegister.css">

<script type="text/javascript">
/*----------------------------일반 회원 가입 페이지----------------------------------*/
 
     $(document).ready(function(){
      var checkId = 0;
      
      //아이디 중복 체크
      $('#confirmMemberId').click(function(){
         if($('#mem_id').val()==''){
            $('#confirmMemberId').css('color','red').val('아이디를 입력하세요');
            $('#mem_id').focus();
            return;
         }
         //var regMsg = new RegExp('^[A-Za-z0-9+]{4,10}$');
         var regMsg = /^[A-Za-z0-9+]{4,10}$/
         if(!regMsg.test($('#mem_id').val())){
            $('#confirmMemberId').css('color','red').val('아이디는 영문,숫자 4자이상 10자이하로 입력하세요');
            $('#mem_id').focus();
            return;
         }
         
         $('#confirmMemberId').text();//메시지 초기화
         $('#loading').show();//로딩 이미지 노출
         
         $.ajax({
            url:'confirmMemberId.do',
            type:'post',
            data:{id:$('#mem_id').val()},
            dataType:'json',
            cache:false,
            timeout:30000,
            success:function(data){
               $('#loading').hide();//로딩 이미지 감추기
               $('#mem_id.errors').hide();//서버에서 유효성 체크 결과 오류 메시지 숨기기
               if(data.result == 'idNotFound'){
                  $('#confirmMemberId').val('ID 중복체크 결과 | 등록가능ID');
                  checkId = 1;
               }else if(data.result == 'idDuplicated'){
                  $('#confirmMemberId').css('color','red').val('ID 중복체크 결과 | 사용중인 ID, 다른 ID를 입력해주세요');
                  $('#mem_id').val('').focus();
                  checkId=0;
               }else{
                  checkId=0;
                  alert('ID중복체크 오류');
               }
            },
            error:function(){
               checkId = 0;
               $('#loading').hide();//로딩 이미지 감추기
               alert('네트워크 오류 발생');
            }
         });
      });
      
      //아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
      $('#member_register_form #mem_id').keydown(function(){
         checkId = 0;
         $('#confirmMemberId').css('color','black').val('ID 중복체크');
      });
      
      //submit 이벤트 발생시 아이디 중복 체크 여부 확인
      $('#member_register_form').submit(function(){
         if(checkId==0){
            $('#confirmMemberId').css('color','red').val('아이디 중복 체크 필수');
            alert("아이디 중복체크 필수");
            $('#mem_id').focus();
            return false;
         }
         if($('#mem_name').val()==''){
            $('#mem_name').attr('placeholder','이름을 입력하세요');
            alert("이름을 입력해주세요");
            $('#mem_name').focus();
            return false;
         }
         if($('#mem_pw').val()==''){
            $('#mem_pw').attr('placeholder','비밀번호를 입력하세요');
            alert("비밀번호를 입력해주세요");
            $('#mem_pw').focus();
            return false;
         }
         if($('#mem_gender').val()==''){
        	 $('#mem_gender').focus();
        	 alert("성별을 선택해주세요");
            return false;
         }
         if($('#mem_cell').val()==''){
            $('#mem_cell').attr('placeholder','전화번호를 입력하세요(숫자만)');
            alert("전화번호를 입력해주세요");
            $('#mem_cell').focus();
            return false;
         }
         if($('#mem_email').val()==''){
            $('#mem_email').attr('placeholder','이메일을 입력하세요');
            alert("이메일을 입력해주세요");
            $('#mem_email').focus();
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
<div class="signup-form">
   <h2><img class="register_img" src="../resources/images/text_Register.png"></h2>
   <form:form id="member_register_form" action="memberRegister.do" commandName="memberVO" >
      <form:errors element="div" cssClass="error-color"/>
      
      <form:input path="mem_name" placeholder="이름 : 필수입력사항" />
      
      <form:input id="mem_id" path="mem_id" placeholder="ID : 필수입력사항" autocomplete="off" />
      <input type="button" id="confirmMemberId" value="ID 중복체크">
      
      <form:password id="mem_pw" path="mem_pw" placeholder="비밀번호 : 필수입력사항" />
      
      <form:select id="mem_gender" path="mem_gender" >
         <form:option value="" label="성별을 선택해주세요 : 필수입력사항" />
         <form:option value="1" label="남자" />
         <form:option value="2" label="여자" />
      </form:select>
      
      <form:input id="mem_cell" path="mem_cell" placeholder="전화번호(숫자만 입력하세요) : 필수입력사항" />
      
      <form:input id="mem_email" path="mem_email" placeholder="이메일 : 필수입력사항" />
      
      <form:input path="mem_zipcode" placeholder="우편번호" />
      <form:input type="button" class="btn_findZip" value="우편번호 찾기" onclick="findCode()" path="mem_zipcode"/>
      
      <form:input id="mem_addr1" path="mem_addr1" placeholder="주소" />
      
      <form:input id="mem_addr2" path="mem_addr2" placeholder="상세주소" />
      <form:input id="mem_addrExtra" path="" placeholder="참고항목" readonly="true" style="display: none;"/>
      
      <input type="submit" value="회원가입" class="signup-btn"> 
      
      <a href="${pageContext.request.contextPath}/member/login.do">아이디가 있으면 여기를 클릭해주세요</a>
   </form:form>
</div>