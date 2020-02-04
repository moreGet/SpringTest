package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.UserRepository;

@Service
public class LoginUserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	
}
