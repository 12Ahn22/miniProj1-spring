package com.mini.proj1.boards.mapper;

import java.util.List;

import com.mini.proj1.boards.BoardVO;
import com.mini.proj1.paging.PageRequestVO;

public interface BoardMapper {
	List<BoardVO> getList(PageRequestVO pageRequestVO);

	BoardVO view(BoardVO boardVO);

	int update(BoardVO boardVO);

	int delete(BoardVO boardVO);

	int insert(BoardVO boardVO);

	int getTotalCount(PageRequestVO pageRequestVO);

}
