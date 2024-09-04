package com.mycompany.springframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.mybatis.Ch13BoardDao;
import com.mycompany.springframework.dto.Ch13Board;
import com.mycompany.springframework.dto.Ch13Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Ch13BoardService {
	@Autowired 	//ChBoardDao 인터페이스에 구현된 구현객체를 가져옴(마이바티스가 자동으로 생성)
	private Ch13BoardDao boardDao;
	
	//전체 게시물
	public List<Ch13Board> getBoardList(Ch13Pager pager) {
		List<Ch13Board> list = boardDao.selectList(pager);
		return list;
	}
	
	// 한 행을 가져옴(게시물 1개 정보)
	public Ch13Board getBoard(int bno) {
		return null;
	}
	
	// 게시물 쓰기
	public void writeBoard(Ch13Board board) {
		log.info("실행");
		log.info("insert 전 bno: " + board.getBno());
		boardDao.insert(board);	// insert문 날리기전 board.xml파일에서 bno값을 먼저 세팅시켜줌
		log.info("insert 후 bno: " + board.getBno());
		// bno를 이용해서 추가적인 비즈니스 로직을 작성해야할 경우
		int bno = board.getBno();
	}
	
	// 게시물 수정(update)
	public void updateBoard(Ch13Board board) {
		
	}
	
	// 게시물 삭제(delete)
	public void deleteBoard(int bno) {
		
	}

	public int getTotalRows() {
		int totalRows = boardDao.countRows();
		return totalRows;
	}
	
}
