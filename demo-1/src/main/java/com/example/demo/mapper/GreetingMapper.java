package com.example.demo.mapper;

import java.util.List;

import com.example.demo.dto.Greeting;
import com.example.demo.dto.Version;

public interface GreetingMapper {

	public List<Greeting> getGreeting();
	
	public Version getVersion(String appName);
	
}
