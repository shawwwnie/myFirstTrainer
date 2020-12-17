<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/alarm_report.css">
</head>
<body>
<div id="report_submit">
	신고가 접수되었습니다.<br>
	10명 이상이 신고할 시, 해당 글은 비공개됩니다.
	<div class="report_button">
		<input type="button" value="확인" onclick="self.close();">
	</div>
</div>
</body>
</html>