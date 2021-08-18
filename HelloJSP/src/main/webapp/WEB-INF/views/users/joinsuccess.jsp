<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!-- 동적 페이지 인클루드 -->
<jsp:include page="/WEB-INF/views/includes/header.jsp">
	<jsp:param value="가입 성공" name="message" />
</jsp:include>

<!-- 정적 페이지 인클루드 -->
<%@ include file="/WEB-INF/views/includes/footer.jsp"%>