package com.mini.proj1.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mini.proj1.member.MemberVO;
import com.mini.proj1.member.MemberVO.Gender;
import com.mini.proj1.member.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTest {
    @Autowired
    private MemberMapper memberMapper;
    
    @Test
    public void testCreateSampleData() {
    	for (int i=0;i<200;i++) {
	    	MemberVO memberVO = MemberVO.builder()
	    			.id("id" + i)
	    			.password("1004")
	    			.name("name" + i)
	    			.phone("010-1234-5555")
	    			.address("서울시 새구 새동")
	    			.build();
	    	
	    	if (i % 2 == 0) {
	    		memberVO.setGender(Gender.M);
	    	} else {
	    		memberVO.setGender(Gender.F);
	    	}
	    	
	    	memberMapper.insert(memberVO);
    	}
    }
}
