package com.mycompany.springframework.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch13Member;
import com.mycompany.springframework.security.Ch17UserDetails;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("ch17")	//ch17이 경로안에 있으면 이 컨트롤러가 실행됨
public class Ch17Controller {

	// 스프링 시큐리티 로그인 폼
	@RequestMapping("/loginForm")
	public String loginForm(Model model) {
		model.addAttribute("chNum", "ch17");
		return "ch17/loginForm";
	}
	
	// 권한별 내용보기
	@GetMapping("/authorityCheck")
	public String authorityCheck(Model model) {
		model.addAttribute("chNum", "ch17");
		return "ch17/authorityCheck";
	}
	
	// 로그인한 사용자 정보보기
	@GetMapping("/userInfo")
	public String userInfo(Model model, Authentication authentication) {
		model.addAttribute("chNum", "ch17");
		
		// 사용자의 아이디만 얻고 싶을 경우
		// 방법1
		String mid = authentication.getName();
		// 방법2
		//Ch17UserDetails userDetails = (Ch17UserDetails) authentication.getPrincipal();
		//String mid = userDetails.getUsername();
		model.addAttribute("mid", mid);
		
		// 사용자의 모든 정보를 얻고 싶은 경우
		Ch17UserDetails userDetails = (Ch17UserDetails) authentication.getPrincipal();
		Ch13Member member = userDetails.getMember();
		model.addAttribute("member", member);
		return "ch17/userInfo";
	}
}
