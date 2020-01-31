package com.example.app.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.domain.Customer;

@Repository
@Transactional
public class CustomerRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final RowMapper<Customer> customerRowMapper = (rs, i) -> {
		Integer id = rs.getInt("id");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		return new Customer(id, firstName, lastName);
	};
	
	// Select All Condition Block
	public List<Customer> findAll() {
		String sql = "SELECT id,first_name,last_name FROM customers ORDER BY id";
		
		List<Customer> customers = jdbcTemplate.query(
				sql, customerRowMapper);
		return customers;
	}
	
	// Select Condition Block
	public Customer findOne(Integer customerId) {
		String sql = "SELECT id,first_name,last_name FROM customers WHERE id=:id";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
		return jdbcTemplate.queryForObject(sql, param, customerRowMapper);
	}
	
	// Update Block
	public Customer save(Customer customer) {
		String sql = "INSERT INTO customers(first_name, last_name) ";
		String values = "values(:firstName, :lastName)";
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(customer);
		if (customer.getId() == null) {
			jdbcTemplate.update(sql+values, param);
		}
		
		return customer;
	}
	
	// Delete Condition Block
	public void delete(Integer customerId) {
		String sql = "DELETE FROM customers WHERE id=:id";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
		jdbcTemplate.update(sql, param);
	}
}
