package com.mini.proj1.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mini.proj1.entity.HobbyVO;
import com.mini.proj1.entity.MemberHobbyVO;
import com.mini.proj1.entity.MemberVO;
import com.mini.proj1.hobby.mapper.HobbyMapper;
import com.mini.proj1.member.mapper.MemberMapper;
import com.mini.proj1.memberhobby.mapper.MemberHobbyMapper;
import com.mini.proj1.paging.PageRequestVO;
import com.mini.proj1.paging.PageResponseVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
	// 레거시라 @Autowired를 사용해주지 않으면 security-context.xml에서 에러 발생
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private HobbyMapper hobbyMapper;
	@Autowired
	private MemberHobbyMapper memberHobbyMapper;

	public PageResponseVO<MemberVO> list(PageRequestVO pageRequestVO) {
		List<MemberVO> list = null;
		PageResponseVO<MemberVO> pageResponseVO = null;
		int total = 0;
		try {
			list = memberMapper.getList(pageRequestVO);
			total = memberMapper.getTotalCount(pageRequestVO);
			
			log.info("list {}", list);
			log.info("total {}", total);
	        pageResponseVO = PageResponseVO.<MemberVO>withAll()
	                .list(list)
	                .total(total)
	                .size(pageRequestVO.getSize())
	                .pageNo(pageRequestVO.getPageNo())
	                .build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageResponseVO;
	}

	public MemberVO view(MemberVO member) {
		MemberVO memberVO = null;
		List<HobbyVO> hobbies  = null;
		try {
			memberVO = memberMapper.view(member);
			hobbies = memberMapper.getMemberHobbies(member);
			if (hobbies.size() != 0) memberVO.setHobbies(hobbies);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberVO;
	}

	public int delete(MemberVO member) {
		return memberMapper.delete(member);
	}

	public int update(MemberVO member) {
		try {
			// 멤버-취미 테이블에서 해당 멤버를 전부 삭제
			memberHobbyMapper.deleteAll(member);

			// 받은 취미를 전부 insert
			Map<Integer, String> hobbies = member.getMapHobbies();
			if (hobbies != null) {
					for(Integer key: hobbies.keySet()) {
						MemberHobbyVO memberHobbyVO = new MemberHobbyVO(member.getId(), key);
						memberHobbyMapper.insert(memberHobbyVO);
					}
			}
			// 멤버 수정 사항 업데이트
			memberMapper.update(member);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public Map<String, Object> fetchUpdateFormData(MemberVO member) {
		Map<String, Object> map = new HashMap<String, Object>();
		MemberVO memberVO =  memberMapper.view(member);
		List<HobbyVO> memberHobbies = memberMapper.getMemberHobbies(member); // 유저가 선택한 취미 목록
		Map<Integer, String> mapHobbies = new HashMap<Integer, String>();
		for(int i = 0; i < memberHobbies.size(); i++) {
			HobbyVO hobby = memberHobbies.get(i);
			mapHobbies.put(hobby.getId(), hobby.getHobby());
		}
		memberVO.setHobbies(memberHobbies);
		memberVO.setMapHobbies(mapHobbies);
		map.put("memberVO", memberVO);
		map.put("hobbyList", hobbyMapper.list());
		return map;
	}

	public List<HobbyVO> fetchInsertFormData() {
		return hobbyMapper.list();
	}

	public int insert(MemberVO member) {
		return memberMapper.insert(member);
	}

	public MemberVO checkDuplicateId(MemberVO member) {
		return memberMapper.checkDuplicateId(member);
	}
	
	
//	public MemberVO login(MemberVO member) {
//		MemberVO result = memberMapper.login(member);
//		if(result != null && member.isEqualsPwd(result.getPassword())) {
//			return result;
//		}
//		return null;
//	}

	// 기존 로그인 로직을 스프링 시큐리티로 처리하도록 변경
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("#### loadUserByUsername ####");
		log.info("LOAD USER BY USERNAME: {}", username);
		MemberVO resultVO = memberMapper.login(MemberVO.builder().id(username).build());
		if(resultVO == null) {
			log.info("사용자가 존재하지 않습니다.");
			throw new UsernameNotFoundException(username + " 사용자가 존재하지 않습니다");
		}
		return resultVO;
	}
}
