package com.freemindcafe.spring.jdbc.sample4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author ic033920
 * 
 * Create a new sample package and copy files from this package over there.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/freemindcafe/spring/jdbc/sample4/IncorrectProxiesWillFailTheTest.xml"})
public class IncorrectProxiesWillFailTheTest {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	@Autowired	
	/*
	 * If we have @Transactional either on the interface or on the class.
	 * And if the class is implementing an interface ISampleService then the proxy
	 * will be an implementation of ISampleService and it will compose the actual
	 * classes object. But this actual classes object will not be available 
	 * for wiring because it will be not of the type SampleServiceImpl
	 */
	private SampleServiceImpl sampleService;
	

	@Test
	public void test(){
		/*
		 * We have @Transactional on the ISampleServiceImpl and on SampleServiceImpl.
		 * SampleServiceImpl is implementing ISampleServiceImpl then the proxy will 
		 * be of ISampleServiceImpl type and it will compose the SampleServiceImpl object. 
		 * But this actual classes object will not be available for wiring and bootstrap is fail.
		 * 
		 */
		throw new RuntimeException("Code should not reach here");
	}
	
 
	@AfterClass
	public static void afterClass(){
		System.out.println("after class");
	}
	
}
