package com.freemindcafe.spring.core.di.sample1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/freemindcafe/spring/core/sample1/Test.xml"})
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class Test {
	
	@Autowired
	private ApplicationContext applicationContext;	
 
	@org.junit.Test
	public void test1(){
		Car myCar = (Car)applicationContext.getBean("mycar");
		Car myFriendsCar = (Car)applicationContext.getBean("myfriendscar");
		
		Assert.assertNotNull(myCar);
		Assert.assertNotNull(myFriendsCar);
		
		Assert.assertEquals(1446, myCar.getRegistrationNumber());
		Assert.assertEquals(1447, myFriendsCar.getRegistrationNumber());
		
		Assert.assertEquals(4, myCar.getNumberOfDoors());
		Assert.assertEquals(4, myFriendsCar.getNumberOfDoors());
		
	}

}
