package com.mini.proj1.boards;

import java.util.List;

import org.springframework.stereotype.Service;
import com.mini.proj1.boards.mapper.BoardMapper;
import com.mini.proj1.paging.PageRequestVO;
import com.mini.proj1.paging.PageResponseVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
	private final BoardMapper boardMapper;
	
	public PageResponseVO <BoardVO> list(PageRequestVO pageRequestVO) throws Exception {
		List<BoardVO> list = null;
		PageResponseVO<BoardVO> pageResponseVO = null;
		int total = 0;
		try {
			list = boardMapper.getList(pageRequestVO);
			total = boardMapper.getTotalCount(pageRequestVO);
			log.info("list {}", list);
			log.info("total {}", total);
	        pageResponseVO = PageResponseVO.<BoardVO>withAll()
	                .list(list)
	                .total(total)
	                .size(pageRequestVO.getSize())
	                .pageNo(pageRequestVO.getPageNo())
	                .build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return pageResponseVO;
	}
	
	public BoardVO view(BoardVO boardVO) {
		// boardMapper.increaseViewCount(boardVO.getBno());
		return boardMapper.view(boardVO);
	}

	
	public BoardVO fetchUpdateFormData(BoardVO boardVO) {
		return boardMapper.view(boardVO);
	}
	
	public int update(BoardVO boardVO) {
		return boardMapper.update(boardVO);
	}
	

	public int delete(BoardVO boardVO) {
		return boardMapper.delete(boardVO);
	}


	public int insert(BoardVO boardVO) {
		return boardMapper.insert(boardVO);
	}

}
