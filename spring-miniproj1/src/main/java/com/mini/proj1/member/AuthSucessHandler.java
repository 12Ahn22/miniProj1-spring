package com.mini.proj1.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.mini.proj1.member.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class AuthSucessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
    public void onAuthenticationSuccess(
    		HttpServletRequest request
    		, HttpServletResponse response
    		, Authentication authentication //로그인한 사용자 정보가 있는 객체 
    		) throws IOException, ServletException {
        
		//로그인 한 마지막 시간 수정 
		memberMapper.updateMemberLastLogin(authentication.getName());
		
		log.info("authentication -> {}", authentication);
		
		//성공시 이동할 주소 
        setDefaultTargetUrl("/board/list");
        
        memberMapper.loginCountClear(authentication.getName());
        
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
