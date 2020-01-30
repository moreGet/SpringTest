package com.example.demo.app;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data // class파일을 생성할때 각 필드의 setter/getter, toString, equals, hashCode 메소드가
// 생성되므로 소스코드가 간결해진다. 아래 필드는 final형태라 setter/getter가 생성되지 않는다.
@RequiredArgsConstructor
public class Argument {
	private final int a;
	private final int b;
}