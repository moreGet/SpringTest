package com.example.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.app.domain.Customer;

public interface CustomerRepository extends JpaRepository<com.example.app.domain.Customer, Integer> {

	@Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName ASC")
	Page<Customer> findAllOrderByName(Pageable pageable);
}
