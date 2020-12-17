<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/layout_myPersonalList.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#photo_btn').click(function() {
							//이미지 파일 선택  태그가 노출
							$('#photo_choice').show();
							//수정버튼을 숨김
							$(this).hide();
						});

						//원래 이미지 보관
						var photo_path;
						//변경 이미지를 보관
						var my_photo;
						$('#upload').change(function() {
							var upload = document.getElementById('upload');
							my_photo = upload.files[0];
							if (my_photo) {
								var reader = new FileReader();
								reader.readAsDataURL(upload.files[0]);

								//사진업로드 전 미리보기 처리
								reader.onload = function() {
									//원래 이미지 보관
									photo_path = $('.my-photo').attr('src');
									//변경된 이미지를 미리보기 셋팅
									$('.my-photo').attr('src', reader.result);
								};
							}
						});

						//이미지 초기화
						$('#photo_reset').click(function() {
							$('.my-photo').attr('src', photo_path);
							$('#upload').val('');
							$('#photo_choice').hide();
							$('#photo_btn').show();
						});

						$('#photo_submit')
								.click(
										function() {
											if ($('#upload').val() == '') {
												alert('파일을 선택하세요');
												$('#upload').focus();
												return;
											}
											//파일 전송
											var form_data = new FormData();
											form_data
													.append('upload', my_photo);
											$
													.ajax({
														data : form_data,
														type : 'POST',
														url : 'updateMyPhoto.do',
														dataType : 'json',
														cache : false,
														contentType : false,
														encType : 'multipart/form-data',
														processData : false,
														success : function(data) {
															if (data.result == 'logout') {
																alert('로그인 후 사용하세요');
															} else if (data.result == 'success') {
																alert('프로필 사진이 수정 되었습니다.');
																$('#upload')
																		.val('');
																$(
																		'#photo_choice')
																		.hide();
																$('#photo_btn')
																		.show();
															} else {
																alert('파일전송 오류 발생');
															}
														},
														error : function(
																request,
																status, error) {
															alert("code = "
																	+ request.status
																	+ " message = "
																	+ request.responseText
																	+ " error = "
																	+ error); // 실패 시 처리
														}
													});

										});
						//나의 한마디 수정 하는 부분
						var be_intro;
						var af_intro;
						//수정 버튼을 누르면
						$('#intro_btn').click(function() {
							$('#intro_change').show();
							$('#intro').focus();
							$('#intro').removeAttr("readonly");
							be_intro = $('#intro').val();
							$(this).hide();
						});

						//글 초기화
						$('#intro_reset').click(function() {

							$('#intro_change').hide();
							$('#intro_btn').show();
							$('#intro').prop('readonly', true);
							$('#intro').val(be_intro);
						});

						//한마디 수정
						$('#intro_submit').click(function() {
							//파일 전송
							af_intro = $('#intro').val();
							$.ajax({
								url : 'updateIntro.do',
								type : 'POST',
								data : {
									af_intro : af_intro
								},
								dataType : 'json',
								cache : false,
								success : function(data) {
									if (data.result == 'logout') {
										alert('로그인 후 사용하세요');
									} else if (data.result == 'success') {
										alert('나의 한마디가 수정 되었습니다.');
										$('#intro_change').hide();
										$('#intro_btn').show();
									} else {
										alert('파일전송 오류 발생');
									}
								},
								error : function() {
									alert('네트워크 오류 발생');
								}
							});

						});

						//==============무한스크롤이면 좋겠지만 ===============
						var currentPage;
						var count;
						var rowCount;

						//글 목록
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
											pageNum : pageNum
										},
										url : 'myPersonalListAjax.do',
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

																	output += '<p class="grid__author">';
																	switch(item.feed_auth){
																		case 1:
																			output += '나만보기';
																			break;
																		case 2:
																			output += '트레이너만 공개';
																			break;
																		case 3:
																			output += '팔로워 공개';
																			break;
																		case 4:
																			output += '전체 공개';
																			break;
																		default :
																			break;
																	}
																	output += '</p>';
																	output += '</div>';
																	output += '<div class="mt-auto">';
																	output += '<span class="grid__tag">'
																			+ item.feed_content
																			+ '</span>';
																	output += '</div>';
																	output += '</div>';
																	output += '</div>';

																	//문서 객체에 추가
																	$(
																			'.masonry')
																			.append(
																					output);
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

									if (scrollTop >= docHeight - winHeight)
										if (currentPage >= Math.ceil(count
												/ rowCount)) {
											//다음 페이지가 없음
											return;
										} else {
											//다음 페이지가 존재
											var pageNum = currentPage + 1;
											selectData(pageNum);
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

<style>
.write_Btn {
	width: 50px;
	height: 50px;
	background-image: url('../resources/images/Btn_write_board.png');
	border: none;
	border-radius: 25px;
}
</style>
<div class="personal_feed">

	<!-- 아이디 노출 -->
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
						<img src="${pageContext.request.contextPath }/member/photoView.do"
							width="120" height="120" class="my-photo">
					</c:if>
					<!-- 프로필 사진 수정버튼 -->
					<input type="button" value="수정" id="photo_btn">
					<div id="photo_choice" style="display: none;">
						<input type="file" id="upload"
							accept="image/gif,image/png,image/jpeg"> <input
							type="button" value="전송" id="photo_submit"> <input
							type="button" value="취소" id="photo_reset">
					</div>
				</div>
				<!-- 프로필 소개 노출 -->
				<div class="intro">
					<label for="intro">나의 한마디</label><br>
					<c:if test="${empty member.mem_intro }">
						<input type="text" id="intro" name="intro"
							placeholder="하고 싶은 한 마디를 적어주세요" readonly />
					</c:if>
					<c:if test="${!empty member.mem_intro }">
						<input type="text" id="intro" value=${member.mem_intro } readonly />
					</c:if>
					<!-- 프로필 소개 수정버튼 -->
					<input type="button" value="수정" id="intro_btn">
					<div id="intro_change" style="display: none;">
						<input type="button" value="전송" id="intro_submit"> 
						<input type="button" value="취소" id="intro_reset">
					</div>
				</div>
			</div>
		</li>
		<li class="myId">
			<h1>${member.mem_id }</h1>
		</li>
	</ul>
	<hr size="1" width="100%">
	<!-- 팔로우 관련 태그 -->
	<div class="tabs">
		<div class="tab-2">
			<label for="tab2-1">내가 팔로우하는 사람</label> <input id="tab2-1"
				name="tabs-two" type="radio" checked="checked">
			<div>
				<c:forEach var="myFollower" items="${followerList}">
					<a href="otherFeedList.do?mem_num=${myFollower.mem_num}">@${myFollower.mem_id}</a>
				</c:forEach>
			</div>
		</div>
		<div class="tab-2">
			<label for="tab2-2">나를 팔로우하는 사람</label> <input id="tab2-2"
				name="tabs-two" type="radio">
			<div>
				<c:forEach var="followMe" items="${followMeList}">
					<a href="otherFeedList.do?mem_num=${followMe.mem_num}">@${followMe.mem_id}</a>
				</c:forEach>
			</div>
		</div>
	</div>

	<!-- 팔로우 관련 태그 -->

	<!-- 글쓰기 버튼 -->
	<p class="align-right">
		<input type="button" class="write_Btn"
			onclick="location.href='feedWrite.do'">
	</p>
</div>
<!-- 하단 리스트 영역 -->
<div>

	<div class="masonry">
		<!-- Ajax 영역 -->
	</div>

	<!-- feed로딩시 로딩 이미지 -->
	<div id="loading" style="display: none;">
		<img src="/MyFirstTrainer/resources/images/ajax-loader.gif">
	</div>
	<!-- feed로딩시 로딩 이미지 -->


	<!-- TOP 버튼 -->
	<div style="position: fixed; bottom: 80px; right: 20px;">
		<input type="button" class="topButton" value="TOP"
			onclick="location.href='#main'">
	</div>
	<!-- TOP 버튼 -->

</div>





