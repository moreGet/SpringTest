package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */

@RestController // 이 클래스가 웹 에서 요적을 받아들이는 컨트롤러 클래스임을 나타냄
@EnableAutoConfiguration // 스프링 부트에서 대단히 중요한 요소임 다양한 설정이 자동으로 수행되고 기존의 스프링 어플에 필요했던 설정 파일들이 필요 없게 됨
public class App 
{
	
	@RequestMapping // 이 메서드가 HTTP요청을 받아들이는 메소드임을 나타냄
	String home() {
		return "Hello World!"; // http응답 반환 컨트롤러 어노테이션 클래스에 속한 메서드에서 문자열을 반환하면 해당 문자열이 그대로 HTTP 응답이 되어 출력됨.
	}
	
    public static void main( String[] args )
    {
       // 스프링부트 어플을 실행하는 데 필요한 처리를 main() 메소드 안에 작성
       // @EnableAutoConfiguration 어노테이션이 붙은 클래스를 아래 클래스 메서드의 첫번째 인자로 지정합니다.
       SpringApplication.run(App.class, args);
    }
}