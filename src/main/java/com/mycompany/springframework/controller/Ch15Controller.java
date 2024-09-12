package com.mycompany.springframework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.springframework.dto.Ch13Account;
import com.mycompany.springframework.service.Ch15AccountService;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@Slf4j
@RequestMapping("ch15")	//ch15가 경로안에 있으면 이 컨트롤러가 실행됨
public class Ch15Controller {
	@Autowired
	private Ch15AccountService accountService;

	@GetMapping("/accountList")
	public String accountList(Model model) {
		model.addAttribute("chNum", "ch15");
		
		List<Ch13Account> list = accountService.getAccountList();
		model.addAttribute("list", list);
		
		return "ch15/accountList";
	}
	
	@PostMapping("/transfer")
	public String transfer(int fromAno, int toAno, int amount) {
		accountService.transfer(fromAno, toAno, amount);
		return "redirect:/ch15/accountList";
	}
	
	
}
