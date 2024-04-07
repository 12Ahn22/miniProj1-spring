package com.mini.proj1.boards.mapper;

import java.util.List;

import com.mini.proj1.boards.BoardVO;

public interface BoardMapper {
	List<BoardVO> getList(BoardVO boardVO);

	BoardVO view(Integer bno);

}
