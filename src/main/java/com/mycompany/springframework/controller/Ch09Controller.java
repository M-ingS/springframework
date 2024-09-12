package com.mycompany.springframework.controller;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.springframework.dto.Ch09FileUploadForm;
import com.mycompany.springframework.dto.Ch09MultiFileUploadForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch09")
public class Ch09Controller {
	
	//----------------파일 업로드 폼 불러오기 
	@GetMapping("/fileUploadForm")
	public String fileUploadForm(Model model) {
		model.addAttribute("chNum", "ch09");
		return "ch09/fileUploadForm";
	}
	// 싱글 파일 업로드
	@PostMapping("/singlefileUpload")
	public String singleFileUpload(Ch09FileUploadForm form) throws Exception{
		log.info("title: " + form.getTitle());
		log.info("desc: " + form.getDesc());
		MultipartFile attach = form.getAttach();
		//파일이 포함되어 있는지 여부 조사
		if(!attach.isEmpty()) {
			// 파일 정보 읽기
			log.info("orignalFilename: " + attach.getOriginalFilename());
			log.info("contentType: " + attach.getContentType());
			log.info("size: " + attach.getSize());
			//파일을 파일 스토리지에 저장
			String saveDir = "C:\\2024-oti\\workspace-spring\\uploadfiles";
			String saveFileName = new Date().getTime() + "-" + attach.getOriginalFilename();
			File file = new File(saveDir, saveFileName);
			attach.transferTo(file);
		}
		return "redirect:/ch09/downloadFileList";
	}
	// 멀티 파일 업로드
	@PostMapping("/multiFileUpload")
	public String multiFileUpload(Ch09MultiFileUploadForm form) throws Exception{
		log.info("title: " + form.getTitle());
		log.info("desc: " + form.getDesc());
		for(MultipartFile mf : form.getAttach()) {
			//파일이 포함되어 있는지 여부 조사
			if(!mf.isEmpty()) {
				// 파일 정보 읽기
				log.info("orignalFilename: " + mf.getOriginalFilename());
				log.info("contentType: " + mf.getContentType());
				log.info("size: " + mf.getSize());
				log.info("");
				//파일을 파일 스토리지에 저장
				String saveDir = "C:\\2024-oti\\workspace-spring\\uploadfiles";
				String saveFileName = new Date().getTime() + "-" + mf.getOriginalFilename();
				File file = new File(saveDir, saveFileName);
				mf.transferTo(file);
			}
		}
		return "redirect:/ch09/downloadFileList";
	}
	// 업로드한  파일 리스트 불러오기
	@GetMapping("/downloadFileList")
	public String downloadFileList(Model model) {
		model.addAttribute("chNum", "ch09");
		
		String saveDir = "C:/2024-oti/workspace-spring/uploadfiles";
		File file = new File(saveDir);
		String[] fileNames = file.list();
		model.addAttribute("fileNames", fileNames);
		
		return "ch09/downloadFileList";
	}
	
	//파일 다운로드
	@GetMapping("/downloadFile")
	public void downloadFile(String fileName, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//응답 헤더에 들어가는 Content-Type 내용 설정
		String contentType = request.getServletContext().getMimeType(fileName);
		response.setContentType(contentType);
		
		//파일로 저장하기 위한 설정
		String encodingFileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + encodingFileName+ "\"");
		
		//응답 본문에 파일 데이터를 출력
		String saveDir = "C:/2024-oti/workspace-spring/uploadfiles";
		Path path = Paths.get(saveDir + "/" + fileName);
		OutputStream out = response.getOutputStream();
		Files.copy(path, out);
		out.flush();
		out.close();
	}
	// ajax로 파일 
	@PostMapping("/uploadFileFromAjax")
	public void uploadFileFromAjax(
			Ch09FileUploadForm form,
			HttpServletResponse response) throws Exception{
		log.info("title: " + form.getTitle());
		log.info("desc: " + form.getDesc());
		MultipartFile attach = form.getAttach();
		//파일이 포함되어 있는지 여부 조사
		if(!attach.isEmpty()) {
			// 파일 정보 읽기
			log.info("orignalFilename: " + attach.getOriginalFilename());
			log.info("contentType: " + attach.getContentType());
			log.info("size: " + attach.getSize());
			//파일을 파일 스토리지에 저장
			String saveDir = "C:\\2024-oti\\workspace-spring\\uploadfiles";
			String saveFileName = new Date().getTime() + "-" + attach.getOriginalFilename();
			File file = new File(saveDir, saveFileName);
			attach.transferTo(file);
		}
		//응답 생성
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
