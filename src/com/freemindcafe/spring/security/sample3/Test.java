package com.freemindcafe.spring.security.sample3;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/freemindcafe/spring/security/sample3/Test.xml"})
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class Test {
	
	@Autowired
	private ApplicationContext applicationContext;	
	
	@Autowired
	private ISampleService sampleService;
	
	@Before
	public void setupUser(){
		//sampleService is jdk dynamic proxy
		Assert.assertTrue(AopUtils.isJdkDynamicProxy(sampleService));
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("role2");
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("user2", "abc", authorities);
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_THREADLOCAL);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	@org.junit.Test(expected = AccessDeniedException.class)
	public void user2_can_not_execute_method1_as_it_does_not_has_role_role1(){
		sampleService.method1();
	}
	
	@org.junit.Test
	public void user2_can_execute_method2_as_it_have_permission_permission2(){
		sampleService.method2();
	}
	
	@org.junit.Test
	public void user2_can_execute_method3_as_it_has_permission_permission2(){
		sampleService.method3();
	}
	
	@org.junit.Test(expected = AccessDeniedException.class)
	public void user2_can_not_execute_method4_as_it_has_principal_as_user2(){
		sampleService.method4("abc");
	}
	
	@org.junit.Test(expected = AccessDeniedException.class)
	public void user2_can_not_execute_method5_as_it_does_not_has_role_role3(){
		sampleService.method5();
	}
	
	@org.junit.Test
	public void user2_can_execute_method6_as_it_does_not_have_authorization_checks(){
		sampleService.method6();
	}

}
