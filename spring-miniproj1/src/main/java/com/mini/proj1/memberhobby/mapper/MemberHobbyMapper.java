package com.mini.proj1.memberhobby.mapper;

import java.util.List;
import java.util.Map;

import com.mini.proj1.entity.HobbyVO;
import com.mini.proj1.entity.MemberHobbyVO;
import com.mini.proj1.entity.MemberVO;
import com.mini.proj1.paging.PageRequestVO;

public interface MemberHobbyMapper {

	void deleteAll(MemberVO member);

	void insert(MemberHobbyVO memberHobbyVO);

}
