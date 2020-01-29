package com.example.demo;

public class AddCalculator implements Calculator {

	public AddCalculator() {}
	
	@Override
	public int calc(int a, int b) {
		return a + b;
	}
}