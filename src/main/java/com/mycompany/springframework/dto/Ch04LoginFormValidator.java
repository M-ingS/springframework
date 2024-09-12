package com.mycompany.springframework.dto;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch04LoginFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		log.info("실행");								// clazz으로 들어오는 클래스가 Ch04LoginForm클래스 타입인지
		boolean result = Ch04LoginForm.class.isAssignableFrom(clazz);
		return result;
	}
	
	/*result가 true면 validate메소드 실행, 아니면 실행x*/
	@Override			
	public void validate(Object target, Errors errors) {
		log.info("실행");
		Ch04LoginForm loginForm = (Ch04LoginForm) target;
		
		
		//Mid 검사
		String mid = loginForm.getMid();
		if(mid == null || mid.equals("")) {
			errors.rejectValue("mid", "errors.mid.required");	//mid가 넘어오지 않을 경우 해당 에러 메시지 표시
		} else if (mid.length() < 6 || mid.length() > 12) {
			errors.rejectValue("mid", "errors.mid.length", new Object[] {6, 12}, null);	// 에러메시지가 없으면 디폴트로 지정
		}
		
		
		//mpassword 검사
	      String mpassword = loginForm.getMpassword();
	      String pattern = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}";
	      if(mpassword == null || mpassword.equals("")) {
	         errors.rejectValue("mpassword", "errors.mpassword.required");
	      } else if(mpassword.length() < 8 || mpassword.length() > 15) {
	         errors.rejectValue("mpassword", "errors.mpassword.length", new Object[] {"8", "15"}, null);
	      } else if(!Pattern.matches(pattern, mpassword)) {
	         errors.rejectValue("mpassword", "errors.mpassword.wrongchar");
	      }
	}
	
}
