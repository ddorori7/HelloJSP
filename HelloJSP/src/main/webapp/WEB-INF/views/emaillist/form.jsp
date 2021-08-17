<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emaillist (Model 2) 가입</title>
</head>
<body>
	<h1>메일링 리스트 가입(Model 2)</h1>
	<p>메일링 리스트에 가입하려면<br/>
	아래 항목을 기입하고 등록 버튼을 눌러 주세요</p>
	<!-- action: 요청을 처리할 페이지(서블릿/JSP)
		method: 요청 방식 -->
	<form action="<%= request.getContextPath() %>/el"
		method="POST">
<!-- 
<form> 태그의 method 속성은 폼 데이터(form data)가 서버로 제출될 때 사용되는 HTTP 메소드를 명시합니다.
method 속성은 속성값으로는 GET과 POST 두 가지 중 하나를 선택할 수 있습니다.
GET 방식은 URL에 폼 데이터를 추가하여 서버로 전달하는 방식입니다.
GET 방식의 HTTP 요청은 브라우저에 의해 캐시되어(cached) 저장됩니다.
또한, GET 방식은 보통 쿼리 문자열(query string)에 포함되어 전송되므로, 길이의 제한이 있습니다.
따라서 보안상 취약점이 존재하므로, 중요한 데이터는 POST 방식을 사용하여 요청하는 것이 좋습니다.

POST 방식은 폼 데이터를 별도로 첨부하여 서버로 전달하는 방식입니다.
POST 방식의 HTTP 요청은 브라우저에 의해 캐시되지 않으므로, 브라우저 히스토리에도 남지 않습니다.
또한, POST 방식의 HTTP 요청에 의한 데이터는 쿼리 문자열과는 별도로 전송됩니다.
따라서 데이터의 길이에 대한 제한도 없으며, GET 방식보다 보안성이 높습니다. 
-->
		
		<!-- hidden은 사용자 입력은 아니지만 전송해야 할 데이터 -->
		<input type="hidden" value="add" name="a" /><!-- a = add -->
		<label for="last_name">성</label>
		<input type="text" name="last_name" id="last_name" /><br />
		<label for="first_name">이름</label>
		<input type="text" name="first_name" id="first_name" /><br />
		<label for="email">이메일</label>
		<input type="text" name="email" id="email" /><br />
		
		<!-- 전송 버튼 -->
		<input type="submit" value="등록" />
	</form>
	
	<!-- 리스트로 돌아가기  -->
	<p>
		<a href="<%= request.getContextPath() %>/el/">목록 보기</a>
	</p>
</body>
</html>