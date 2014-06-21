package com.freemindcafe.spring.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/freemindcafe/spring/core/TemplateTestDoNotModify-context.xml"})
public class TemplateTestDoNotModify {
	
	@Autowired
	private ApplicationContext applicationContext;	
 
	@Test
	public void testContext() {
	Assert.assertNotNull(applicationContext.getBean("test"));
	}

}
