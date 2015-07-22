package com.freemindcafe.spring.core.di.sample3;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/freemindcafe/spring/core/di/sample3/Test.xml"})
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class Test {
	
	@Autowired
	private ApplicationContext applicationContext;	
 
	@org.junit.Test
	public void test(){
		Vehicle vehicle = (Vehicle)applicationContext.getBean("vehicle");
		Assert.assertEquals("hundai", vehicle.getMake());
		Assert.assertEquals("i10", vehicle.getModel());
		Assert.assertEquals(1446, vehicle.getRegistrationNumber());
	}

}
