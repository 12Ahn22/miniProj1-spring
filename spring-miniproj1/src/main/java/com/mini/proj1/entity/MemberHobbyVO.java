package com.mini.proj1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberHobbyVO {
	private String memberId;
	private Integer hobbyId; 
}
