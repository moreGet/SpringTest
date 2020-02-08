package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import com.example.demo.common.Code;

public class GreetingException extends RuntimeException {

	private static final long serialVersionUID = 78400607852015282L;
	
	private final String code;
	private final String message;
	private final HttpStatus status;
	
	public GreetingException(String code, String message, HttpStatus status) {
		super();
		this.code = code;
		this.message = message;
		this.status = status;
	}
	
	
	public GreetingException(Code code, String message, HttpStatus status) {
		super();
		this.code = code.name();
		this.message = code.getMessage();
		this.status = status;
	}
	
	public GreetingException(Code code, HttpStatus status) {
		super();
		this.code = code.name();
		this.message = code.getMessage();
		this.status = status;
	}
	
	public static GreetingException fire(String code, String message, HttpStatus status) {
		return new GreetingException(code, message, status);
	}
	
	public static GreetingException fire(Code code, HttpStatus status) {
		return new GreetingException(code, status);
	}
	
	public String getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public HttpStatus getStaus() {
		return status;
	}
}
