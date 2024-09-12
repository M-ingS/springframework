package com.mycompany.springframework.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Ch13Board {
	
	private int bno;	//insert한 후에 값이 시퀀스에서 자동으로 부여됨(사용자가 정의할 수 x)
	private String btitle;
	private String bcontent;
	private Date bdate;
	private String mid;
	private int bhitcount;
	private String battachoname;
	private String battachsname;
	private String battachtype;
	private byte[] battachdata;
}
