<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RATTY | 게시글 등록</title>
<jsp:include page="/WEB-INF/views/include/bootStrap.jsp" />
</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/include/layoutHeader.jsp" />
		<main>
			<h2>게시물 등록</h2>
			<form id="iForm">
				<label for="title">제목:</label><br>
				<input type="text" id="title" name="title"><br>
				<label for="author">작성자:</label><br>
				<input type="text" id="author" name="author" value="${principal.id}" readonly><br>
				<label for="content">내용:</label><br>
				<textarea id="content" name="content" rows="4" cols="50"></textarea><br><br>
				<input type="submit" value="생성">
				<a href="list">취소</a>
		</form>
		</main>
	</div>
	<script>
		const uForm = document.getElementById("iForm");
		uForm.addEventListener("submit",(e)=>{
			e.preventDefault();
			const param = {
				action:"insert",
				author:author.value,
				title:title.value,
				content:content.value
			};

			fetch("insert", {
								method: "POST",
								body: JSON.stringify(param),
								headers: { "Content-type": "application/json; charset=utf-8" }
							}).then((res) => res.json())
								.then((data) => {
								if (data.status === 204) {
									alert("게시글 생성에 성공했습니다.");
									// 페이지 리다이렉트
									location = "list"
								} else {
									alert(data.statusMessage);
								}
							});
		});
	</script>
</body>
</html>