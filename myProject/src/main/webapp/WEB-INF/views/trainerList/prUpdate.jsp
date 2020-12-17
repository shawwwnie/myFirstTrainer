<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>

<style>

@charset "UTF-8";
@import url('https://rsms.me/inter/inter-ui.css');

.container {
  display: flex;
  height: 500px;
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
  width:500px;
  height:480px;
  margin:0 auto;
}
@media (max-width: 767px) {
  .right {
    flex-shrink: 0;
    height: 100%;
    width: 100%;
    max-height: 350px;
  }
}
svg {
  position: absolute;
  width: 320px;
}
path {
  fill: none;
  stroke: url(#linearGradient);;
  stroke-width: 4;
  stroke-dasharray: 240 1386;
}
.form {
  width:300px;
  height:300px;
  margin-left:30%;
  position: absolute;
}



input::-moz-focus-inner { 
  border: 0; 
}
.submit {
  color: #000;
  transition: color 300ms;
  margin-top:5%;
}
.submit:hover{
   transform: scale(0.96);
   cursor:pointer;
}




</style>



<script type="text/javascript">
	$(document).ready(function(){
		$("#pic_btn").click(function(){
			//이미지 파일 선택 태그가 노출
			$("#pic_choice").show();
			//수정 버튼 숨김
			$(this).hide();
		});
		
		//원래 이미지를 보관
		var pic_path;
		//변경할 이미지를 보관
		var change_pic;
		
		$("#upload").change(function(){
			var upload = document.getElementById('upload');
			change_pic = upload.files[0];
			if(change_pic){
				var reader = new FileReader();
				reader.readAsDataURL(change_pic);
				
				//사진 업로드전 미리보기 처리
				reader.onload = function(){
					pic_path = $(".change_pic").attr('src');
					//변경된 이미지를 미리보기 셋팅
					$(".change_pic").attr('src',reader.result);
				};
			}
		});
		
		//이미지 초기화
		$('#pic_reset').click(function(){
			$('.change_pic').attr('src',pic_path);
			$('#upload').val('');
			$('#pic_choice').hide();
			$('#pic_btn').show();
		});
		
		$('#pic_submit').click(function(){
			if($('#upload').val()==''){
				alert('파일을 선택하세요');
				$('#upload').focus();
				return;
			}
			
			//파일 전송
			var form_data = new FormData();
			form_data.append('upload',change_pic);
			$.ajax({
				data:form_data,
				type:'POST',
				url:'updatePic.do',
				dataType:'json',
				cache:false,
				contentType:false,
				enctype:'multipart/form-data',
				processData:false,
				success:function(data){
					if(data.result == 'logout'){
						alert('로그인 후 사용하세요!');
					}else if(data.result == 'success'){
						alert('프로필 사진이 수정되었습니다.')
						$('#upload').val('');
						$('#pic_choice').hide();
						$('#pic_btn').show();
						location.reload();
					}else{
						alert('파일 전송 오류 발생');
					}
				},
				error:function(){
					alert('네트워크 오류 발생');
				}
			});
		});
		
	});

</script>

<div class="container">

<div class="right">
<h2 style="text-align:center;">프로필 사진 변경하기</h2>
<h3 style="text-align:center;">${trainer.mem_name}</h3>
  
<div class="form">
	<ul>
		<li>
			<c:if test="${empty trainer.mem_picName}">
				<img src="../resources/images/cameraImage.jpg" style="max-width:200px; max-height:200px;">
			</c:if>
			<c:if test="${!empty trainer.mem_picName}">
				<img src="${pageContext.request.contextPath}/trainerList/photoOutPut.do" 
				width="200" height="200" class="change_pic">
			</c:if>
		</li>
		
		<li>
			<input type="button" class="submit" value="사진등록" id="pic_btn">
			
			<div id="pic_choice" style="display:none;">
			<input type="file" id="upload" accept="images/gif,images/png,images/jpeg"><br>
			<input type="button" class="submit" value="전송" id="pic_submit">
			<input type="button" class="submit" value="취소" id="pic_reset">
			</div>
		</li>
		<li>
			<br>
			<input type="button" value="돌아가기" onclick="location.href='trainerList.do'">
		</li>
	</ul>
</div>

</div>
</div>



