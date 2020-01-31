package com.example.app.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Customer;

@Repository
// 이러한 클래스를 DI컨테이너 에서 가져오고 해당 클래스에 속한 각 메소드를 다른 클래스에서 호출 하면 DB 트랜잭션이 자동으로 이루어 집니다.
// 메서드가 제대로 실행되면 DB 트랜잭션이 커밋됩니다.
// 실행 도중 오류가 발생하면 DB 트랜잭션이 롤백 됩니다.
// DI 컨테이너는 각 메소드 앞뒤에 처리를 추가한 클래스를 동적으로 생성합니다.
@Transactional 
public class CustomerRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	SimpleJdbcInsert insert;
	
	// 클래스 객체가 생성 될 떄, 아래 어노테이션을 명시한 
	// 메소드는 별도로 우선 초기화 한다.
	// 이 어노테이션은 WAS에 띄어질떄 init가 객체생성 된다.
	@PostConstruct
	public void init() {
		insert = new SimpleJdbcInsert(
				// SompleJdbcInsert에 JdbcTemplate을 설정해야 하므로 NamedParameterJdbcTemplate에 속한
				// JdbcTemplate 객체를 받아옵니다.
				(JdbcTemplate) jdbcTemplate.getJdbcOperations())
				// SimpleJdbcInsert는 INSERT SQL을 자동으로 생성하므로 테이블 이름을 명시적으로 지정.
				.withTableName("customers")
				// 자동으로 번호가 매겨지는 기본 키 칼럼명을 지정합니다.
				.usingGeneratedKeyColumns("id");
	}
	
	// 템플릿을 이용하여 query() 메소드의 람다식을 이용하여 SQL 실행 결과를 자바 객체 리스트 형태로 가져옵니다.
	private static final RowMapper<Customer> customerRowMapper = (rs, i) -> {
		Integer id = rs.getInt("id");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		return new Customer(id, firstName, lastName);
	};
	
	// Select All Condition Block
	public List<Customer> findAll() {
		String sql = "SELECT id,first_name,last_name FROM customers ORDER BY id";
		
		// 리스트로 가져 옵니다. 이를 위해선 위 RowMapper 클래스의 query()를 구현 해야 합니다.
		// 위에선 람다식을 사용하여 구현 했습니다.
		List<Customer> customers = jdbcTemplate.query(
				sql, customerRowMapper);
		return customers;
	}
	
	// ID값을 기준으로 행을 가져 옵니다.
	// Select Condition Block
	public Customer findOne(Integer customerId) {
		String sql = "SELECT id,first_name,last_name FROM customers WHERE id=:id";
		
		// MapSqlParam... 클래스의 addValue() 메소드를 이용해 customerId 인자값을 기준으로 SELECT문을 수행합니다.
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
		return jdbcTemplate.queryForObject(sql, param, customerRowMapper);
	}
	
	// Update Block
	public Customer save(Customer customer) {
		String update = "UPDATE customer SET first_name=:firstName, last_name=:lastName WHERE id=:id";
		
		/*
		 * 업데이트 계열의 SQL을 실행하기 위해 템플릿의 Update() 메소드를 사용합니다.
		 * Customer 객체의 id필드가 null 이면 insert 문을 수행 합니다.
		 * 객체의 id필드가 null이 아니면 update 문을 수행 합니다.
		 */
		
		// BeanPropertySqlParameterSource를 사용하면 자바객체에 포함된 필드 이름과 
		// 값을 매핑한 SqlParameterSource가 자동으로 작성 됩니다.
		SqlParameterSource param = new BeanPropertySqlParameterSource(customer);
		if (customer.getId() == null) {
			// executeAndReturnKey() 메소드를 호출하면 SQL을 실행하고 자동으로 번호가 매겨진 ID가 반환됩니다.
			Number key = insert.executeAndReturnKey(param);
			customer.setId(key.intValue());
		} else {
			jdbcTemplate.update(update, param);
		}
		
		return customer;
	}
	
	// Delete Condition Block
	public void delete(Integer customerId) {
		String sql = "DELETE FROM customers WHERE id=:id";
		
		// delete문을 사용할 떄도 update문을 사용 합니다.
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
		jdbcTemplate.update(sql, param);
	}
}