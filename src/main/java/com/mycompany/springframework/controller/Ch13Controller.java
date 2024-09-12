package com.mycompany.springframework.controller;


import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.springframework.dto.Ch13Board;
import com.mycompany.springframework.dto.Ch13Member;
import com.mycompany.springframework.dto.Ch13Pager;
import com.mycompany.springframework.dto.Ch13UpdateBoardForm;
import com.mycompany.springframework.dto.Ch13WriteBoardForm;
import com.mycompany.springframework.interceptor.LoginCheck;
import com.mycompany.springframework.service.Ch13BoardService;
import com.mycompany.springframework.service.Ch13MemberService;
import com.mycompany.springframework.service.Ch13MemberService.JoinResult;
import com.mycompany.springframework.service.Ch13MemberService.LoginResult;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch13")
public class Ch13Controller {
	@Autowired
	private Ch13BoardService boardService;
	
	@Resource
	private Ch13MemberService memberService;
	
	@LoginCheck
	@GetMapping("/writeBoardForm")
	public String writeBoardForm(Model model) {
		model.addAttribute("chNum", "ch13");
		return "ch13/writeBoardForm";
	}
	
/*	@PostMapping("/writeBoard")
	public String writeBoard(Ch13WriteBoardForm form) throws Exception{
		Ch13Board board = new Ch13Board();
		board.setBtitle(form.getBtitle());
		board.setBcontent(form.getBcontent());
		board.setMid("user");
		
		MultipartFile battach = form.getBattach();
		
		if(!battach.isEmpty()) {
			board.setBattachoname(battach.getOriginalFilename());
			board.setBattachtype(battach.getContentType());
			String fileName = new Date().getTime() + "-" + battach.getOriginalFilename();
			board.setBattachsname(fileName);
			String savaDir = "C:\\2024-oti\\workspace-spring\\uploadfiles";
			File file = new File(savaDir, fileName);
			battach.transferTo(file);
		}
		boardService.writeBoard(board);
		return "redirect:/";
		
	}*/
	
	@PostMapping("/writeBoard")
	public String writeBoard(Ch13WriteBoardForm form, HttpSession session) throws Exception{
		Ch13Board board = new Ch13Board();
		board.setBtitle(form.getBtitle());
		board.setBcontent(form.getBcontent());
		
		//board.setMid("user");
		Ch13Member member = (Ch13Member) session.getAttribute("login");
		board.setMid(member.getMid());
		
		MultipartFile battach = form.getBattach();
		
		if(!battach.isEmpty()) {
			board.setBattachoname(battach.getOriginalFilename());
			board.setBattachtype(battach.getContentType());
			board.setBattachdata(battach.getBytes()); // 파일 자체의 데이터 얻기 - getByte()
		}
		boardService.writeBoard(board);
		return "redirect:/ch13/boardList";
		
	}
	// 전체 게시글 리스트 불러오기
	@LoginCheck
	@GetMapping("/boardList")
	public String boardList(Model model, 
			@RequestParam(defaultValue="1") int pageNo,
			HttpSession session) {
		model.addAttribute("chNum", "ch13");
		
		//new Ch13Pager(10, 5, totalRows, pageNo); 
		// 한 페이지에 보여줄 행 수, 그룹당 페이지 수, 페이징 대상이 되는 전체 행수, 현재 페이지 번호
		int totalRows = boardService.getTotalRows();
		Ch13Pager pager = new Ch13Pager(10, 5, totalRows, pageNo);	 
		session.setAttribute("pager", pager);
		//model.addAttribute("pager", pager);	//jsp에 pager객체를 보내줌
		
		List<Ch13Board> list = boardService.getBoardList(pager);
		model.addAttribute("list", list);
		return "ch13/boardList";
	}
	
	// 조회수 증가
	@GetMapping("/detailBoardAddHitcount")
	public String detailBoardAddHitcount(int bno, Model model) {
		boardService.addHitcount(bno);
		return detailBoard(bno, model);
	}
		
	// 상세 게시글
	@GetMapping("/detailBoard")
	public String detailBoard(int bno, Model model) {
		model.addAttribute("chNum", "ch13");
		
		Ch13Board board = boardService.getBoard(bno);
		model.addAttribute("board", board); 
		return "ch13/detailBoard";
	}
	
