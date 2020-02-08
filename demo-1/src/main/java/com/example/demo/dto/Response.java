package com.example.demo.dto;

import com.example.demo.common.Code;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@JsonInclude(Include.NON_NULL)
public class Response {
	private String code;
	private String message;
	private Object result;
	
	public Response(Code responseCode) {
		this.code = responseCode.name();
		this.message = responseCode.getMessage();
	}
	
	public Response(Code responseCode, Object result) {
		super();
		this.code = responseCode.name();
		this.message = responseCode.getMessage();
		this.result = result;
	}
}
