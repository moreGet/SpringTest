//package com.example.demo.etc;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.example.demo.app.Argument;
//import com.example.demo.app.ArgumentResolver;
//import com.example.demo.app.Calculator;
//
//@Component
//public class Frontend {
//
//	@Autowired // 어노테이션을 붙인 필드는 DI 컨테이너가 주입해야 할 필드를 의미합니다.
//	ArgumentResolver argumentResolver;
//	@Autowired
//	Calculator calculator;
//	// DI컨테이너는 관리하고 있는 객체 중에서 @Autowired 어노테이션이 붙은 필드에 맞는 객체를 자동으로 찾아내어 주입.
//	// 이를 오토 와이어링 이라고 부릅니다.
//	
//	public void run() {
//		System.out.print("Enter 2 number like 'a b' : ");
//		// 오토 와이어링 으로 인해 자동으로 객체를 찾아줌.
//		Argument argument = argumentResolver.resolve(System.in);
//		int result = calculator.calc(argument.getA(), argument.getB());
//		System.out.println("result = " + result);
//	}
//}