package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Greeting;
import com.example.demo.dto.ResponseGreetingResult;
import com.example.demo.dto.Version;
import com.example.demo.exception.GreetingException;
import com.example.demo.mapper.GreetingMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class GreetingService {
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Value("${greeting.file.path}")
	private String filePath; 
	
	@Autowired 
	private GreetingMapper greetingMapper;
	
	public ResponseGreetingResult getGreeting() {
		System.out.println("여기는 서비스0");
		Version latestVersion = this.getGreetingVersion("record");
		List<Greeting> list = this.getGreetingList();
		System.out.println("여기는 서비스1" + list);
		//String jsonFilePath = filePath + File.separator;
		//System.out.println("여기는 서비스2");
//		String jsonFile = latestVersion.getVersion() + ".json";
//		System.out.println("여기는 서비스3" + jsonFile);
//		String greetingJson = jsonFilePath + jsonFile;
//		System.out.println("여기는 서비스4" + greetingJson);
		ResponseGreetingResult result = new ResponseGreetingResult();
		result.setResult(list);
//		System.out.println("여기는 서비스5" );
//		ObjectMapper mapper = new ObjectMapper();
//		try {
////			result = mapper.readValue(new File(greetingJson), ResponseGreetingResult.class);
//		} catch (JsonParseException e) {
//			throw GreetingException.fire("E500", "JsonParseException:"+e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		} catch (JsonMappingException e) {
//			throw GreetingException.fire("E500", "JsonMappingException:"+e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		} catch (IOException e) {
//			throw GreetingException.fire("E500", "IOException:"+e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);			
//		}
		 
		return result;
	}
	
	private List<Greeting> getGreetingList() {
		
		return greetingMapper.getGreeting();
	}

	private Version getGreetingVersion(String appName) {
		return greetingMapper.getVersion(appName);
	}

	private String currentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		Date currentDate = new Date();
		return sdf.format(currentDate);
	}
}
