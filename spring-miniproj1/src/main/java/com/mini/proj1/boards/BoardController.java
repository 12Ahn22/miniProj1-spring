package com.mini.proj1.boards;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mini.proj1.code.CodeService;
import com.mini.proj1.entity.BoardVO;
import com.mini.proj1.paging.PageRequestVO;
import com.mini.proj1.paging.PageResponseVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	// 스프링이 의존성을 주입해준다.
	private final BoardService boardService;
	private final CodeService codeService;

	@RequestMapping("list")
	public String list(@Valid PageRequestVO pageRequestVO, BindingResult bindingResult, Model model) throws Exception {
		log.info("게시판 목록");
		
        if(bindingResult.hasErrors()){
        	pageRequestVO = PageRequestVO.builder().build();
        }
        
		PageResponseVO<BoardVO> pageResponseVO = boardService.list(pageRequestVO);
		// HttpSession session = request.getSession();
		// boolean isLogin = (session.getAttribute("loginMember") != null)? true : false;
		
		model.addAttribute("pageResponseVO", pageResponseVO);
		model.addAttribute("sizes", codeService.getList());
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
	
	@RequestMapping("jsonView")
	@ResponseBody
	public Map<String, Object> jsonView(BoardVO boardVO) {
		log.info("게시판 상세 목록 - JSON");
		Map<String, Object> map = new HashMap<>();
		// 게시글 정보
		BoardVO board = boardService.view(boardVO);
		
		if(board != null) { // 성공
			map.put("status", 204);
			map.put("board",board);
		} else {
			map.put("status", 404);
			map.put("statusMessage", "게시글 삭제에 실패하였습니다");
		}
		return map;
	}

	@RequestMapping("delete")
	@ResponseBody
	public Map<String,Object> delete(Model model, @RequestBody BoardVO boardVO) {
		log.info("게시물 삭제");
		Map<String, Object> map = new HashMap<String, Object>();
		// HttpSession session = request.getSession();
		// MemberVO loginMember = (MemberVO) session.getAttribute("loginMember"); // 로그인 한 유저
		
		// 로그인 하지않았다면
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
		int updated = boardService.delete(boardVO);
		
		if(updated == 1) { // 성공
			map.put("status", 204);
		} else {
			map.put("status", 404);
			map.put("statusMessage", "게시글 삭제에 실패하였습니다");
		}
		
		return map;
	}

	@RequestMapping("updateForm")
	public String updateForm(Model model, BoardVO boardVO) {
		// 게시글 정보
		BoardVO board = boardService.fetchUpdateFormData(boardVO);
		model.addAttribute("board", board);
		return "board/boardUpdate";
	}

	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> update(Model model, @RequestBody BoardVO boardVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		// HttpSession session = request.getSession();
		// MemberVO loginMember = (MemberVO) session.getAttribute("loginMember"); // 로그인 한 유저
		
		// 로그인 하지않았다면
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
		
		int updated = boardService.update(boardVO);
		
		if(updated == 1) { // 성공
			map.put("status", 204);
		} else {
			map.put("status", 404);
			map.put("statusMessage", "게시글 수정에 실패하였습니다");
		}
		
		return map;
	}

	@RequestMapping("insertForm")
	public String insertForm() {
		// 로그인 상태라면, 해당 로그인 정보를 JSP로 보내주기
		// HttpSession session = request.getSession();
		// MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		// if(loginMember != null) {
		// 	request.setAttribute("member", loginMember);
		// }
		return "board/boardInsert";
	}

	@RequestMapping("insert")
	@ResponseBody
	public Map<String, Object> insert(Model model, @RequestBody BoardVO boardVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		// HttpSession session = request.getSession();
		// MemberVO loginMember = (MemberVO) session.getAttribute("loginMember"); // 로그인 한 유저
		
		// 로그인 하지않았다면
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
		
		int updated = boardService.insert(boardVO);
		
		if(updated == 1) { // 성공
			map.put("status", 204);
		} else {
			map.put("status", 404);
			map.put("statusMessage", "게시글 생성에 실패하였습니다");
		}
		
		return map;
	}

}
