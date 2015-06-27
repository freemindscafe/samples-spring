package com.freemindcafe.spring.aop.sample1;

import org.aopalliance.aop.Advice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/freemindcafe/spring/aop/sample1/FirstTest.xml"})
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class FirstTest {
	
	@Autowired
	private ApplicationContext applicationContext;	
 
	@Test
	public void testContext() {
	Assert.assertNotNull(applicationContext.getBean("test"));
	}
	
	@Test
	public void test(){
		ProxyFactory proxyFactory = new ProxyFactory();
		
		proxyFactory.addInterface(A.class);
		
		proxyFactory.addAdvice(new Advice() {
		});
	}

}
