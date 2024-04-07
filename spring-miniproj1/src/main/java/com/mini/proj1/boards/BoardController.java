package com.mini.proj1.boards;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	// 스프링이 의존성을 주입해준다.
	private final BoardService boardService;

	@RequestMapping("list")
	public String list(Model model, BoardVO boardVO) throws Exception {
		log.info("게시판 목록");
		List<BoardVO> list = boardService.list(boardVO);
		// HttpSession session = request.getSession();
		// boolean isLogin = (session.getAttribute("loginMember") != null)? true : false;
		
		model.addAttribute("list", list);
		// model.setAttribute("isLogin", isLogin);
		return "board/boardList";
	}

	@RequestMapping("view")
	public String view(Model model, BoardVO boardVO) {
		log.info("게시판 상세 목록");
		// 게시글 정보
		BoardVO board = boardService.view(boardVO);
		model.addAttribute("board", board);

		return "board/boardView";
	}
//
//	public Map<String,Object> delete(HttpServletRequest request, BoardVO boardVO) {
//		Map<String, Object> map = new HashMap<>();
//		HttpSession session = request.getSession();
//		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember"); // 로그인 한 유저
//		
//		// 로그인 하지않았다면
//		if(loginMember == null) {
//			map.put("status", 404);
//			map.put("statusMessage", "로그인을 해야합니다.");
//			return map;
//		}
//		
//		// 현재 세션의 유저와 입력된 정보의 id가 같지 않다면 수정 불가
//		if(!loginMember.getId().equals("ADMIN")) { // 어드민이 아닐 때만 체크
//			if(!loginMember.getId().equals(boardVO.getAuthor())) { // 로그인 유저와 요청 유저가 다르다면
//				// 실패
//				map.put("status", 404);
//				map.put("statusMessage", "잘못된 요청입니다.");
//				return map;
//			}
//		}
//		
//		int updated = boardService.delete(boardVO);
//		
//		if(updated == 1) { // 성공
//			map.put("status", 204);
//		} else {
//			map.put("status", 404);
//			map.put("statusMessage", "게시글 삭제에 실패하였습니다");
//		}
//		
//		return map;
//	}
//
//	public String updateForm(HttpServletRequest request, BoardVO boardVO) {
//		// 게시글 정보
//		BoardVO board = boardService.fetchUpdateFormData(boardVO);
//		request.setAttribute("board", board);
//		return "boardUpdate";
//	}
//
//	public Map<String, Object> update(HttpServletRequest request, BoardVO boardVO) {
//		Map<String, Object> map = new HashMap<>();
//		HttpSession session = request.getSession();
//		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember"); // 로그인 한 유저
//		
//		// 로그인 하지않았다면
//		if(loginMember == null) {
//			map.put("status", 404);
//			map.put("statusMessage", "로그인을 해야합니다.");
//			return map;
//		}
//		
//		// 현재 세션의 유저와 입력된 정보의 id가 같지 않다면 수정 불가
//		if(!loginMember.getId().equals("ADMIN")) { // 어드민이 아닐 때만 체크
//			if(!loginMember.getId().equals(boardVO.getAuthor())) { // 로그인 유저와 요청 유저가 다르다면
//				// 실패
//				map.put("status", 404);
//				map.put("statusMessage", "잘못된 요청입니다.");
//				return map;
//			}
//		}
//		
//		int updated = boardService.update(boardVO);
//		
//		if(updated == 1) { // 성공
//			map.put("status", 204);
//		} else {
//			map.put("status", 404);
//			map.put("statusMessage", "게시글 수정에 실패하였습니다");
//		}
//		
//		return map;
//	}
//
//	public String insertForm(HttpServletRequest request) {
//		// 로그인 상태라면, 해당 로그인 정보를 JSP로 보내주기
//		HttpSession session = request.getSession();
//		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
//		if(loginMember != null) {
//			request.setAttribute("member", loginMember);
//		}
//		return "boardInsert";
//	}
//
//	public Map<String, Object> insert(HttpServletRequest request, BoardVO boardVO) {
//		Map<String, Object> map = new HashMap<>();
//		HttpSession session = request.getSession();
//		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember"); // 로그인 한 유저
//		
//		// 로그인 하지않았다면
//		if(loginMember == null) {
//			map.put("status", 404);
//			map.put("statusMessage", "로그인을 해야합니다.");
//			return map;
//		}
//		
//		// 현재 세션의 유저와 입력된 정보의 id가 같지 않다면 수정 불가
//		if(!loginMember.getId().equals("ADMIN")) { // 어드민이 아닐 때만 체크
//			if(!loginMember.getId().equals(boardVO.getAuthor())) { // 로그인 유저와 요청 유저가 다르다면
//				// 실패
//				map.put("status", 404);
//				map.put("statusMessage", "잘못된 요청입니다.");
//				return map;
//			}
//		}
//		
//		int updated = boardService.insert(boardVO);
//		
//		if(updated == 1) { // 성공
//			map.put("status", 204);
//		} else {
//			map.put("status", 404);
//			map.put("statusMessage", "게시글 생성에 실패하였습니다");
//		}
//		
//		return map;
//	}

}
