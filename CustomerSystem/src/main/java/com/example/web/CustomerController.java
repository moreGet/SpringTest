package com.example.web;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.Customer;
import com.example.service.CustomerService;

// REST API와는 달리 화면 변경용 컨트롤러에는 이렇게 붙입니다.
@Controller
@RequestMapping("customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@ModelAttribute
	CustomerForm setUpForm() {
		return new CustomerForm();
	}
	
	// 스프링 MVC에서는 화면에 값을 넘겨주는 데 Model 객체를 사용 합니다.
	// 인자로 Model을 받아들이고 Model.addAttribute 메소드를 사용하여 화면에 넘겨줄 속성을 설정 합니다.
	@RequestMapping(method = RequestMethod.GET)
	String list(Model model) {
		List<Customer> customers = customerService.findAll();
		// findAll의 결과를 model에 설정 합니다.
		// 속성이름은 customers로 지정합니다.
		// 사용자는 이 customers를 사용하여 접속할 수 있습니다.
		model.addAttribute("customers", customers);
		// 컨트롤러에서 @Controller가 붙은 요청 처리 메소드는 뷰 이름, 즉 변경될 화면 이름을 반환 합니다.
		return "customers/list";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	String create(@Validated CustomerForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return list(model);
		}
		
		Customer customer = new Customer();
		BeanUtils.copyProperties(form, customer);
		customerService.create(customer);
		return "redirect:/customers";
	}
	
	@RequestMapping(value = "edit", params = "form", method = RequestMethod.GET)
	String editForm(@RequestParam Integer id, CustomerForm form) {
		Customer customer = customerService.findOne(id);
		
		BeanUtils.copyProperties(customer, form);
		return "customers/edit";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	String edit(@RequestParam Integer id, @Validated CustomerForm form,
			BindingResult result) {
		
		if(result.hasErrors()) {
			return editForm(id, form);
		}
		
		// 고객 편집 폼이 현재 고객 정보를 표시할 수 있도록
		// CustomerService.update() 메소드로 업데이트 합니다.
		// 업데이트 처리가 끝나면 목록 표시 화면으로 리다이렉트 합니다.
		Customer customer = new Customer();
		BeanUtils.copyProperties(form, customer);
		customer.setId(id);
		customerService.update(customer);
		return "redirect:/customers"; // 다시 화면 로딩
	}
	
	@RequestMapping(value = "edit", params = "goToTop")
	String goToTop() {
		return "redirect:/customers";
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	String edit(@RequestParam Integer id) {
		customerService.delete(id);
		return "redirect:/customers";
	}
}