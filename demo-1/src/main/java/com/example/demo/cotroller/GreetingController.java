package com.example.demo.cotroller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Greeting;
import com.example.demo.dto.ResponseGreetingResult;
import com.example.demo.service.GreetingService;

@RestController
public class GreetingController {

	@Autowired
	private GreetingService greetingService;
	
//	private static final String template = "Hello, %s!";
//	private final AtomicLong counter = new AtomicLong();
//
//	@PostMapping("/greeting")
//	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//		return new Greeting(counter.incrementAndGet(), String.format(template, name));
//	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "hello world";
	}
	@RequestMapping(value = "/mama", method=RequestMethod.GET)
	public ResponseEntity<ResponseGreetingResult> getGreeting() {
		System.out.println("여기는 컨트롤러");
		ResponseGreetingResult res = greetingService.getGreeting();
		return new ResponseEntity<ResponseGreetingResult>(res, HttpStatus.OK) ;
	}
	
}
