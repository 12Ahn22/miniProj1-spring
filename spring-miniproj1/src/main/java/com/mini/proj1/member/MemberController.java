package com.mini.proj1.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mini.proj1.code.CodeService;
import com.mini.proj1.entity.HobbyVO;
import com.mini.proj1.entity.MemberHobbyVO;
import com.mini.proj1.entity.MemberVO;
import com.mini.proj1.paging.PageRequestVO;
import com.mini.proj1.paging.PageResponseVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	private final MemberService memberService;
	private final CodeService codeService;

	@RequestMapping("list")
	public String list(@Valid PageRequestVO pageRequestVO, BindingResult bindingResult, Model model) throws Exception {
		// 서비스를 호출한다.
		PageResponseVO<MemberVO> pageResponseVO = memberService.list(pageRequestVO);

		// JSP에서 사용할 수 있도록 setAttribute 해준다.
		model.addAttribute("pageResponseVO", pageResponseVO);
		model.addAttribute("sizes", codeService.getList());
		return "member/memberList";
	}

	@RequestMapping("view")
	public String view(Model model, MemberVO member) {
		MemberVO viewMember = memberService.view(member);
		model.addAttribute("member", viewMember);
		return "member/memberView";
	}

	@RequestMapping("delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestBody MemberVO member) {
		Map<String, Object> map = new HashMap<String, Object>();
//		HttpSession session = request.getSession();
//		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
//
//		// 로그인 하지않았다면
//		if (loginMember == null) {
//			map.put("status", 404);
//			map.put("statusMessage", "로그인을 해야합니다.");
//			return map;
//		}
//
//		// 현재 세션의 유저와 입력된 정보의 id가 같지 않다면 수정 불가
//		if (!loginMember.getId().equals("ADMIN")) { // 어드민이 아닐 때만 체크
//			if (!loginMember.getId().equals(member.getId())) { // 로그인 유저와 요청 유저가 다르다면
//				// 실패
//				map.put("status", 404);
//				map.put("statusMessage", "잘못된 요청입니다.");
//				return map;
//			}
//		}

		int updated = memberService.delete(member);
		if (updated == 1) { // 성공
			map.put("status", 204);
			// 관리자가 아니라면 세션 정보를 삭제
//			if(!loginMember.getId().equals("ADMIN")) {
//				session.removeAttribute("loginMember");
//				session.invalidate();
//			}
		} else {
			map.put("status", 404);
			map.put("statusMessage", "회원 정보 삭제 실패하였습니다");
		}

		return map;
	}

	@RequestMapping("update")
	@ResponseBody
	public Map<String, Object> update(@RequestBody MemberVO member) {
		Map<String, Object> map = new HashMap<String, Object>();
		// HttpSession session = request.getSession();
		// MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
		
		// 로그인 하지않았다면
//		if (loginMember == null) {
//			map.put("status", 404);
//			map.put("statusMessage", "로그인을 해야합니다.");
//			return map;
//		}
//
//		// 현재 세션의 유저와 입력된 정보의 id가 같지 않다면 수정 불가
//		if (!loginMember.getId().equals("ADMIN")) { // 어드민이 아닐 때만 체크
//			if (!loginMember.getId().equals(member.getId())) { // 로그인 유저와 요청 유저가 다르다면
//				// 실패
//				map.put("status", 404);
//				map.put("statusMessage", "잘못된 요청입니다.");
//				return map;
//			}
//		}
		
		int updated = memberService.update(member);
		if (updated == 1) { // 성공
			map.put("status", 204);
		} else {
			map.put("status", 404);
			map.put("statusMessage", "회원 정보 수정 실패하였습니다");
		}
		return map;
	}

	@RequestMapping("updateForm")
	public String fetchUpdateFormData(Model model, MemberVO member) {
		log.info("멤버 업데이트 폼");
		Map<String, Object> map = memberService.fetchUpdateFormData(member);
		model.addAttribute("member", map.get("memberVO"));
		model.addAttribute("hobbyList", map.get("hobbyList"));
		return "member/memberUpdate";
	}

	@RequestMapping("insertForm")
	public String fetchInsertFormData(Model model) {
		List<HobbyVO> hobbyList = memberService.fetchInsertFormData();
		 model.addAttribute("hobbyList", hobbyList);
		return "member/memberInsert";
	}

	@RequestMapping("insert")
	@ResponseBody
	public Map<String, Object> insert(@RequestBody MemberVO member) {
		Map<String, Object> map = new HashMap<String, Object>();
		int updated = memberService.insert(member);
		if (updated == 1) { // 성공
			map.put("status", 204);
		} else {
			map.put("status", 404);
			map.put("statusMessage", "회원 가입에 실패하였습니다");
		}
		return map;
	}
	
	@RequestMapping("loginForm")
	public Object loginForm() {
		log.info("로그인 화면");
		return "member/login";
	}

