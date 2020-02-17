package com.example.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.app.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<com.example.app.domain.Customer, Integer> {

	/*
	 *  JpaRepository 클래스 에는 다음과 같은 CRUD(Create, Read, Update, Delete)
	 *  기본 조작용 메소드가 정의되ㅣ어 있으며, JpaRepository를 상속한 인터페이스를 만드는 작업만으로 아주 쉽게
	 *  리포지토리를 작성할 수 있습니다. 
	 *  
	 *  findOne, save, findAll, delete
	 */
	@Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
	Page<Customer> findAllOrderByName(Pageable pageable);
}
