package com.mycompany.springframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.Ch12Dao1;
import com.mycompany.springframework.dao.Ch12Dao2;
import com.mycompany.springframework.dao.Ch12Dao3;
import com.mycompany.springframework.dao.Ch12DaoInterface;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class Ch12Service8 {
	
	@Autowired 	@Qualifier("ch12Dao4")
	// @Resource(name="ch12Dao5")
	private Ch12DaoInterface dao;	// dao4, dao5 중에 주입될 수 있음
	
	public Ch12Service8() {
		log.info("실행 - Ch12Service8 객체 생성 ");
	}
}
