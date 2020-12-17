<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout_board.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$('#board_submit').submit(function(){
			if($('#free_content').val()==''){
				alert('내용을 입력해 주세요');
				$('#free_content').focus();
				return false;
			}
		});
		
		$('#upload').change(function(){
			$('#cancel_upload').show();
		});
		
		$('#cancel_upload').click(function(){
			$(this).hide();
		});
		
		var free_photo;
		$('#upload').change(function(){
			var upload = document.getElementById('upload');
			
			free_photo = upload.files[0];
			if(free_photo){
				var reader = new FileReader();
				reader.readAsDataURL(free_photo);
				
				reader.onload = function(){
					$('.free_file').attr('src',reader.result).show();
				}				
			}
		});
		
		$('#cancel_upload').click(function(){
			$('#upload').val("");
			$('.free_file').hide();
		});

		//글제목 글자수 제한
		$('#boardSubmit').submit(function(){
			if($('#free_title').val().length > 32){
				alert('글 제목이 제한 글자수를 초과했습니다.');
			}
			return false;
		});
	});
</script>

<div class="page-main-style">
	<h2>팁 게시판</h2>
  	<form:form commandName="freeBoardVO" action="write.do" enctype="multipart/form-data" id="board_submit"> 
 		<form:errors element="div" cssClass="error-color"/>
		<ul style="margin-top:20px">
			<li>
				<label for="free_title">제목</label>
				<form:input path="free_title" class="title"/>
				<form:errors path="free_title" cssClass="error-color"/>
			</li>
			<li>
				<label for="free_content">내용</label>
				<form:textarea path="free_content" cols="45" rows="15"/>
				<form:errors path="free_content" cssClass="error-color"/>
			</li>
 			<li>
				<label for="upload" class="upload">이미지 파일업로드</label>
				<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg" style="visibility:hidden;">
			</li>
			<li>
				<img id="free_file" class="free_file" alt="free image"
				 style="max-width:350px; max-height:350px; display:none; position:relative; left:130px;">
			</li>
			<li>
				<input type="button" value="파일업로드 취소" id="cancel_upload" style="display:none; width:130px; margin-left:130px;">
			</li>
		</ul>
		<div class="align-center" style="margin-top:30px;">
			<input type="submit" value="글등록">
			<input type="button" value="목록"
			       onclick="location.href='list.do'">
		</div> 
	</form:form> 
</div>


