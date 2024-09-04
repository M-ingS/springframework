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
	
	
}
