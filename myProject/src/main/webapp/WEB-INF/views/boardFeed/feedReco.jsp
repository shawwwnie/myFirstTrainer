<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javaScript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script>
$(document).ready(function(){
		
		var currentPage;
		var count;
		var rowCount;
		//목록 호출
		function selectData(pageNum){
			currentPage = pageNum;
			
			if(pageNum == 1){
				//처음 호출시는 해당 ID의 div의 내부 내용물을 제거
				$('#output').empty();
			}
			
			//로딩 이미지 노출
			$('#loading').show();
			
			$.ajax({
				type:'post',
				data:{pageNum:pageNum},
				url:'feedRecoAjax.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					//로딩 이미지 감추기
					$('#loading').hide();
					count = data.count;
					rowCount = data.rowCount;
					var list = data.list;
					
					if(count < 0 || list == null){
						alert('목록 호출 오류 발생!');
					}else{
						$(list).each(function(index,item){
							
							var output = '<div class="grid">';
							
							if(item.feed_filename == null){
								output += '<img class="thumbnail" src="../resources/images/feed/defaultImg.jpg">';
							} else {
								output += '<img src="imageView.do?feed_num='+item.feed_num+'">';
							}
							
							output += '<div class="grid__body">';
							output += '<div class="relative">';
							if(${user.mem_num} == item.mem_num){
								output += '<a class="grid__link"  href="feedDetail.do?feed_num='+ item.feed_num +'" ></a>';
							} else if(${user.mem_num} != item.mem_num){
								output += '<a class="grid__link"  href="otherFeedDetail.do?feed_num='+ item.feed_num +'" ></a>';
							}
							
							output += '<h1 class="grid__title">';
							
							if(item.feed_type == 1) {
								output += '식단';
							} else if(item.feed_type == 2) {
								output += '운동';
							}
							output += '</h1>';
							output += '<p class="grid__author">작성일: ' + item.feed_reg_date + '</p>';
							output += '<p class="grid__author">글쓴이:' +item.mem_id+'</p>';
							output += '</div>';
							output += '<div class="mt-auto" >';
							output += '<span class="grid__tag">'+item.feed_content+'</span>';
							output += '</div>';
							output += '</div>';
							output += '</div>';
							
							//문서 객체에 추가
							$('#output').append(output);
						});
						
					}
				},
				error:function(){
					//로딩 이미지 감추기
					$('#loading').hide();
					alert('네트워크 오류');
				}
		});
	}
	
	//스크롤시 데이터 추가
	$(window).scroll(function(){
		
		var scrollTop = $(document).scrollTop();
		var docHeight = $(document).height();
		var winHeight = $(window).height();
		
		if(scrollTop >= docHeight - winHeight){
			if(currentPage>=Math.ceil(count/rowCount)){
				//다음 페이지가 없음
				return;
			}else{
				//다음 페이지가 존재
				var pageNum = currentPage + 1;
				selectData(pageNum);
			}
		 }
	});
	
	//1페이지 호출
	selectData(1);
	
});
</script>
<style>
.write_Btn {
	width: 50px;
	height: 50px;
	background-image: url('../resources/images/Btn_write_board.png');
	border: none;
	border-radius: 25px;
}
</style>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/layout_feedReco.css">
<div class="page_feedReco">
	<input type="button" class="write_Btn" value="" onclick="location.href='feedWrite.do'">
	<a>글쓰기</a>
	<div class="feeds">	
		<c:if test="${count == 0}">
			<div class="masonry">
				<div class="grid">
					<img src="../resources/images/cameraImage.jpg">
					<div class="grid__body">
						<div class="relative">
							<a class="grid__link" href="/"></a>
							<h1 class="grid__title">
								등록된 게시물이 <br>없습니다.
							</h1>
							<p class="grid__author">게시물을 등록해주세요</p>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<div class="masonry">
			<div id="output">
				<!-- ajax 영역 -->
			</div>
			<div id="loading" style="display: none;">
				<img
					src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif">
			</div>
		</div>
	</div>
</div>