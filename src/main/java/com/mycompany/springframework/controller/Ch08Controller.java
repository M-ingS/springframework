package com.mycompany.springframework.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch08LoginForm;
import com.mycompany.springframework.dto.Ch08Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch08")
public class Ch08Controller {

	// ----------로그인 폼을 가져옴
	@GetMapping("/login")
	public String loginForm(Model model) {
		model.addAttribute("chNum", "ch08");
		return "ch08/loginForm";
	}
	
	//로그인하면 post로 데이터 전송
	@PostMapping("/login")
	public String login(Ch08LoginForm loginForm, HttpSession session) {
		
		Ch08Member member = new Ch08Member();
		member.setMid(loginForm.getMid());
		member.setMpassword(loginForm.getMpassword());
		member.setMname("홍길동");
		member.setMemail("hong@mycompany.com");
		
		session.setAttribute("login", member);
		
		return "redirect:/ch08/loginInfo";
	}
	// 로그인 정보
	@GetMapping("/loginInfo")
	public String loginInfo(Model model) {
		model.addAttribute("chNum", "ch08");
		
		return "ch08/loginInfo";
	}
	
	// ------------로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//session.removeAttribute("login");
		session.invalidate();		//세션 무효화 - 세션에 있는 모든 정보 삭제
		
		return "redirect:/ch08/login";
	}
	
		
		
		
		
}
