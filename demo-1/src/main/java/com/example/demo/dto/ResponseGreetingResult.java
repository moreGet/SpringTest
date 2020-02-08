package com.example.demo.dto;

import java.util.List;

import com.example.demo.common.Code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseGreetingResult {
	
	private String code;
	private String message;
	private String creationDate;
	private Integer count;
	private List<Greeting> result;
	
	public ResponseGreetingResult(Code responseCode) {
		this.code = responseCode.name(); 
		this.message = responseCode.getMessage();
	}
	
	public ResponseGreetingResult(Code responseCode, String creationDate, List<Greeting> result) {
		this.code = responseCode.name();
		this.message = responseCode.getMessage();
		this.result = result;
		this.creationDate = creationDate;
	}
	
	public ResponseGreetingResult(Code responseCode, Integer count, String creationDate, List<Greeting> result) {
		this.code = responseCode.name();
		this.count = count;
		this.message = responseCode.getMessage();
		this.result = result;
		this.creationDate = creationDate;
	}
}
