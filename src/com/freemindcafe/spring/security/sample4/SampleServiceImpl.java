package com.freemindcafe.spring.security.sample4;

import org.junit.Assert;
import org.springframework.aop.support.AopUtils;
import org.springframework.security.access.prepost.PreAuthorize;

public class SampleServiceImpl{

	@PreAuthorize("hasRole('role1')")
	public void method1() {
		// TODO Auto-generated method stub
		
	}

	@PreAuthorize("hasPermission(null, 'permission2')")
	public void method2() {
		// TODO Auto-generated method stub
		
	}

	@PreAuthorize("hasRole('role1') or hasPermission(null, 'permission2')")
	public void method3() {
		// TODO Auto-generated method stub
		
	}

	@PreAuthorize("authentication.principal == 'user1' and #context == 'abc'")
	public void method4(String context) {
		// TODO Auto-generated method stub
		
	}

	@PreAuthorize("hasRole('role3')")
	public void method5() {
		// TODO Auto-generated method stub
		
	}

	public void method6() {
		Assert.assertFalse(AopUtils.isCglibProxy(this));
		this.method2();		
	}

}
