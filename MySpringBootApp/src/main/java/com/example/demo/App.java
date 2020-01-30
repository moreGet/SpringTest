package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Customer;
import com.example.demo.service.CustomerService;

@SpringBootApplication
public class App implements CommandLineRunner{
	
	@Autowired
	CustomerService customerService;
	
	@Override
	public void run(String... args) throws Exception {
		// 데이터 추가
		customerService.save(new Customer(1,  "Nobita", "Nobi"));
		customerService.save(new Customer(2, "Shin", "SungHyeun"));
		customerService.save(new Customer(3, "Kim", "ShinHan"));
		
		// 데이터 표시
		customerService.findAll().forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}