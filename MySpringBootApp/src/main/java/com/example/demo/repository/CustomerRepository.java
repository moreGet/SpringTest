package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Customer;

@Repository
public class CustomerRepository {
	private final ConcurrentMap<Integer, Customer> customerMap = 
			new ConcurrentHashMap<Integer, Customer>();
	
	// 전부 출력
	public List<Customer> findAll() {
		return new ArrayList<Customer>(customerMap.values());
	}
	
	// HashMap 요소 Key값 으로 찾기
	public Customer findOne(Integer customerId) {
		return customerMap.get(customerId);			
	}
	
	// HashMap 요소 추가
	public Customer save(Customer customer) {
		return customerMap.put(customer.getId(), customer);
	}
	
	// HashMap 요소 삭제
	public void delete(Integer customerId) {
		customerMap.remove(customerId);
	}
}
