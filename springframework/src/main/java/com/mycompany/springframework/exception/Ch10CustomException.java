package com.mycompany.springframework.exception;

public class Ch10CustomException extends RuntimeException{
	public Ch10CustomException() {
	}

	public Ch10CustomException(String message){
		super(message);
	}
}
