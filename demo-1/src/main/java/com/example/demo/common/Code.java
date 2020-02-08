package com.example.demo.common;



public enum Code {
	S000("SUCCESS")
	, E001("BAD REQUEST")
	, E404("Not Found")
	, E500("INTERNAL SERVICE FAILURE")
	;
	
	private String message;
	
	private Code(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
