package com.mycompany.springframework.controller;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.springframework.dto.Ch03Dto;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch03")	// 이 컨트롤러가 선택되기 위한 공통 경로 ch03
public class Ch03Controller {
	
	// ----------------------1. GET방식 데이터 얻기
	@GetMapping("/receiveParamData")
	public String receiveParamData(String param1,
									String param2,	// 브라우저에서 값들을 받아와야함
									String param3,
									String param4,
									String param5,
									Model model) {
		
		log.info("param1: " + param1);
		log.info("param2: " + param2);
		log.info("param3: " + param3);
		log.info("param4: " + param4);
		log.info("param5: " + param5);
		
		
		// JSP로 데이터 전달 - model에 담아서 전달
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		model.addAttribute("param3", param3);
		model.addAttribute("param4", param4);
		model.addAttribute("param5", param5);
		
		model.addAttribute("chNum", "ch03");
		
		return "ch03/receiveParamData";
		
	}
	
	// ---------------------------2. Post Form으로 데이터 넘겨주기
	@GetMapping("/postMethodForm")
	public String postMethodForm(Model model) {
		model.addAttribute("chNum", "ch03");
		return "ch03/postMethodForm";
	}
	
	@PostMapping("/receivePostMethodForm")
	public String receivePostMethodForm(String param1,
									int param2,	// 브라우저에서 값들을 받아와야함
									double param3,
									boolean param4,
									@DateTimeFormat(pattern="yyyy-MM-dd") Date param5,
									Model model) {
		
		log.info("param1: " + param1);
		log.info("param2: " + param2);
		log.info("param3: " + param3);
		log.info("param4: " + param4);
		log.info("param5: " + param5);
		
		
		// JSP로 데이터 전달 - model에 담아서 전달
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		model.addAttribute("param3", param3);
		model.addAttribute("param4", param4);
		model.addAttribute("param5", param5);
		
		model.addAttribute("chNum", "ch03");
		return "ch03/receiveParamData";	
	}
	
	// ------------------------------3. DefaultValue
	@GetMapping("/defaultValue")
	public String defaultValue(
			String param1,
			@RequestParam(defaultValue="0")int param2,	// 값이 넘어오지 않을 경우에 기본값 지정
			@RequestParam(defaultValue="0.0")double param3,
			@RequestParam(defaultValue="false")boolean param4,
			String param5,
			Model model) {
		
		log.info("param1: " + param1);
		log.info("param2: " + param2);
		log.info("param3: " + param3);
		log.info("param4: " + param4);
		log.info("param5: " + param5);
		
		
		// JSP로 데이터 전달 - model에 담아서 전달
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		model.addAttribute("param3", param3);
		model.addAttribute("param4", param4);
		model.addAttribute("param5", param5);
		
		model.addAttribute("chNum", "ch03");
		return "ch03/receiveParamData";
	}

	
	// -----------------------4. 매개변수명과 다른 이름의 파라미터명
	@GetMapping("/otherArgName")
	public String otherArgName(	// param1로 받은건 arg1에 저장.
			@RequestParam("param1")String arg1,
			@RequestParam("param2")int arg2,	// 값이 넘어오지 않을 경우에 디폴트값 지정
			@RequestParam(value="param3", defaultValue="0.0")double arg3,
			@RequestParam(value="param4", defaultValue="false")boolean arg4,
			String arg5,
			Model model) {
		
		log.info("param1: " + arg1);
		log.info("param2: " + arg2);
		log.info("param3: " + arg3);
		log.info("param4: " + arg4);
		log.info("param5: " + arg5);
		
		
		// JSP로 데이터 전달 - model에 담아서 전달
		model.addAttribute("param1", arg1);
		model.addAttribute("param2", arg2);
		model.addAttribute("param3", arg3);
		model.addAttribute("param4", arg4);
		model.addAttribute("param5", arg5);
		
		model.addAttribute("chNum", "ch03");
		return "ch03/receiveParamData";
	}
	
	
	// -----------------------5. DTO로 파라미터 값을 모두 받기
		@GetMapping("/commandObject")
		public String commandObject(Ch03Dto dto, Model model) {
			
			log.info("param1: " + dto.getParam1());
			log.info("param2: " + dto.getParam2());
			log.info("param3: " + dto.getParam3());
			log.info("param4: " + dto.isParam4());	// boolean타입의 getter는 is로 시작함
			log.info("param5: " + dto.getParam5());
			
			
			// JSP로 데이터 전달 (CommandObject를 사용할 경우 자동으로 전달)
			// model.addAllAttributes("ch03Dto", dto);	//ch03Dto키로 dto객체가 자동으로 전달이 됨(그래서 생략가능)
			model.addAttribute("chNum", "ch03");
			return "ch03/receiveCommandObject";
		}
	
		
		//--------------6. JSON을 객체(Command Object)로 받기 - AJAX
		@GetMapping("/ajaxParam")
		public String ajaxParam() {
			return "ch03/ajaxParam";
		}
		
		@PostMapping("/requestAjax")
		public void requestAjax(Ch03Dto dto, HttpServletResponse response) throws Exception{
			log.info(dto.toString());	//dto에 @Data를 사용하여 toString메소드 사용가능
			
			// {"result": "ok"}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", "ok");
			String json = jsonObject.toString();
			
			response.setContentType("application/json; charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println(json);
			pw.flush();
			pw.close();
		}
		
		
		
		
		
		
		
		
		
		
}
