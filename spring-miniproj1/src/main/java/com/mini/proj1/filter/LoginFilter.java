package com.mini.proj1.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mini.proj1.entity.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginFilter implements Filter {
	// 로그인 필터 클래스의 필드들
	// actionSet - 들어오는 요청들 중에서 필터가 필요하지 않는 요청 경로를 저장할 set, ex) /mini/login
	// webSet - 서버에서 제공하는 리소스들의 경로, 아마 리소스는 항상 클라이언트에서 접근 가능해야하기 때문에 설정하는듯.  
	Set<String> actionSet = new HashSet<String>();
	Set<String> webSet = new HashSet<String>();
	
    public LoginFilter() {
    }

	public void destroy() {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 요청과 응답에 대한 객체 가져오기 
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		MemberVO loginVO = (MemberVO) session.getAttribute("loginMember");
		
		String url = req.getRequestURI();
		log.info("FILTER URL : {}", url);
		
		// 필터링
		// actionSet에 없고, webSet에도 없는 경우에 필터링
		if(!actionSet.contains(url) && !webSet.stream().anyMatch((e)-> url.startsWith(e))) {
			if (loginVO == null) {
				//로그인 되지 않았으면 로그인 페이지로 이동한다
				res.sendRedirect("/member/loginForm");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		log.info("LOGIN FILTER INIT");
		
		// LoginFilter 클래스 필드들에 값을 세팅
		actionSet.add("/");
		actionSet.add("/intro.jsp");
		actionSet.add("/member/loginForm");
		actionSet.add("/member/login");
		actionSet.add("/member/insertForm");
		actionSet.add("/member/insert");
		
		actionSet.add("/board/list");
		actionSet.add("/board/view");
		actionSet.add("/board/jsonView");
		
		webSet.add("/resources");
	}

}
