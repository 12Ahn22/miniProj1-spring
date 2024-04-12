package com.mini.proj1;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mini.proj1.entity.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	@GetMapping("/")
	public String home(Authentication authentication) {
//		MemberVO memberVO = (MemberVO) authentication.getPrincipal();
//		log.info("board.list() 함수 호출 ");
//		log.info("memberVO  = {}", memberVO);
//		log.info("member_id = {}", authentication.getName());
//		log.info("authorities = {}", memberVO.getAuthorities());
		return "home";
	}
	
	@GetMapping("/intro")
	public String intro() {
		return "intro";
	}
	
	
}
