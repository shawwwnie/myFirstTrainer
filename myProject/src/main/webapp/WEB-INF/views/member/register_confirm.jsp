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
   margin-top: 30px;
   margin-bottom: 5px;
   background-image:url('../resources/images/Btn_confirm.png');
}

</style>

  <div class="container">
  
     <div class="right"> 
         <div class="form">
            <c:if test="d.mem_name == null"><a><img src="../resources/images/text_result.png"></a></c:if>
            <c:if test="d.mem_name != null"><a></a></c:if>
               <li class="id_result">${message}</li>
            
            <input type="button" id="submit" value="" class="confirm_btn" onclick="location.href='login.do'"> 

         </div>
    </div>
    
  </div>
  

