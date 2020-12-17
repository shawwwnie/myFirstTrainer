<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout_board.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$('#board_submit').submit(function(){
			if($('#tq_content').val()==''){
				alert('내용을 입력해 주세요');
				$('#tq_content').focus();
				return false;
			}
		});
		
		var pre_upload = $('#upload').val();
		var photo_path;
		var tq_photo;

		$('#upload').change(function(){
			
			var upload = document.getElementById('upload');
			
			tq_photo = upload.files[0];
			if(tq_photo){
				var reader = new FileReader();
				reader.readAsDataURL(tq_photo);
				
				reader.onload = function(){
					photo_path = $('#tq_mfile').attr('src');
					$('#tq_file').attr('src',reader.result).show();
					$('#tq_mfile').attr('src',reader.result).show();
				}				
			}
			
			$('.reset_photo').show();
			$('.isImgUpdate').val('0');
		});
		
		$('.reset_photo').click(function(){
			$(this).hide();
			$('#tq_mfile').attr('src',photo_path).show();
			$('#upload').val('');
			$('.isImgUpdate').val('1');
		});
		
		$('.cancel_photo').click(function(){
			$('#tq_file').hide();
			$('#tq_mfile').hide();
			$('.isImgUpdate').val('0');
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
	<h2>트레이너 문의 게시판</h2>
  	<form:form commandName="boardTranqna" action="modify.do"
	                      		    enctype="multipart/form-data" id="board_submit">
	    <form:hidden path="tq_num"/>
 		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<form:radiobutton path="tq_type" value="0" name="tq_type_0" style="margin: 5px 0 15px 130px;"/>
				<label for="tq_type_0" style="margin-top:5px; float:none;">모두에게 공개</label>
				<form:radiobutton path="tq_type" value="1" name="tq_type_1" style="margin: 5px 0 15px 15px;"/>
				<label for="tq_type_1" style="margin-top:5px; float:none;">트레이너에게만 공개</label>
			</li>
			<li>
				<label for="tq_title">제목</label>
				<form:input path="tq_title" class="title"/>
				<form:errors path="tq_title" cssClass="error-color"/>
			</li>
			<li>
				<label for="tq_content">내용</label>
				<form:textarea path="tq_content" id="tq_content" cols="45" rows="12"/>
				<form:errors path="tq_content" cssClass="error-color"/>
			</li>
			
			<li>
				<c:if test="${empty boardTranqna.tq_filename}">		 			
						<img name="tq_file" id="tq_file" style="max-width:300px; max-height:300px; display:none; position:relative; left:130px;">					
				</c:if>
						
				<c:if test="${!empty boardTranqna.tq_filename}">
					<input type=hidden value="1" name="isImgUpdate" class="isImgUpdate">
					<div class="align-center">
						<img src="imageView.do?tq_num=${boardTranqna.tq_num}" name="tq_mfile" id="tq_mfile" style="max-width:300px; max-height:300px;">
					</div>					
				</c:if>	
			</li>
			<li>
				<label for="upload" class="upload">이미지 파일업로드</label>
			</li>
			
			<li>
				<div class="align-center">
					<input type="file" value="이미지 업로드 및 수정" name="upload" id="upload" accept="image/gif,image/png,image/jpeg" style="visibility:hidden;">				
					<input type="button" value="이미지 삭제" class="cancel_photo delete_img" style="width:100px;">
					<input type="button" value="이미지 되돌리기" class="reset_photo" style="display:none; width:120px;">
				</div>
			</li>	
		</ul>
		
			
		<div class="align-right">
			<input type="submit" value="수정">
			<input type="button" value="목록으로" onclick="location.href='list.do'">
		</div> 
	</form:form> 
</div>