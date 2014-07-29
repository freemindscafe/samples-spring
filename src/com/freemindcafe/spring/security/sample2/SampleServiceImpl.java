package com.freemindcafe.spring.security.sample2;

import org.junit.Assert;
import org.springframework.aop.support.AopUtils;

public class SampleServiceImpl implements ISampleService{

	@Override
	public void method1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method3() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method4(String context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method5() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method6() {
		Assert.assertFalse(AopUtils.isJdkDynamicProxy(this));
		this.method2();		
	}

}
