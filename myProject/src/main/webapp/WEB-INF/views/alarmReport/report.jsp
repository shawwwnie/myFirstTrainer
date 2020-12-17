<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/alarm_report.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){

	$('#others').click(function(){
		$('#text').show();
	});
	
	$('#report_submit').submit(function(){

		if($(':radio[name="alarm"]:checked').length<1){
			alert('신고이유를 선택해 주세요!');
			return false;
		}
			
		var value = $('input[name="alarm"]:checked').val();
		var text = $('#text').val();
		if(value == 7){
			$('input[name="alarm"]:checked').val(text);
		}
	});
	
});
</script>
</head>
<body>
	<form id="report_submit" method="post" action="submitReport.do">
		<input type="hidden" name="board_type" value=0>
		<input type="hidden" name="board_num" value="${board_num}">
		<ul>
			<li>			
				<input type="radio" name="alarm" value="욕설" id="alarm1">
				<label for="alarm1">욕설, 비방, 차별, 혐오</label>
			</li>
			<li>
				<input type="radio" name="alarm" value="홍보" id="alarm2">
				<label for="alarm2">홍보, 영리목적</label>
			</li>
			<li>
				<input type="radio" name="alarm" value="불법" id="alarm3">
				<label for="alarm3">불법 정보</label>
			</li>
			<li>
				<input type="radio" name="alarm" value="음란" id="alarm4">
				<label for="alarm4">음란, 청소년 유해</label>
			</li>
			<li>
				<input type="radio" name="alarm" value="개인정보" id="alarm5">
				<label for="alarm5">개인 정보 노출,유포, 거래</label>
			</li>
			<li>
				<input type="radio" name="alarm" value="도배스팸" id="alarm6">
				<label for="alarm6">도배, 스팸</label>
			</li>
			<li>
				<input type="radio" name="alarm" value="7" id="others">
				<label for="others">기타</label><br>
				<textarea id="text" style="display:none;" placeholder="신고내용을 기재해주세요"></textarea>
		</ul>
		<div class="report_button">
			<input type="submit" value="신고하기">
			<input type="button" value="취소" id="cancel" onclick="self.close();">
		</div>
	</form>
</body>
</html>