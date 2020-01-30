package com.example.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data의 역할
// class파일을 생성할때 각 필드의 setter/getter, toString, equals, hashCode 메소드가
// 생성되므로 소스코드가 간결해진다.
@Data
@AllArgsConstructor // 롬복이 제공하는 이 어노테이션들을 붙혀서 모든 필드를 인자로 받는 생성자를 만듭니다.
@NoArgsConstructor
public class Customer {
	private Integer id;
	private String firstName;
	private String lastName;
}