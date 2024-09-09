package com.mycompany.springframework.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.springframework.dto.Ch13Account;

@Mapper
public interface Ch13AccountDao {

	public List<Ch13Account> selectAll();

	public Ch13Account selectByAno(int fromAno);

	public void update(Ch13Account fromAccount);
	
	
}
