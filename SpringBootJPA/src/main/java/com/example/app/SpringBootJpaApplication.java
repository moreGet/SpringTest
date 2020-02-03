package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.app.domain.Customer;
import com.example.app.repository.CustomerRepository;

@SpringBootApplication
public class SpringBootJpaApplication implements CommandLineRunner{
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public void run(String... args) throws Exception {
		int idx = 0;
		while(idx < 10) {
			customerRepository.save(new Customer(null, idx, "Phone"));
			idx++;
		}
		
		// 0페이지 부터 시작, 페이지 당 데이터 갯수
		Pageable pageable = PageRequest.of(0, 5);
		Page<Customer> page = customerRepository.findAllOrderByName(pageable);
		
		System.out.println("페이지 당 데이터 수 : " + page.getSize());
		System.out.println("현재 페이지 : " + page.getNumber());
		System.out.println("전체 페이지 수 : " + page.getTotalPages());
		System.out.println("전체 데이터 수 : " + page.getTotalElements());
		
		page.getContent().forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaApplication.class, args);
	}
}