package com.ensat;

import com.ensat.property.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;



// multipart 컨트롤러 추가시 메인클래스에 꼭 추가시켜줘야한다. 

@SpringBootApplication
@EnableConfigurationProperties({
	FileUploadProperties.class
})
public class TestApplication {

	public static void main(String[] args) {
		System.out.println("HERE is testApplication***************");
		SpringApplication.run(TestApplication.class, args);
	}

}

