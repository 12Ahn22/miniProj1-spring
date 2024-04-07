<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>RATTY | 게시글 목록</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
  </head>
  <body>
    <div class="container">
      <jsp:include page="/layoutHeader.jsp" />
      <main>
        <h1>게시물 리스트</h1>
        <a class="btn btn-primary mb-2" href="insertForm">새 글 작성하기</a>
        <form id="searchForm" method="get" action="list">
        	<select id="size" name="size" >
				<c:forEach var="size" items="${sizes}">
        			<option value="${size.codeid}" ${pageRequestVO.size == size.codeid ? 'selected' : ''} >${size.name}</option>
        		</c:forEach>
        	</select>
          <input
            type="text"
            name="searchKey"
            id="searchKey"
            placeholder="Search..."
          />
          <input type="submit" value="검색" />
        </form>
        <table class="table">
          <thead>
            <tr>
              <th scope="col">no</th>
              <th scope="col">제목</th>
              <th scope="col">작성자</th>
              <th scope="col">작성일</th>
              <th scope="col">조회수</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="board" items="${pageResponseVO.list}">
              <tr>
                <td>${board.bno}</td>
                <td><a href="view?bno=${board.bno}">${board.title}</a></td>
                <td>${board.author}</td>
                <td>${board.createdAt}</td>
                <td>${board.viewCount}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        <!--  페이지 네비게이션 바 출력  -->
        <div class="float-end">
          <ul class="pagination flex-wrap">
            <c:if test="${pageResponseVO.prev}">
              <li class="page-item">
                <a class="page-link" data-num="${pageResponseVO.start -1}"
                  >이전</a
                >
              </li>
            </c:if>

            <c:forEach
              begin="${pageResponseVO.start}"
              end="${pageResponseVO.end}"
              var="num"
            >
              <li
                class="page-item ${pageResponseVO.pageNo == num ? 'active':''}"
              >
                <a class="page-link" data-num="${num}">${num}</a>
              </li>
            </c:forEach>

            <c:if test="${pageResponseVO.next}">
              <li class="page-item">
                <a class="page-link" data-num="${pageResponseVO.end + 1}"
                  >다음</a
                >
              </li>
            </c:if>
          </ul>
        </div>
      </main>
    </div>
  </body>
  <script>
    document
      .querySelector('.pagination')
      .addEventListener('click', function (e) {
        e.preventDefault();

        const target = e.target;

        if (target.tagName !== 'A') {
          return;
        }
        //dataset 프로퍼티로 접근 또는 속성 접근 메서드 getAttribute() 사용 하여 접근 가능
        //const num = target.getAttribute("data-num")
        const num = target.dataset['num'];
        const size = document.getElementById("size").value;
        location = `?pageNo=\${num}&size=\${size}`;
      });

    document.querySelector('#size').addEventListener('change', (e) => {
      searchForm.submit();
    });

    const searchForm = document.getElementById('searchForm');
    searchForm.addEventListener('submit', (e) => {
      e.preventDefault();
      searchForm.submit();
    });

		const url = new URL(window.location.href);
		const urlParams = url.searchParams;
		if(urlParams.get("searchKey")){
			searchKey.value = urlParams.get("searchKey");
		}
  </script>
</html>
