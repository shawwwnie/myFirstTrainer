<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout_board.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		var pre_upload = $('#upload').val();
		var photo_path;
		var free_photo;

		$('#upload').change(function(){
			
			var upload = document.getElementById('upload');
			
			free_photo = upload.files[0];
			if(free_photo){
				var reader = new FileReader();
				reader.readAsDataURL(free_photo);
				
				reader.onload = function(){
					photo_path = $('#free_mfile').attr('src');
					$('#free_file').attr('src',reader.result).show();
					$('#free_mfile').attr('src',reader.result).show();
				}				
			}
			
			$('.reset_photo').show();
			$('.isImgUpdate').val('0');
		});
		
		$('.reset_photo').click(function(){
			$(this).hide();
			$('#free_mfile').attr('src',photo_path).show();
			$('#upload').val('');
			$('.isImgUpdate').val('1');
		});
		
		$('.cancel_photo').click(function(){
			$('#free_file').hide();
			$('#free_mfile').hide();
			$('#upload').val("");
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
	<h2>자유 게시판</h2>
  	<form:form commandName="boardFree" action="modify.do"
	                      		    enctype="multipart/form-data">
	    <form:hidden path="free_num"/>
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
			<c:if test="${empty boardFree.free_filename}">	 			
					<img name="free_file" id="free_file" style="max-width:350px; max-height:350px; display:none; position:relative; left:130px;">				
			</c:if>
					
			<c:if test="${!empty boardFree.free_filename}">
				<input type=hidden value="1" name="isImgUpdate" class="isImgUpdate">
				<div class="align-center">
					<img src="imageView.do?free_num=${boardFree.free_num}" name="free_mfile" id="free_mfile" 
					style="max-width:350px; max-height:350px;">
				</div>				
			</c:if>
			</li>
			
			<li>		
				<label for="upload" class="upload">이미지 파일업로드</label>	
			</li>
			<li>
				<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg" style="visibility:hidden;">				
				<input type="button" value="이미지 삭제" class="cancel_photo" class="delete_img" style="width:100px;">
				<input type="button" value="이미지 되돌리기" class="reset_photo" style="display:none; width:120px;">
			</li>
		</ul>
		
			
		<div class="align-right" style="padding-top:30px;">
			<input type="submit" value="수정">
			<input type="button" value="목록으로" onclick="location.href='list.do'">
		</div> 
		
	</form:form> 
</div>