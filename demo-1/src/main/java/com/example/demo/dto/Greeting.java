package com.example.demo.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//AllArgsConstructor -> 필드 모두를 인자로 받는 생성자 만듬
//NoArgsConstructor -> 인자없는 기본생성자 
//RequiredArgsConstructor -> final이나 notnull인자만 받는 생성자 만듬
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Greeting {

	int idx, status;
	String id, password, email;
//	MultipartFile upload_f;

}

