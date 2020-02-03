package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.Customer;
import com.example.service.CustomerService;

// REST API와는 달리 화면 변경용 컨트롤러에는 이렇게 붙입니다.
@Controller
// URL이 /customers를 포함할 떄 list() 메소드에 매핑하도록 이렇게 붙입니다.
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	// 스프링 MVC에서는 화면에 값을 넘겨주는 데 Model 객체를 사용 합니다.
	// 인자로 Model을 받아들이고 Model.addAttribute 메소드를 사용하여 화면에 넘겨줄 속성을 설정 합니다.
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	String list(Model model) {
		List<Customer> customers = customerService.findAllList();
		// findAll의 결과를 model에 설정 합니다.
		// 속성이름은 customers로 지정합니다.
		// 사용자는 이 customers를 사용하여 접속할 수 있습니다.
		model.addAttribute("customers", customers);
		// 컨트롤러에서 @Controller가 붙은 요청 처리 메소드는 뷰 이름, 즉 변경될 화면 이름을 반환 합니다.
		// 스프링 부트에서는 기본값으로 classpath:templates/+ "뷰이름" + .html이 화면 경로가 됩니다.
		// 이 예제에서는 classpath:templates/customers/list.html을 표시합니다.
		return "customers/list";
	}
}
