<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RATTY | 게시글 상세</title>
<jsp:include page="/WEB-INF/views/include/bootStrap.jsp" />
</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/views/include/layoutHeader.jsp" />
		<main>
			<h1>${board.title}</h1>
			<a href="list">목록</a>
			<div>${board.bno}</div>
			<input type="hidden" name="bno" id="bno" value="${board.bno}">
			<input type="hidden" name="author" id="author" value="${board.author}">
			<div><span>작성자: ${board.author}</span><span>조회수: ${board.viewCount}</span><span>작성일: ${board.createdAt}</span></div>
			<div>
				${board.content}
			</div>
			<div>
				<a class="btn btn-primary" href="updateForm?bno=${board.bno}">수정</a>
				<button class="btn btn-secondary" id="deleteBtn">삭제</button>
			</div>
			<c:if test="${isLogin != null}">
				<div>
					<a class="btn btn-primary" href="updateForm?bno=${board.bno}">수정</a>
					<button class="btn btn-secondary" id="deleteBtn">삭제</button>
				</div>
			</c:if>
		</main>
	</div>
	<script>
		const deleteBtn = document.getElementById("deleteBtn");
		if(deleteBtn){
			deleteBtn.addEventListener("click",() => {
						if (confirm("정말 삭제하시겠습니까?")) {
							const param = {
								bno: bno.value,
								author: author.value,
								action: "delete"
							}

							fetch("delete", {
								method: "POST",
								body: JSON.stringify(param),
								headers: { "Content-type": "application/json; charset=utf-8" }
							}).then((res) => res.json())
								.then((data) => {
								if (data.status === 204) {
									alert("게시글 삭제에 성공했습니다.");
									// 페이지 리다이렉트
									location = "list";
								} else {
									alert(data.statusMessage);
								}
							})
						}
					});
		}
	</script>
</body>
</html>