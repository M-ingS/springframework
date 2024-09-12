package com.mycompany.springframework.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.springframework.dto.Ch02LoginResult;
import com.mycompany.springframework.interceptor.LoginCheck;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ch02")	/*경로에 ch02가 들어가면 이 컨트롤러에서 처리*/
@Slf4j
public class Ch02Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch02Controller.class);
	
	@RequestMapping("/getMethod")			// get방식 페이지 불러옴
	public String getMethod(Model model) {
		model.addAttribute("chNum", "ch02");
		log.info("실행");
		return "ch02/getMethod";
	}
	
//	@RequestMapping("/postMethod")		/*값 하나만 줄 때*/
//	public String postMethod() {
//		logger.info("실행");
//		return "ch02/postMethod";
//	}
	
	/*get만 허용하고 post는 허용x일 때*/
	/*@RequestMapping(value="/getAtag", method=RequestMethod.GET)*/		/*2개 이상 값을 줄 때*/
	@GetMapping("/getAtag")
	public String getAtag(String bno, String bkind, Model model) {										/*디폴트값은 value=""*/
		log.info("실행");
		log.info("bno: " + bno);
		log.info("bkind: " + bkind);
		model.addAttribute("chNum", "ch02");
		return "ch02/getMethod";
	}
	
	@GetMapping("/getFormTag")				// getMethod에서 제출 버튼 클릭 시 Form태그 조회? 후 홈 화면 리턴
	public String getFormTag(String bno, String bkind, Model model) {
		log.info("실행");
		log.info("bno: " + bno);
		log.info("bkind: " + bkind);
		model.addAttribute("chNum", "ch02");
		//return "ch02/getMethod";
		return "redirect:/"; //다시 원래 페이지로 돌아감
	}
	
	//-------------
	
	@GetMapping("/getLocationHref")				
	public String getLocationHref(String bno, String bkind, Model model) {
		log.info("실행");
		log.info("bno: " + bno);
		log.info("bkind: " + bkind);
		model.addAttribute("chNum", "ch02");
		return "ch02/getMethod";
	}
		
	@GetMapping("/getAjax1")					// get맵핑 - ajaxHTML 
	public String getAjax1(String bno, String bkind, Model model) {
		log.info("실행");
		log.info("bno: " + bno);
		log.info("bkind: " + bkind);
		model.addAttribute("chNum", "ch02");
		return "ch02/AjaxFragmentHtml";
	}
	
	@GetMapping("/getAjax2")					// get맵핑 - JSON 
	public String getAjax2(String bno, String bkind, Model model) {
		log.info("실행");
		log.info("bno: " + bno);
		log.info("bkind: " + bkind);
		model.addAttribute("chNum", "ch02");
		return "ch02/AjaxJSON";
	}
	
	//--------------POST-----------------------
	//						postMapping - Form 태그 방식
	@GetMapping("/postMethod")
	public String postMethod(Model model) {
		logger.info("post 방식 실행");
		model.addAttribute("chNum", "ch02");
		return "ch02/postMethod";
	}
	
	@PostMapping("/postFormTag")		// postMethod에서 제출 버튼 클릭 시 Form태그 보낸 후 홈 화면 리턴
	public String postFormTag(String bno, String bkind, Model model) {
		log.info("실행");
		log.info("bno: " + bno);
		log.info("bkind: " + bkind);
		model.addAttribute("chNum", "ch02");
		//return "ch02/postMethod";	//jsp로 post메소드 응답
		return "redirect:/"; //다시 원래 페이지로 돌아감
	}
	
	// --------------------
	//						postMapping - ajax 방식
	
	@PostMapping("/postAjax1")					// post맵핑 - ajaxHTML 
	public String postAjax1(String bno, String bkind, Model model) {
		log.info("실행");
		log.info("bno: " + bno);
		log.info("bkind: " + bkind);
		model.addAttribute("chNum", "ch02");
		return "ch02/AjaxFragmentHtml";
	}
	
	@PostMapping("/postAjax2")					// post맵핑 - ajaxJSON
	public String postAjax2(String bno, String bkind, Model model) {
		log.info("실행");
		log.info("bno: " + bno);
		log.info("bkind: " + bkind);
		model.addAttribute("chNum", "ch02");
		return "ch02/AjaxJSON";		// 뷰 이름 - 앞에 WEB-INF/view, 뒤 - .jsp가 붙음
	}
	
	//-----------------------------
	
	@GetMapping("/returnModelAndView")
	public ModelAndView returnModelAndView() {
		log.info("실행");
		ModelAndView mav = new ModelAndView();
		mav.addObject("bno", 15);		// jsp파일이 실행될 때 여기의 데이터가 model에 담겨 사용됨
		mav.addObject("bkind", "notice");	// (model 이름, 값) 형식
		mav.addObject("mid", "user1");
		mav.addObject("memail", "mycompany@naver.com");
		mav.addObject("chNum", "ch02");
		mav.setViewName("ch02/returnModelAndView");	// 뷰(jsp파일)이 있어야함
		
		return mav;
	}
	
	@GetMapping("/returnVoid")		// 응답을 생성 HttpServletResponse, void면 응답을 생성해야함
	public void returnVoid(HttpServletResponse response) throws IOException{
		log.info("실행");
		// { } : 객체 JSONObject jsonObject = new JSONObject();
		// [ ] : 배열 JSONArray jsonArray = new JSONArray();
		
		// { "result": "success", "mid": "user1"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("mid", "user1");
		String json = jsonObject.toString();

		//응답 생성(헤더(contentType) + 본문 (데이터))
		response.setContentType("application/json; charset=utf-8");		//헤더(contentType)
		PrintWriter pw =  response.getWriter();		//본문(데이터)
		pw.println(json);
		pw.flush();
		pw.close();
		
	}
	//리턴된 value객체를 json으로 만들어줌
	@GetMapping(value="/returnObject", produces="application/json; charset=utf-8")
	@ResponseBody		// 리턴되는 Ch02LoginResult객체가 응답 본문에 들어감(json으로 바뀌면서)
	public Ch02LoginResult returnObject() {
		log.info("실행");
		
		Ch02LoginResult obj = new Ch02LoginResult();
		obj.setResult("success");
		obj.setMid("user1");

		return obj;
	}
	//---------------------------------------
	// Filter
	
	@LoginCheck	// login 어노테이션이 붙어있는 경로의 요청 메소드는 반드시 로그인을 해야함
	@GetMapping("/mypage")			// 마이페이지
	public String mypage(Model model) {
		log.info("실행");
		model.addAttribute("chNum", "ch02");
		return "ch02/mypage";
	}
	
	@GetMapping("/loginForm")		// 로그인 폼 요청
	public String loginForm(Model model) {
		log.info("실행");
		model.addAttribute("chNum", "ch02");
		return "ch02/loginForm";
	}
	
	@PostMapping("/login")			// Post맵핑으로 로그인 정보 보내줌
	public String login(String mid, String mpassword, HttpSession session) {
		log.info("실행");
		log.info("mid: " + mid);
		log.info("mpassword: " + mpassword);
		
		session.setAttribute("login", mid);	//세션에 로그인 정보 저장
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.info("실행");
		
		session.removeAttribute("login");//세션에 로그인 정보 삭제
		return "redirect:/ch02/loginForm";	// 로그아웃을 요청하고 로그인 폼으로 가게함
	}
	
	
}
