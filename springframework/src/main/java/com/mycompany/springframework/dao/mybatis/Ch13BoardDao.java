package com.mycompany.springframework.dao.mybatis;

import java.util.List; 

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.springframework.dto.Ch13Board;
import com.mycompany.springframework.dto.Ch13Pager;

@Mapper
public interface Ch13BoardDao {
	
	public int insert(Ch13Board board);	// board.xml insert문으로 DB에 반영
	
	public List<Ch13Board> selectList(Ch13Pager pager); // board.xml select문으로 List로 전체 게시글을 DB에 반영

	public int countRows();		

	public Ch13Board selectByBno(int bno);

	public Ch13Board selectAttachByBno(int bno);

	public int update(Ch13Board board);	// insert, update, delete는 반환타입을 int로 
	
	public int delete(int bno);
	
	public int updateHitcount(int bno);	//조회수 증가
}
