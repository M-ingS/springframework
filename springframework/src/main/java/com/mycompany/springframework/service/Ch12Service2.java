package com.mycompany.springframework.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch12Service2 {
	
	// 정적 메소드
	public static Ch12Service2 getInstance() {
		log.info("서비스 2 실행");
		return new Ch12Service2();	// 자기 자신의 객체가 생성되고 실행됨
	}
	
	// static이 안붙어있으면 Ch12Service2객체가 생성된 이후에  getCh12Service3()를 호출
	// 인스턴스 메소드
	public Ch12Service3 getCh12Service3() {
		return new Ch12Service3();
	}
}
