<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
	<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="principal"/>
	</sec:authorize>
	<a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none">
		<svg xmlns="http://www.w3.org/2000/svg" width="40" height="32" class="bi me-2" viewBox="0 0 1024 1024"><path fill="#85C3DE" d="M727.66 431.158c-132.581 0-220.349 47.454-285.804 154.354-19.78 32.31-15.845 76.504-9.405 96.58a100.268 100.268 0 0 0 12.423 25.546c19.887 29.373 55.054 46.888 94.073 46.888h66.83c1.159 17.893-1.967 34.762-15.145 53.976-12.692 18.54-37.807 40.151-56.32 54.81 25.25-.673 52.71-.996 83.24-.996 259.935 0 352.553-150.393 352.553-291.14C936.421 512 882.365 431.158 727.66 431.158z"/><path fill="#231F20" d="m210.432 1012.898-43.574-31.69C273.812 834.156 352.175 810.01 590.686 808.502c21.397-31.26 16.25-56.266 9.378-89.708-3.557-17.138-7.222-34.843-7.222-54.434 0-68.958 25.33-104.636 63.407-136.973l34.897 41.04c-29.453 25.062-44.41 46.781-44.41 95.933 0 14.094 2.938 28.403 6.064 43.547 5.901 28.51 12.8 62.033-1.132 99.463 166.373-10.24 264.543-96.903 264.543-236.194 0-152.845-88.63-247.808-231.29-247.808-83.644 0-153.303 29.696-174.188 39.613a224.876 224.876 0 0 1-20.533 31.34l-41.742-34.116 20.884 17.058-20.91-16.977c.35-.458 36.62-45.999 36.62-97.55 0-34.815-8.946-60.765-26.57-77.069-17.515-16.249-44.786-24.603-81.219-24.953v162.654h-53.895V109.784l24.873-1.914c64.7-4.931 114.095 7.896 146.863 38.239 29.103 26.947 43.843 66.182 43.843 116.628 0 11.102-1.131 21.908-3.072 32.202 37.269-12.584 89.843-25.465 149.046-25.465 173.245 0 285.184 118.433 285.184 301.702 0 140.747-92.618 291.14-352.552 291.14-258.668 0-311.943 19.698-407.121 150.582zm19.106-256.836c-12.046 0-24.388-.566-37.026-1.643l-22.097-1.86-2.425-22.016c-.243-2.398-6.306-58.098-6.306-99.516 0-103.586 21.45-178.904 53.895-259.046V107.79h53.895v274.783l-2.021 4.904c-32.014 78.282-51.874 146.324-51.874 243.55 0 22.879 2.102 51.443 3.826 70.98 99.679 2.802 172.814-35.409 222.451-116.494l48.02 24.091c-11.237 28.133-11.372 51.578-.377 67.854 9.701 14.282 28.645 23.175 49.448 23.175v53.894c-39.02 0-74.186-17.515-94.073-46.888a100.244 100.244 0 0 1-12.423-25.546c-53.22 49.179-121.128 73.943-202.913 73.97zm150.42-230.588c0-34.223-13.231-44.463-29.642-44.463s-29.642 10.24-29.642 44.463c0 34.25 13.23 44.463 29.642 44.463s29.642-10.213 29.642-44.463z"/></svg>
		<span class="fs-4">RATTY</span>
	</a>
	<nav>
		<ul class="nav nav-pills">
			<li class="nav-item"><a class="nav-link" href="/intro">회사소개</a></li>
			<c:choose>
				<c:when test='${empty principal}'>
					<!-- 비로그인 상태일 때 수행할 내용 -->
					<li class="nav-item"><a class="nav-link" href="/member/insertForm">회원가입</a></li>
					<li class="nav-item"><a class="nav-link" href="/member/loginForm">로그인</a></li>
				</c:when>
				<c:otherwise>
					<!-- 로그인 상태일 때 수행할 내용 -->
					<!-- 관리자가 로그인 한 상태일 때 -->
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li class="nav-item"><a class="nav-link" href="/member/list">회원관리</a></li>
					</sec:authorize>
					<li class="nav-item">					
						<form method="post" action="/logout">
				        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				        	<input type="submit" class="nav-link" value="로그아웃" />
				    	</form>
				    </li>
					<li class="nav-item"><a class="nav-link" href="/member/profile">마이페이지</a></li>
				</c:otherwise>
			</c:choose>
			<li class="nav-item"><a class="nav-link" href="/board/list">게시판</a></li>
		</ul>
	</nav>
</header>