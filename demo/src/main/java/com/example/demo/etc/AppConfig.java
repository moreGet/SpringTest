package com.example.demo.etc;
//package com.example.demo;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration // 이 클래스가 JavaConfig용 클래스임을 컴파일러에게 알립니다.
//public class AppConfig {
//	
//	@Bean // DI컨테이너가 관리할 Bean을 생성하는 메서드에는 이 어노테이션을 붙힙니다.
//	public Calculator calculator() {
//		// @Bean 어노테이션으로 인해 기본적으로 메서드 이름이 Bean이름이 됩니다.
//		// 또한 기본적으로 이 메소드가 생성한 이름은 싱글톤 형태로 DI컨테이너 별로 한 개만 생성 됩니다.
//		return new AddCalculator(); // Calculator 타입의 싱글톤 객체
//	}
//	
//	@Bean // DI컨테이너가 관리할 Bean을 생성하는 메서드
//	// DI컨테이너 = App.java 파일 
//	ArgumentResolver argumentResolver() {
//		return new ScannerArgumentResolver();
//	}
//	
//	@Bean // DI컨테이너가 관리할 Bean을 생성하는 메서드
//	Frontend frontend() {
//		return new Frontend();
//	}
//}