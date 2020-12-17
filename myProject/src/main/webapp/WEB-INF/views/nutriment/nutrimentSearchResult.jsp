<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>	
	<table>
			<c:forEach var="nutriment" items="${list}">
				<tr>
					<td><a href="nutrimentDetail.do?food_num=${nutriment.food_num}">${nutriment.food_name}</a></td>
				</tr>
			</c:forEach>
	</table>
</div>
<div class="align-center">${pagingHtml}</div>
