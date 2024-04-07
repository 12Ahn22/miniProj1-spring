package com.mini.proj1.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mini.proj1.boards.BoardVO;
import com.mini.proj1.boards.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTest {
    @Autowired
    private BoardMapper boardMapper;
    
    @Test
    public void testCreateSampleData() {
    	for (int i=0;i<200;i++) {
	    	BoardVO boardVO = BoardVO.builder()
	    			.title("게시물 제목 " + i)
	    			.content("게시물 내용 " + i)
	    			.build();
	    	
	    	if (i % 2 == 0) {
	    		boardVO.setAction("male2");
	    	} else {
	    		boardVO.setAction("female2");
	    	}
	    	
	    	boardMapper.insert(boardVO);
    	}
    }
}
