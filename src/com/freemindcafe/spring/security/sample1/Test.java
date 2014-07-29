package com.freemindcafe.spring.security.sample1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/freemindcafe/spring/security/sample1/Test.xml"})
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class Test {
	
	@Autowired
	private ApplicationContext applicationContext;	
	
	@Autowired
	private ISampleService sampleService;
	
	@Before
	public void setupUser(){
		/*
		 * sampleService will not be a jdk dynamic proxy
		 */
		Assert.assertFalse(AopUtils.isJdkDynamicProxy(sampleService));
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("role1");
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("user1", "abc", authorities);
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_THREADLOCAL);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	@org.junit.Test
	public void pre_post_annotations_are_not_enabled_hence_no_authorization_check_is_performed(){
		sampleService.method1();
		sampleService.method2();
		sampleService.method3();
		sampleService.method4("abc");
		sampleService.method5();
		sampleService.method6();
	}
	
}
