package com.freemindcafe.spring.jdbc.sample3;

import org.springframework.transaction.annotation.Transactional;

public interface ISampleService {
	
	void service1();
	
	void service2();
	
	@Transactional
	void service3();

}
