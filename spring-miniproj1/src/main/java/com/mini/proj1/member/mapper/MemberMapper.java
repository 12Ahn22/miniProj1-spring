package com.mini.proj1.member.mapper;

import java.util.List;
import java.util.Map;

import com.mini.proj1.entity.HobbyVO;
import com.mini.proj1.entity.MemberVO;
import com.mini.proj1.paging.PageRequestVO;

public interface MemberMapper {
	List<MemberVO> getList(PageRequestVO pageRequestVO);

	MemberVO view(MemberVO memberVO);

	int update(MemberVO memberVO);

	int delete(MemberVO memberVO);

	int insert(MemberVO memberVO);

	int getTotalCount(PageRequestVO pageRequestVO);

	MemberVO checkDuplicateId(MemberVO member);

	List<HobbyVO> getMemberHobbies(MemberVO member);

	MemberVO login(MemberVO member);

	int updateMemberLastLogin(String name);
	
	//로그인시 비밀 번호 틀린 회수를 1 증가 함
	//틀린 횟수가 5회 이상이면 계정을 잠근다  
	void loginCountInc(MemberVO resultVO);
	
	//로그인 성공이 비밀 번호 틀린 회수를 초기화 함  
	void loginCountClear(String email);
}
