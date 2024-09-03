package com.mycompany.springframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.mybatis.Ch13BoardDao;
import com.mycompany.springframework.dto.Ch13Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch13BoardService {
	@Autowired 	//ChBoardDao 인터페이스에 구현된 구현객체를 가져옴(마이바티스가 자동으로 생성)
	private Ch13BoardDao boardDao;
	
	// 전체 게시물 리스트
	public List<Ch13Board> getBoardList(){
		return null;
	}
	
	// 한 행을 가져옴(게시물 1개 정보)
	public Ch13Board getBoard(int bno) {
		return null;
	}
	
	// 게시물 쓰기(생성 create)
	public void writeBoard(Ch13Board board) {
		log.info("실행");
		boardDao.insert(board);	//서비스가 Dao를 주입받고 board(dto)를 데이터베이스에 insert시켜줌(게시물 생성)
	}
	
	// 게시물 수정(update)
	public void updateBoard(Ch13Board board) {
		
	}
	
	// 게시물 삭제(delete)
	public void deleteBoard(int bno) {
		
	}
	
}
