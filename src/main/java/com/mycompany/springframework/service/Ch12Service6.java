package com.mycompany.springframework.service;

import org.springframework.stereotype.Service;

import com.mycompany.springframework.dao.Ch12Dao1;
import com.mycompany.springframework.dao.Ch12Dao2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
//@AllArgsConstructor		// 필드를 매개변수로 갖는 생성자를 만듦
@NoArgsConstructor
public class Ch12Service6 {
	
	private Ch12Dao1 dao1;
	private Ch12Dao2 dao2;
	
	public Ch12Service6(Ch12Dao1 dao1, Ch12Dao2 dao2) {	// @AllArgsConstructor 있으면 생성자 없어도 됨
		this.dao1 = dao1;
		this.dao2 = dao2;
		log.info("실행 - Ch12Service6(Ch12Dao1 dao1, Ch12Dao2 dao2)");
	}
}
