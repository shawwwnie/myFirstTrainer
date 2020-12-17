<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout_resultIdPw.css">
<style>
.confirm_btn{
   width:200px;
   height:30px;
   margin-left: 0px;
   margin-top: 50px;
   margin-bottom: 5px;
   background-image:url('../resources/images/Btn_confirm.png');
} 
</style>

  <div class="container">
  
     <div class="right">
     
      <form:form id="re_form" action="findExit.do" commandName="memberVO">
      
         <div class="form">
            <a><img src="../resources/images/text_result.png"></a>
               <c:if test="${findPw.mem_id == null}"><li class="id_result">검색 결과가 없습니다.<br> 입력정보를 다시 확인해주세요.</li></c:if>
               <c:if test="${findPw.mem_id != ''}"><li class="id_result">${findPw.mem_pw}</li></c:if>
            <!-- 로그인 버튼 -->
            <input type="submit" id="submit" value="" class="confirm_btn"> 
         </div>
         
      </form:form>
      
    </div>
    
  </div>
  


