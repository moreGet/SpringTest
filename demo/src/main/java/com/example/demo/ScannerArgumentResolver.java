package com.example.demo;

import java.io.InputStream;
import java.util.Scanner;

// Resolver : 결의, 결심, 결단
public class ScannerArgumentResolver implements ArgumentResolver{
	
	@Override
	public Argument resolve(InputStream is) {
		Scanner scanner = new Scanner(is);
		
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		return new Argument(a, b);
	}
}