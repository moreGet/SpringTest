package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domain.Customer;
import com.example.service.CustomerService;

@SpringBootApplication()
public class App implements CommandLineRunner{
	
	@Autowired
	CustomerService cs;
	
	@Override
	public void run(String... args) throws Exception {
		
//		for (int i = 0; i < 10; i++) {
//			cs.create(new Customer(null, String.valueOf(i), "성현"));
//		}
	}	
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}