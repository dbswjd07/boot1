package net.daum.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddrVO {//데이터 저장빈 클래스
	
	private int addrNo; //번호, 빈클래스의 변수명이 JSON데이터의 키이름이 된다.
	private String sido; //시도
	private String gugun; //구군
	
	
	

}
