package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.app.domain.Customer;

@SpringBootApplication
public class App implements CommandLineRunner{

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public void run(String... args) throws Exception {
		// customers 테이블에서 키를 지정하여 정보를 얻는 SQL를 작성
		String sql = "SELECT id, first_name, last_name FROM customers WHERE id = :id";
		
		// 파라미터로 기본 키의 값을 설정합니다.
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("id", 1);
		
		// ResultSet에서 Customer 객체를 생성하는 RowMapper<Customer>를 구현합니다.
		// Lambda식 으로 코드를 간소화 합니다.
		Customer result = jdbcTemplate.queryForObject(sql, param, (rs, rowNum) -> new Customer(
				rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name")));
		
		System.out.println("result = " + result);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
