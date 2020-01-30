package com.example.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;

@Configuration
public class AppConfig {
	
	@Autowired
	DataSourceProperties dataSrcProp; // 데이터 소스를 명시적으로 C드라이브에 저장 했습니다. 이 속성값 들을 가져옵니다.
	DataSource dataSrc; // 여기에 위 prop변수에 내포되어 있는 속성값 들을 아래 래핑 작업으로 래핑해줍니다.
	
	@Bean
	DataSource realDataSource() {
		// 스프링 데이터가 제공하는 Builder클래스로 DataSource 인스턴스를 생성 합니다.
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.dataSrcProp.getClassLoader())
				.url(this.dataSrcProp.getUrl())
				.username(this.dataSrcProp.getUsername())
				.password(this.dataSrcProp.getPassword());
		
		this.dataSrc = factory.build();
		return this.dataSrc;
	}
	
	@Bean
	@Primary
	DataSource dataSource() {
		// Log4jdbcProxyDataSource 클래스로 DataSource를 래핑합니다. 이 클래스가 DataSource에 구현된
		// 각각의 처리에 로깅 처리를 끼워 넣습니다.
		return new Log4jdbcProxyDataSource(this.dataSrc);
	}
}