	// 사진 첨부파일 보이게 + 다운로드
	@GetMapping("/attachDownload")
	public void attachDownload(int bno, HttpServletResponse response) throws Exception{
		Ch13Board board = boardService.getBoardAttach(bno);	//첨부를 포함한 모든 게시글 내용
		
		//응답 헤더에 들어가는 Content-Type 내용 설정
		String contentType = board.getBattachtype();
		response.setContentType(contentType);
		
		//파일로 저장하기 위한 설정
		String fileName = board.getBattachoname();
		String encodingFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + encodingFileName+ "\"");
		log.info(board.getBattachdata()+ "");
		//응답 본문에 파일 데이터를 출력
		OutputStream out = response.getOutputStream();
		out.write(board.getBattachdata());
		out.flush();
		out.close();
	}
	
	// 게시물 수정 get
	@GetMapping("/updateBoardForm")
	public String updateBoardForm(int bno, Model model) {
		model.addAttribute("chNum", "ch13");
		
		Ch13Board board = boardService.getBoard(bno);
		model.addAttribute("board", board);
		return "ch13/updateBoardForm";
	}
	
	// 게시물 수정 post
	@PostMapping("/updateBoard")
	public String updateBoard(Ch13UpdateBoardForm form) throws Exception{
		Ch13Board board = new Ch13Board();
		board.setBno(form.getBno());
		board.setBtitle(form.getBtitle());
		board.setBcontent(form.getBcontent());

		MultipartFile mf = form.getBattach();
		if(!mf.isEmpty()) {
			board.setBattachoname(mf.getOriginalFilename());
			board.setBattachtype(mf.getContentType());
			board.setBattachdata(mf.getBytes());
		}
		boardService.updateBoard(board);
		return "redirect:/ch13/detailBoard?bno=" + form.getBno();
	}
	
	// 게시물 삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(int bno, HttpSession session) {
		boardService.deleteBoard(bno);
		
		Ch13Pager pager = (Ch13Pager) session.getAttribute("pager");
		int pageNo = pager.getPageNo();
		return "redirect:/ch13/boardList?pageNo=" + pageNo;
	}
	
	//------------ 9/6 ----------
	//회원가입 폼 get
	@GetMapping("/joinForm")
	public String joinForm(Model model) {
		model.addAttribute("chNum", "ch13");
		return "ch13/joinForm";
	}
	// 회원가입 폼 post 데이터 보내기
	@PostMapping("/join")
	public String join(Ch13Member member, Model model) {
		member.setMenabled(true);
		log.info(member.toString());	//멤버에 데이터가 잘 들어가는지 확인
		
		// id 중복 검사
		JoinResult joinResult = memberService.join(member);
		
		if(joinResult == JoinResult.FAIL_DUPLICATED_MID) {
			String errorMessage = "아이디가 이미 존재합니다.";
			model.addAttribute("errorMessage", errorMessage);
			return "ch13/joinForm";
		} else {	// JoinResult.SUCCESS 일 경우
			return "redirect:/ch13/loginForm";	
		}
	}
	
	// 로그인 폼 get
	@GetMapping("/loginForm")
	public String loginForm(Model model) {
		model.addAttribute("chNum", "ch13");
		return "ch13/loginForm";
	}
	// 로그인 post 
	@PostMapping("/login")
	public String login(Ch13Member member, Model model, HttpSession session) {
		LoginResult loginResult = memberService.login(member);
		// 비즈니스 로직은 service에서 처리하고 controller에서는 이에 따른 응답만 처리해주면 됨
		if(loginResult == LoginResult.FAIL_MID) {
			model.addAttribute("chNum", "ch13");
			model.addAttribute("errorMid", "아이디가 존재하지 않습니다. ");
			return "ch13/loginForm";
		} else if(loginResult == LoginResult.FAIL_MPASSWORD) {
			model.addAttribute("chNum", "ch13");
			model.addAttribute("errorMpassword", "비밀번호가 맞지 않습니다. ");
			return "ch13/loginForm";
		} else if(loginResult == LoginResult.FAIL_ENABLED) { 	// 휴먼 계정은 우선은 처리x
			return "redirect:/";
		} else { //LoginResult.SUCCESS 일 경우
			session.setAttribute("login", member);	
			return "redirect:/";			
		}
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("login");
		return "redirect:/ch13/loginForm";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
