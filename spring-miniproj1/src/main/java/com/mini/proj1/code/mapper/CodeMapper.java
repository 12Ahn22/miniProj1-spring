package com.mini.proj1.code.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mini.proj1.code.CodeVO;

@Mapper
public interface CodeMapper {
	List<CodeVO> getList();
}
