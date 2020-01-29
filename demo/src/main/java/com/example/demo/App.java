package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfig.class) // javaConfig흫 읽어들이기 위해 @Cunfiguration 어노테이션이 붙은 클래스를 지정
public class App {
	public static void main(String[] args) {
		
		// try() 안에 정의하는 방법을 try-with-resources이다. 처리가 끝나면 
		// 자동으로 close() 메소드가 호출되어  DI컨테이너가 소멸하고 어플리케이션을 종료합니다.
		try(ConfigurableApplicationContext context = 
				SpringApplication.run(App.class, args)) {
			
			// SpringApplication.run으로 @EnableAutoConfiguration을 붙힌 클래스를 지정
			// 이 메소드의 반환값은 DI컨테이너의 본체인 타입으로 반환 받습니다.
			System.out.println("Enter 2 numbers like 'a b' : ");
			ArgumentResolver ar = context.getBean(ArgumentResolver.class);
			
			Argument argument = ar.resolve(System.in);
			Calculator calculator = context.getBean(Calculator.class);
			
			int result = calculator.calc(argument.getA(), argument.getB());
			System.out.println("result = " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}