package com.example.demo.app;

import org.springframework.stereotype.Component;

@Component
public class AddCalculator implements Calculator {

	public AddCalculator() {}
	
	@Override
	public int calc(int a, int b) {
		return a + b;
	}
}