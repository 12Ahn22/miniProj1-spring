package com.mini.proj1.boards;

import java.util.List;
import org.springframework.stereotype.Service;
import com.mini.proj1.boards.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
	private final BoardMapper boardMapper;
	
	public List<BoardVO> list(BoardVO boardVO) throws Exception {
		List<BoardVO> list = null;
		try {
			list = boardMapper.getList(boardVO);
			log.info("list {}", list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return list;
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
