package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.app.Argument;
import com.example.demo.app.ArgumentResolver;
import com.example.demo.app.Calculator;

@SpringBootApplication
public class App implements CommandLineRunner{ // 앞서 구현한 FrontEnd 클래스의 Run메소드와 같은 역할
	
	@Autowired // 자동 오토 와이어링 으로 인해 자동으로 객체를 찾아주는 역할을 하라 하는 어노테이션
	Calculator calculator;
	@Autowired
	ArgumentResolver argResolver;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Enter 2 number like 'a b' : ");
		
		// 오토 와이어링 으로 인한 자동객체 대입
		Argument argument = argResolver.resolve(System.in);
		
		// 오토 와이어링...
		// Argument 에서 구현한 InputStream 으로 받은 키보드 인자값을 계산;
		int result = calculator.calc(argument.getA(), argument.getB());
		System.out.println("result : " + result);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}