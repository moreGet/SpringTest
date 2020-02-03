package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.app.domain.Customer;
import com.example.app.service.CustomerService;

@SpringBootApplication // 여기에 CompoScan 들어 있음.
public class SpringBootJpaApplication implements CommandLineRunner{
	
	@Autowired
	CustomerService cs;
	
	@Override
	public void run(String... args) throws Exception {
		int idx = 0;
		while(idx < 10) {
			cs.create(new Customer(null, idx, "Phone"));
			idx++;
		}
		
		// 페이지 반환
		Pageable pageable = PageRequest.of(0, 5);
		Page<Customer> page = cs.findAll(pageable);
		System.out.println("총 페이지 수 : " + page.getTotalPages());
		System.out.println("총 데이터 수 : " + page.getTotalElements());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}
}