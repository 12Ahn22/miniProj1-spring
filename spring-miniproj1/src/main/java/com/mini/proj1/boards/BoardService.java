package com.mini.proj1.boards;

import java.sql.SQLException;
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
		BoardVO board = null;
		// boardMapper.increaseViewCount(boardVO.getBno());
		return boardMapper.view(boardVO.getBno());
	}
//	
//	public BoardVO fetchUpdateFormData(BoardVO boardVO) {
//		BoardVO board = null;
//		try {
//			board =  boardDAO.view(boardVO.getBno());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return board;
//	}
//
//	public int delete(BoardVO boardVO) {
//		try {
//			boardDAO.delete(boardVO.getBno());
//			BaseDAO.conn.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			try {
//				BaseDAO.conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			return 0;
//		}
//		return 1;
//	}
//
//	public int update(BoardVO boardVO) {
//		try {
//			boardDAO.update(boardVO);
//			BaseDAO.conn.commit();
//		} catch (Exception e) {
//			try {
//				BaseDAO.conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			return 0;
//		}
//		return 1;
//	}
//
//	public int insert(BoardVO boardVO) {
//		try {
//			boardDAO.insert(boardVO);
//			BaseDAO.conn.commit();
//		} catch (Exception e) {
//			try {
//				BaseDAO.conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			return 0;
//		}
//		return 1;
//	}

}
