package com.mini.proj1.member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mini.proj1.hobby.HobbyVO;
import com.mini.proj1.hobby.mapper.HobbyMapper;
import com.mini.proj1.member.mapper.MemberMapper;
import com.mini.proj1.memberhobby.MemberHobbyVO;
import com.mini.proj1.memberhobby.mapper.MemberHobbyMapper;
import com.mini.proj1.paging.PageRequestVO;
import com.mini.proj1.paging.PageResponseVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
	private final MemberMapper memberMapper;
	private final HobbyMapper hobbyMapper;
	private final MemberHobbyMapper memberHobbyMapper;

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
//	MemberDAO memberDAO = new MemberDAO(); // 유저 정보
//	HobbyDAO hobbyDAO = new HobbyDAO(); // 취미
//	MemberHobbyDAO memberHobbyDAO = new MemberHobbyDAO(); // 멤버-취미 관계
//
//	public List<MemberVO> list() throws SQLException {
//		List<MemberVO> list = null;
//		list = memberDAO.list();
//		return list;
//	}

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
//		try {
//			memberDAO.delete(member);
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
				
//				for(int i = 0; i < hobbies.size(); i++) {
//					Set<Entry<Integer, String>> hv = hobbies.
////					MemberHobbyVO memberHobbyVO = new MemberHobbyVO(member.getId(), hv);
//					memberHobbyMapper.insert(memberHobbyVO);
//				}
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
//		List<HobbyVO> list = null;
//		try {
//			list = hobbyDAO.list();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
	}

	public int insert(MemberVO member) {
		return memberMapper.insert(member);
//		try {
//			// 멤버를 먼저 생성
//			memberDAO.insert(member);
//			// 취미-멤버 테이블에 데이터 생성
//			Map<Integer, String> hobbies = member.getHobbies();
//			if (hobbies != null) {
//				for (var hobby : hobbies.entrySet()) {
//					memberHobbyDAO.insert(member.getId(), hobby.getKey());
//				}
//			}
//			BaseDAO.conn.commit();
//		} catch (Exception e) {
//			try {
//				// SQL 에러가 나면, 여기서 처리
//				e.printStackTrace();
//				BaseDAO.conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			return 0;
//		}
//		return 1;
	}

//	public MemberVO authenticateMember(MemberVO member) {
//		return memberMapper.authenticateMember(member);

//		boolean hasAuth = false;
//		try {
//			hasAuth =  memberDAO.authenticateMember(member);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return hasAuth;
//	}
//
//	public int updateUUID(MemberVO member) {
//		try {
//			memberDAO.updateUUID(member);
//			BaseDAO.conn.commit();
//		} catch (SQLException e) {
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
	public MemberVO checkDuplicateId(MemberVO member) {
		return memberMapper.checkDuplicateId(member);
	}
}
