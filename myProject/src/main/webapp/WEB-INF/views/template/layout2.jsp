<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout.css">
</head>
<body>
<div id="main">
	<div id="main_header" style="z-index: 2;">
		<tiles:insertAttribute name="header"/>
	</div>
	<div id="main_menu" style="z-index: 1;">
		<tiles:insertAttribute name="left"/>
	</div>
	<div id="layout2_body" style="z-index: 1;">
		<tiles:insertAttribute name="body"/>
	</div>
	<div id="main_footer" style="z-index: 1;">
		<tiles:insertAttribute name="footer"/>
	</div>
</div>
</body>
</html>







