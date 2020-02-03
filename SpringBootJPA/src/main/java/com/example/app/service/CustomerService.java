package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Customer;
import com.example.app.repository.CustomerRepository;

@Service
//이러한 클래스를 DI컨테이너 에서 가져오고 해당 클래스에 속한 각 메소드를 다른 클래스에서 호출 하면 DB 트랜잭션이 자동으로 이루어 집니다.
//메서드가 제대로 실행되면 DB 트랜잭션이 커밋됩니다.
//실행 도중 오류가 발생하면 DB 트랜잭션이 롤백 됩니다.
//DI 컨테이너는 각 메소드 앞뒤에 처리를 추가한 클래스를 동적으로 생성합니다.
@Transactional
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	public Page<Customer> findAll(Pageable pageable) {
		return customerRepository.findAllOrderByName(pageable);
	}
	
	public Customer findOne(Integer id) {
		return customerRepository.getOne(id);
	}
	
	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Customer update(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public void delete(Integer id) {
		customerRepository.deleteById(id);
	}
}
