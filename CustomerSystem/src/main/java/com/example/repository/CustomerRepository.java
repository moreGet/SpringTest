package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	/*
	 *  JpaRepository 클래스 에는 다음과 같은 CRUD(Create, Read, Update, Delete)
	 *  기본 조작용 메소드가 정의되ㅣ어 있으며, JpaRepository를 상속한 인터페이스를 만드는 작업만으로 아주 쉽게
	 *  리포지토리를 작성할 수 있습니다. 
	 *  
	 *  findOne, save, findAll, delete
	 */
//	@Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
//	Page<Customer> findAllOrderByName(Pageable pageable);

//	// SELECT X FROM 에 클래스 이름 넣어야함.
	@Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
	List<Customer> findAllOrderByNameList();
//	
//	@Query("SELECT x.firstName FROM Customer x")
//	List<Customer> findFirstNameList();
}