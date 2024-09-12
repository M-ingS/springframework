package com.mycompany.springframework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch13Member;
import com.mycompany.springframework.security.Ch17UserDetails;
import com.mycompany.springframework.service.Ch13MemberService;
import com.mycompany.springframework.service.Ch13MemberService.JoinResult;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("ch17")	//ch17이 경로안에 있으면 이 컨트롤러가 실행됨
public class Ch17Controller {
	
	@Autowired
	private Ch13MemberService memberService;

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
	
	// 요청 URL에 따른 권한 설정 
	@Secured("ROLE_ADMIN")
	@GetMapping("/admin/page")
	public String adminPage() {
		log.info("실행");
		return "redirect:/ch17/authorityCheck";
	}
	@Secured("ROLE_MANAGER")
	@GetMapping("/manager/page")
	public String managerPage() {
		log.info("실행");
		return "redirect:/ch17/authorityCheck";
	}
	@Secured("ROLE_USER")
	@GetMapping("/user/page")
	public String userPage() {
		log.info("실행");
		return "redirect:/ch17/authorityCheck";
	}
	// 로그인을 안하고 권한별 내용을 보려고 할 때 보여주는 에러페이지
	@GetMapping("/error403")
	public String error403(Model model) {
		log.info("실행");
		model.addAttribute("chNum", "ch17");
		return "/ch17/error403";
	}
	
	//------------CSRF--------
	//회원가입 폼 get
	@GetMapping("/joinForm")
	public String joinForm(Model model) {
		model.addAttribute("chNum", "ch13");
		return "ch17/joinForm";
	}
	// 회원가입 폼 post 데이터 보내기
	@PostMapping("/join")
	public String join(Ch13Member member, Model model) {
		// 계정 활성화
		member.setMenabled(true);
		// 비밀번호 암호화
		PasswordEncoder passwordEncoder = 
				PasswordEncoderFactories.createDelegatingPasswordEncoder();	//패스워드 암호화
		member.setMpassword(passwordEncoder.encode(member.getMpassword()));
		
		log.info(member.toString());	//멤버에 데이터가 잘 들어가는지 확인
		// id 중복 검사
		JoinResult joinResult = memberService.join(member);
		
		if(joinResult == JoinResult.FAIL_DUPLICATED_MID) {
			String errorMessage = "아이디가 이미 존재합니다.";
			model.addAttribute("errorMessage", errorMessage);
			return "ch13/joinForm";
		} else {	// JoinResult.SUCCESS 일 경우
			return "redirect:/ch17/loginForm";	
		}
	}
}
