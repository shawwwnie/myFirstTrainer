<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/layout_otherPersonalList.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						var currentPage;
						var count;
						var rowCount;
						var mem_num = $('#mem_num').val();
						//댓글 목록
						function selectData(pageNum) {
							currentPage = pageNum;

							if (pageNum == 1) {
								//처음 호출시는 해당 ID의 div의 내부 내용물을 제거
								$('.masonry').empty();

							}

							//로딩 이미지 노출

							$('#loading').show();

							$
									.ajax({

										type : 'post',
										data : {
											pageNum : pageNum,
											mem_num : mem_num
										},
										url : 'otherPersonalListAjax.do',
										dataType : 'json',
										cache : false,
										timeout : 30000,
										success : function(data) {
											//로딩 이미지 감추기
											$('#loading').hide();
											count = data.count;
											rowCount = data.rowCount;
											var list = data.list;

											if (count < 0 || list == null) {
												alert('목록 호출 오류 발생!');
											} else {
												$(list)
														.each(
																function(index,
																		item) {

																	var output = '<div class="grid">';

																	output += '<img ';
																	if (item.feed_filename == null) {
																		output += 'src="../resources/images/blank.jpg">';
																	} else {
																		output += 'src="photoView.do?feed_num='
																				+ item.feed_num
																				+ '">';
																	}

																	output += '<div class="grid__body">';

																	output += '<div class="relative">';
																	output += '<a class="grid__link" href="feedDetail.do?feed_num='
																			+ item.feed_num
																			+ '"></a>';

																	output += '<h1 class="grid__title">';
																	if (item.feed_type == 1) {
																		output += '식단';
																	} else if (item.feed_type == 2) {
																		output += '운동';
																	}
																	output += ' </h1>';

																	output += '<p class="grid__author">'
																			+ item.feed_auth
																			+ '</p>';
																	output += '</div>';
																	output += '<div class="mt-auto">';
																	output += '<span class="grid__tag">'
																			+ item.feed_content
																			+ '</span>';
																	output += '</div>';
																	output += '</div>';
																	output += '</div>';

																	//문서 객체에 추가
																	$('.masonry').append(output);
																});

											}
										},
										error : function() {
											//로딩 이미지 감추기
											$('#loading').hide();
											alert('네트워크 오류');
										}

									});
						}

						//스크롤시 데이터 추가
						$(window).scroll(
								function() {

									var scrollTop = $(document).scrollTop();
									var docHeight = $(document).height();
									var winHeight = $(window).height();

									if (scrollTop >= docHeight - winHeight) {
										if (currentPage >= Math.ceil(count
												/ rowCount)) {
											//다음 페이지가 없음
											return;
										} else {
											//다음 페이지가 존재
											var pageNum = currentPage + 1;
											selectData(pageNum);
										}
									}

								});

						//1페이지 호출
						selectData(1);

					});
	/*
	 * 사진 수정 버튼, 소개글 수정 버튼 안보이도록 설정
	 이미지 수정 처리, 소개글 수정 처리
	 */
</script>

<div class="personal_feed">
	<input id="mem_num" style="display: none" value="${member.mem_num }" />
	<!-- 프로필 사진 노출 -->
	<ul id="upperNotice">
		<li id="image" class="profile_image">
			<div class="upperIntro">
				<div class="myPic">
					<c:if test="${empty member.mem_picName }">
						<img
							src="${pageContext.request.contextPath }/resources/images/blank.jpg"
							width="120" height="120" class="my-photo">
					</c:if>
					<c:if test="${!empty member.mem_picName }">
						<img src="${pageContext.request.contextPath }/member/photoOtherView.do?mem_num=${member.mem_num}"
							width="120" height="120" class="my-photo">
					</c:if>
				</div>
					<!-- 프로필 소개 노출 -->
					<div class="intro">
						<label for="intro">나의 한마디</label><br>
						<c:if test="${empty member.mem_intro }">

							<input type="text" id="intro" name="intro"
								placeholder="등록된 소개글이 없습니다." readonly style="border: none;" />
						</c:if>
						<c:if test="${!empty member.mem_intro }">

							<input type="text" id="intro" value=${member.mem_intro } readonly style="border: none;" />
						</c:if>
					</div>
			</div>
		</li>
		<li class="myId">
			<h1>${member.mem_id }</h1>
		</li>
	</ul>
	
	<div calss=>
		<!-- 로그인 아이디 일반회원			피드 아이디 트레이너 회원 -->
		<c:if test="${user.mem_auth == 1 && member.mem_auth ==2 }">
			
			<c:if test="${empty member2.follower_num}">
				<button type="button" class="Btn_follow"
					onclick="location.href='insertFollow.do?mem_num=${member.mem_num}'">팔로우하기</button>
			</c:if>
			<c:if test="${!empty member2.follower_num}">
				<button type="button" class="Btn_follow"
					onclick="location.href='deleteFollow.do?mem_num=${member.mem_num}'">팔로우끊기</button>
			</c:if>
			<button type="button" class="Btn_training"
				onclick="location.href='${pageContext.request.contextPath}/trainerList/trainerListDetail.do?mem_num=${member.mem_num}'">트레이닝 신청하기</button>
		</c:if>
		<c:if test="${user.mem_auth == 1 && member.mem_auth ==1 }">
			<c:if test="${empty member2.follower_num}">
				<button type="button" class="Btn_follow"
					onclick="location.href='insertFollow.do?mem_num=${member.mem_num}'">팔로우하기</button>
			</c:if>
			<c:if test="${!empty member2.follower_num}">
				<button type="button" class="Btn_follow"
					onclick="location.href='deleteFollow.do?mem_num=${member.mem_num}'">팔로우끊기</button>
			</c:if>
		</c:if>
		<c:if test="${user.mem_auth == 2 && member.mem_auth ==1 }">
			<c:if test="${empty member2.follower_num}">
				<button type="button" class="Btn_follow"
					onclick="location.href='insertFollow.do?mem_num=${member.mem_num}'">팔로우하기</button>
			</c:if>
			<c:if test="${!empty member2.follower_num}">
				<button type="button" class="Btn_follow"
					onclick="location.href='deleteFollow.do?mem_num=${member.mem_num}'">팔로우끊기</button>
			</c:if>
		</c:if>
		<c:if test="${user.mem_auth == 2 && member.mem_auth ==2 }">
			<c:if test="${empty member2.follower_num}">
				<button type="button" class="Btn_follow"
					onclick="location.href='insertFollow.do?mem_num=${member.mem_num}'">팔로우하기</button>
			</c:if>
			<c:if test="${!empty member2.follower_num}">
				<button type="button" class="Btn_follow"
					onclick="location.href='deleteFollow.do?mem_num=${member.mem_num}'">팔로우끊기</button>
			</c:if>
		</c:if>
	</div>
	<hr size="1" width="100%">
</div>
<div>

	<div class="masonry">
		<!-- Ajax 영역 -->
	</div>

	<!-- feed로딩시 로딩 이미지 -->
	<div id="loading" style="display: none;">
		<img src="/MyFirstTrainer/resources/images/ajax-loader.gif">
	</div>
	<!-- feed로딩시 로딩 이미지 --a>
	
	  
	
 
		
		
  
  
		   
			
				  
  
		   
 
								 
 
						  
						
																				
	 
	  
								
	<!-- TOP 버튼 -->
	<div style="position: fixed; bottom: 80px; right: 20px;">
		<input type="button" class="topButton" value="TOP"
			onclick="location.href='#main'">

	</div>
	<!-- TOP 버튼 -->

</div>





