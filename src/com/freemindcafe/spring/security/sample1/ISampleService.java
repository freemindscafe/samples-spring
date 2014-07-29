package com.freemindcafe.spring.security.sample1;

import org.springframework.security.access.prepost.PreAuthorize;

public interface ISampleService {
	
	@PreAuthorize("hasRole('role1')")
	void method1();
	
	@PreAuthorize("hasPermission(null, 'permission2')")
	void method2();
	
	@PreAuthorize("hasRole('role1') or hasPermission(null, 'permission2')")
	void method3();
	
	@PreAuthorize("authentication.principal == 'user1' and #context == 'abc'")
	void method4(String context);
	
	@PreAuthorize("hasRole('role3')")
	void method5(); 
	
	void method6();


}
