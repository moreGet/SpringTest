package com.example.app;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.app.domain.Customer;
import com.example.app.service.CustomerService;

// REST 웹 서비스의 엔드 포인트인 컨트롤러 클래스에는 이 어노테이션을 붙입니다.
@RestController
// 이 REST 웹 서비스에 접속하기 위한 경로를 @RequestMapping 어노테이션에 지정합니다.
@RequestMapping("api/customers")
public class CustomerRestController {
	@Autowired // 이미 작성된 CustomerService 클래스를 주입합니다.
	CustomerService customerService;
	
	// 이 메소드에 HTTP메소드 중 하나인 GET을 할당 합니다.
	// @RequestMapping 에서 지정한 경로에 접근 하면 getCustomers()가 실행 됩니다.
	@RequestMapping(method = RequestMethod.GET)
	// PageableDefault를 사용해 기본값을 세팅 할 수 있습니다.
	// 어노테이션에 page값과 size를 지정하지 않으면 0, 20 으로 각각 기본값으로 초기화 됩니다.
	Page<Customer> getCustomers(@PageableDefault(page = 0, size = 2) Pageable pageable) {
		// 페이징 정보를 CustomerService 에 넘겨주며 검색합니다.
		// 결과를 Page형태로 반환 하도록 합니다.
		Page<Customer> customers = customerService.findAll(pageable);
		return customers;
	}
	
	// 아래 메소드 에도 GET할당 
	// 주소값 지정을 플레이스홀더로 id값을 정해 주었기 떄문에
	// api/customers/id 값 으로 접근이 가능함
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	Customer getCustomer(@PathVariable Integer id) {
		Customer customer = customerService.findOne(id);
		return customer;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<Customer> postCustomers(@RequestBody Customer customer,
			// 컨텍스트 상대경로 URI를 쉽게 만들게 해주는 UriComponentsBuilder를
			// 컨트롤러 메소드의 인자로 지정
			UriComponentsBuilder uriBuilder) {
		
		Customer created = customerService.create(customer);
		
		// UriComponentsBuilder와 Customer 객체의 id로 리소스 URI를 만듭니다.
		// path() 메소드에 있는 {id}플레이스홀더며. buildAndExpand() 메소드에 넘겨준 값으로 치환 됩니다.
		URI location = uriBuilder.path("api/customers/{id}")
				.buildAndExpand(created.getId()).toUri();
		
		HttpHeaders headers = new HttpHeaders();
		// HttpHeaders객체로 HTTP응답 헤더를 만들고 location을 인자값으로 줌
		headers.setLocation(location);

		// HTTP응답 헤더를 설정하려면 메소드에서 Customer 객체가 아닌 밑의 자료형으로 반환 해야 합니다.
		//아래 처럼 각각 customer객체, 응답헤더인 headers객체, 상태 코드인 HttpStatus를 설정 합니다.
		return new ResponseEntity<Customer>(created, headers, HttpStatus.CREATED);
	}
	
	// 고객 한명 정보 업데이트
	// 아래 어노테이션으로 Http 메소드 중 PUT 할당
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	Customer putCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
		customer.setId(id);
		return customerService.update(customer);
	}
	
	// 고객 한 명의 정보 삭제
	// DELETE Http 메소드 할당
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT) // 정상동작 시 204 NO_CONTENT 반환
	void deleteCustomer(@PathVariable Integer id) {
		customerService.delete(id);
	}
}
