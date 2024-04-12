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
}
