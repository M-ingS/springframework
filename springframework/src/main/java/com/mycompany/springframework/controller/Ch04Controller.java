package com.mycompany.springframework.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch04LoginForm;
import com.mycompany.springframework.dto.Ch04LoginFormValidator;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch04")
public class Ch04Controller {
	
	// 로그인 폼 화면 요청
	@GetMapping("/loginForm")
	public String loginForm(Model model) {
		model.addAttribute("chNum", "ch04");
		return "ch04/loginForm";
	}
	
	@InitBinder("ch04LoginForm")	// Dto 객체
	public void ch04LoginFormValidator(WebDataBinder binder) {
		binder.setValidator(new Ch04LoginFormValidator());
	}
	
	// 로그인 post로 데이터 보내주기
	@PostMapping("/login")							// errors에 유효성 검사 결과가 들어가있음
	public String login(@Valid Ch04LoginForm loginForm, Errors errors, Model model) {
		
		if(errors.hasErrors()) {	// 에러가 있으면 
			log.info("유효성 검사 실패");
			model.addAttribute("chNum", "ch04");
			return "ch04/loginForm";
		}
		log.info("유효성 검사 성공");
		
		log.info("mid: " + loginForm.getMid());
		log.info("mpassword: " + loginForm.getMpassword());
		return "redirect:/";
	}
	
	
	
		
}
