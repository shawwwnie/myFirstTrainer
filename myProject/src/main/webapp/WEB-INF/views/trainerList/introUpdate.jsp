<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style>

@charset "UTF-8";
@import url('https://rsms.me/inter/inter-ui.css');

  
.container {
  display: flex;
  height: 320px;
  margin: 0 auto;
  width: 640px;
}
@media (max-width: 767px) {
  .container {
    flex-direction: column;
    height: 630px;
    width: 320px;
  }
}

.right {
  background: #DAEFF5;
  box-shadow: 0px 0px 40px 16px rgba(0,0,0,0.22);
  color: #000;
  position: relative;
  width:450px;
  height:220px;
  margin:0 auto;
  margin-top:15%;
}
@media (max-width: 767px) {
  .right {
    flex-shrink: 0;
    height: 100%;
    width: 100%;
    max-height: 350px;
  }
}

.form {
  width:300px;
  height:300px;
  margin: 40px;
  margin-top: 20px;
  margin-left: 35px;
  position: absolute;
}


input {
  background: transparent;
  border: 0;
  color: #000;
  font-size: 18px;
  height: 30px;
  line-height: 30px;
  outline: none !important;
  width: 100%;
}
input::-moz-focus-inner { 
  border: 0; 
}


#submit {
  color: #000;
  transition: color 300ms;
  margin-left:10%
}
#submit:hover{
   transform: scale(0.96);
   cursor:pointer;
}

#intro_write{
	width:400px;
	margin-bottom:10%;
}




</style>



<div class="container">
	<div class="right">
		<h2 style="text-align:center;">자기소개 수정</h2>
		<form:form id="re_form" action="introUpdate.do" commandName="tlBoardVO">		
			<div class="form">
			<form:input path="mem_intro" id="intro_write" placeholder="최선을 다해 몸짱을 만들어드리겠습니다!!"/><br>
			<input type="submit" id="submit" name="intro_modify" value="수정">
			<input type="button" id="submit" value="돌아가기" onclick="location.href='trainerList.do'">
			</div>
		</form:form>

	</div>
</div>







