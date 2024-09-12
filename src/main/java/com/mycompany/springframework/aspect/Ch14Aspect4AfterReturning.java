package com.mycompany.springframework.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Ch14Aspect4AfterReturning {
	
	@AfterReturning(
			pointcut="execution(public * com.mycompany.springframework.controller.Ch14Controller.afterReturning*(..))",
			returning="returnValue"	// 메소드가 실행되고 리턴값을 받을 때 returnValue변수로 받음
		)
	public void method(String returnValue) {
		//공통 코드 (메소드 호출 후에 실행하는 코드)
		log.info("실행");
		log.info("리턴값: " + returnValue);
	}

}