//	@RequestMapping("login")
//	@ResponseBody
//	public Map<String, Object> login(@RequestBody MemberVO member, HttpSession session) {
//		log.info("LOGIN");
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		// 로그인 처리
//		MemberVO loginVO = memberService.login(member);
//		
//		if(loginVO != null) {
//			// 로그인 성공
//			session.setAttribute("loginMember", loginVO);
//			session.setMaxInactiveInterval(30 * 60 * 1000); // 30분
//			map.put("status", 204);
//		}else {
//			// 로그인 실패
//			map.put("status", 404);
//			map.put("statusMessage", "아이디 혹은 비밀번호가 잘못되었습니다.");
//		}
		
		
		// 성공
//		if (memberService.authenticateMember(member)) {
////			HttpSession session = request.getSession();
//			
//			MemberVO viewMember = memberService.view(member);
//			if(viewMember == null) {
//				map.put("status", 500);
//				map.put("statusMessage", "로그인에 실패했습니다.");
//				return map;
//			}
//			
//			// 자동 로그인 체크를 한 경우, 쿠키를 저장해 전달.
//			if(member.getAutoLogin() != null && member.getAutoLogin().equals("Y")) { // 자동 로그인 체크를 한 요청인 경우
//				// UUID 생성
//				String uuid = UUID.randomUUID().toString();
//				member.setMemberUUID(uuid);
//				
//				// 해당 UUID를 유저 데이터에 저장
//				if(memberService.updateUUID(member) == 0) {
//					map.put("status", 404);
//					map.put("statusMessage", "자동 로그인 설정에 실패했습니다.");
//					return map;
//				}
//				
//				// 해당 UUID 값을 쿠키로 반환
//				Cookie uuidCookie = new Cookie("uuidCookie", uuid);
//				uuidCookie.setMaxAge(24 * 60 * 60); //24시간
//				uuidCookie.setPath("/");
//
//				response.addCookie(uuidCookie);
//			}
//			
//			session.setAttribute("loginMember", viewMember);
//			session.setMaxInactiveInterval(30 * 60 * 1000); // 30분
//			map.put("status", 204);
//		} else {
//			// 실패
//			map.put("status", 404);
//			map.put("statusMessage", "아이디 혹은 비밀번호가 잘못되었습니다.");
//		}
//		return map;
//	}
//
//	public String logout(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		// 세션에서 로그인 정보 얻기
//		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");
//		loginMember.setMemberUUID("");
//		memberService.updateUUID(loginMember);
//		// 세션 삭제
//		session.removeAttribute("loginMember");
//		session.invalidate();
//
//		// main 화면으로 리다이렉트 하도록 응답
//		return "redirect:/";
//	}

	@RequestMapping("profile")
	public String profile(Authentication authentication, Model model) {
		log.info("PROFILE");
		MemberVO memberVO = (MemberVO) authentication.getPrincipal();
		log.info("MEMBER_VO = {} ", memberVO);
		 MemberVO viewMember = memberService.view(memberVO);
		 model.addAttribute("member", viewMember);
		return "member/memberProfile";
	}

	@RequestMapping("duplicate")
	@ResponseBody
	public Map<String, Object> checkDuplicateId(@RequestBody MemberVO member) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		MemberVO searchMember = memberService.checkDuplicateId(member);
		if(searchMember == null) {
			map.put("status", 204);
		}else {
			map.put("status", 404);
			map.put("statusMessage", "해당 아이디는 이미 사용중입니다.");
		}
		return map;
	}
}
