package com.freemindcafe.spring.jdbc;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {"classpath:/com/freemindcafe/jdbc/abc.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class Test extends AbstractJUnit4SpringContextTests {

	@org.junit.Test
	public void m1(){
		
	}
}
