package com.example.demo.app;

import java.io.InputStream;
import java.util.Scanner;

import org.springframework.stereotype.Component;

// Resolver : 결의, 결심, 결단
@Component
public class ScannerArgumentResolver implements ArgumentResolver{
	
	@Override
	public Argument resolve(InputStream is) {
		Scanner scanner = new Scanner(is);
		
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		return new Argument(a, b);
	}
}