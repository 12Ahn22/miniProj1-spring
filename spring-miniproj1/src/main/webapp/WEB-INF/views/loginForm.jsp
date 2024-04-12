<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>RATTY | LOGIN</title>
	<jsp:include page="/WEB-INF/views/include/bootStrap.jsp" />
</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/include/layoutHeader.jsp" />
		<main>
			<h1>LOGIN</h1>
			${error ? exception : ''}
			<form id="loginForm" method="post" action="/login">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div>
					<label for="email">아이디:</label>
					<input type="text" id="email" name="email" required>
				</div>
				<div>
					<label for="password">비밀번호:</label>
					<input type="password" id="password" name="password" required>
				</div>
				<input class="btn btn-primary" type="submit" value="로그인">
				<div>
					<label for="autoLogin">자동로그인체크</label>
					<!-- 체크박스가 하나인 경우 -->
					<input type="checkbox" name="autoLogin" value="Y">
				</div>
			</form>
		</main>
	</div>
</body>
</html>