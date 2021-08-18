<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// example attribute 가져오기
String cookieValue = (String) request.getAttribute("example"); // 어떤 객체인지 알 수 없으니까 String 으로 변환
if (cookieValue == null) {
	cookieValue = "";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Cookie Test Form</h1>

	<h3>Add/Edit Cookie</h3>
	<form method="POST" action="cookies">
		<label for="example">Cookie Value</label> <input type="text"
			name="example" id="example" value="<%=cookieValue%>"> <input
			type="submit" value="쿠키 설정">
	</form>

	<h3>Delete Cookie</h3>
	<a href="<%=request.getContextPath()%>/cookies?a=delete">쿠키 삭제</a>
</body>
</html>