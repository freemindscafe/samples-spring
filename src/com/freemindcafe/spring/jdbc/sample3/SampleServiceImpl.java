package com.freemindcafe.spring.jdbc.sample3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

public class SampleServiceImpl implements ISampleService {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	@Override
	public void service1() {
		jdbcTemplate.update("insert into publisher values (2, 'test publisher 2')");
		throw new RuntimeException("service1 exception");
	}
	
	@Override
	@Transactional
	public void service2() {
		jdbcTemplate.update("insert into publisher values (3, 'test publisher 3')");
		throw new RuntimeException("service2 exception");

	}
	
	@Override
	public void service3() {
		jdbcTemplate.update("insert into publisher values (4, 'test publisher 4')");
		throw new RuntimeException("service3 exception");

	}	

}
