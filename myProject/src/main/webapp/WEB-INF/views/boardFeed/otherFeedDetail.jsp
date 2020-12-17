<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/layout_board.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/feedDetailStyle.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	var mem_num = $
	{
		user.mem_num
	};
	$(document).ready(function() {
		commentList();
		$('#submit_comment').click(function() {
			if ($('#comment').val() == '') {
				alert('댓글 내용을 입력해주세요!');
				return;
			}
			$.ajax({
				url : 'submit_feedcomment.do',
				type : 'post',
				data : {
					comment : $('#comment').val(),
					feed_num : $('#feed_num').val(),
					'mem_num' : mem_num
				},
				dataType : 'json',
				cache : false,
				timeout : 30000,
				success : function(data) {
					if (data == 1) {
						$('#comment').val('');
					}
					commentList();
				},
				error : function() {
					alert('네트워크 오류 발생!');
				}
			});
		});
	});

	function commentList() {
		$
				.ajax({
					url : 'list_feedcomment.do',
					type : 'get',
					data : {
						feed_num : $('#feed_num').val()
					},
					success : function(data) {
						var a = '';
						$
								.each(
										data,
										function(key, value) {
											a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
											a += '<div><b>' + value.mem_id
													+ '</b>';
											if (value.mem_auth == 1) {
												a += '&nbsp;일반회원';
											} else if (value.mem_auth == 2) {
												a += '&nbsp;트레이너';
											} else if (value.mem_auth == 0) {
												a += '&nbsp;관리자';
											}
											a += '&nbsp;' + value.str_date;
											if (value.mem_num == mem_num) {
												a += '&nbsp;|&nbsp;<span class="commentModify'+value.feedc_num+'"><a onclick="commentUpdate('
														+ value.feedc_num
														+ ',\''
														+ value.feedc_comment
														+ '\');">수정</a>';
												a += '&nbsp;|&nbsp;<a onclick="commentDelete('
														+ value.feedc_num
														+ ');">삭제</a> </span>';
											}
											a += '<div class="commentContent'+value.feedc_num+'"> <p>'
													+ value.feedc_comment
													+ '</p></div>';
											a += '</div></div>';
										});

						$(".commentList").html(a);
					},
					error : function(request, status, error) {
						alert("code = " + request.status + " message = "
								+ request.responseText + " error = " + error); // 실패 시 처리
					}
				});
	};

	function commentUpdate(feedc_num, feedc_comment) {
		var a = '';
		var b = '';
		a += '<a onclick="commentUpdateCommit(' + feedc_num + ');">수정완료</a>';
		a += '&nbsp;|&nbsp;<a onclick="commentDelete(' + feedc_num
				+ ');">삭제</a>';
		b += '<div class="input-group">';
		b += '<input type="text" class="form-control" id="content_'+feedc_num+'" value="'+feedc_comment+'"/>';
		b += '</div>';
		$('.commentModify' + feedc_num).html(a);
		$('.commentContent' + feedc_num).html(b);
	}

	function commentUpdateCommit(feedc_num) {
		var update_comment = $('#content_' + feedc_num).val();
		$.ajax({
			url : 'update_feedcomment.do',
			type : 'post',
			data : {
				'feedc_num' : feedc_num,
				'update_comment' : update_comment
			},
			dataType : 'json',
			cache : false,
			timeout : 30000,
			success : function(data) {
				if (data == 1) {
					alert('댓글 수정 완료');
					commentList();
				}
			},
			error : function() {
				alert('네트워크 오류 발생!');
			}
		});
	}

	function commentDelete(feedc_num) {
		var choice = window.confirm('삭제하시겠습니까?');
		if (!choice) {
			return;
		}
		$.ajax({
			url : 'delete_feedcomment.do',
			type : 'post',
			data : {
				'feedc_num' : feedc_num
			},
			dataType : 'json',
			cache : false,
			timeout : 30000,
			success : function(data) {
				if (data == 1) {
					commentList();
				}
			},
			error : function() {
				alert('댓글삭제 네트워크오류');
			}
		});
	}
</script>

<!-- ------------------------body------------------- -->
<!-- 프로필 사진, 프로필 아이디 보이기 (클릭가능)-->

<div id="feedDetail">
	<div id="userInfo">
		<div id="memberPhoto">
			<c:if test="${empty member.mem_picName }">
				<img
					src="${pageContext.request.contextPath }/resources/images/blank.jpg"
					width="150" height="150" class="my-photo">
			</c:if>
			<c:if test="${!empty member.mem_picName }">
				<img
					src="${pageContext.request.contextPath }/member/photoOtherView.do?mem_num=${member.mem_num}"
					width="150" height="150" class="my-photo">
			</c:if>
		</div>
		<div id="userText">
			<c:if test="${user.mem_num == feed.mem_num}">
				<h2><a href="feedList.do">${feed.mem_id}</a></h2>
			</c:if>
			<c:if test="${user.mem_num != feed.mem_num}">
				<h2><a href="otherFeedList.do?mem_num=${feed.mem_num }">${feed.mem_id}</a></h2>
			</c:if>
		</div>
	</div>
	<div id="content">
		<hr style="width: 100%; border: 2px solid #0de0df;">
		<!-- 타입 보이기 -->
		<c:if test="${feed.feed_type == 1 }">
			<p id="feedType">[ 식단 기록 ]</p>
		</c:if>
		<c:if test="${feed.feed_type == 2 }">
			<p id="feedType">[ 운동 기록 ]</p>
		</c:if>
		<!-- 사진 미리보기 -->
		<div id="photo">
			<c:if test="${empty feed.feed_filename }">
				<p>저장한 사진이 없습니다</p>
			</c:if>
			<c:if test="${!empty feed.feed_filename }">
				<!-- photoview 부분은 수정을 해줘야 한다 어떤 controller에서 사용하는지 확인  -->
				<img src="photoView.do?feed_num=${feed.feed_num}"
					style="width: 700px;">
			</c:if>
		</div>
		<!-- content 보이기 -->
		<div id="textContent">
			<p id="acc">>></p>
			<p>${feed.feed_content}</p>
		</div>
		<!-- 취소(목록으로 이동), 수정 버튼 -->
		<div id="buttonContainer">
			<%--수정 삭제의 경우는 로그인이 되어있고 로그인한 회원번호와 작성자 회원번호가 일치해야 함 --%>
			<c:if test="${!empty user && user.mem_num == feed.mem_num}">
				<input type="button" value="수정"
					onclick="location.href='feedUpdate.do?feed_num=${feed.feed_num}'">
				<input type="button" value="삭제" id="delete_btn">
				<script>
					var delete_btn = document.getElementById('delete_btn');
					//이벤트 연결
					delete_btn.onclick = function() {
						var choice = window.confirm('삭제하시겠습니까?');
						if (choice) {
							location.href = 'feedDelete.do?feed_num=${feed.feed_num}';
						}
					};
				</script>
			</c:if>
			<input type="button" value="목록" onclick="location.href='feedList.do'">
		</div>
		<hr style="width: 100%; border: 2px solid #0de0df;">
	</div>



	<div id="commentContainer">
		<form id="commentbox">
			<input type="hidden" id="feed_num" value="${feed.feed_num}">
			<input type="text" id="comment" name="comment"
				placeholder="댓글을 입력하세요."> <input type="button"
				id="submit_comment" value="등록">
		</form>
		<h3>댓글</h3>
		<hr>
		<div class="container">
			<div class="commentList"></div>
		</div>
	</div>

</div>