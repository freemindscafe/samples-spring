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
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author ic033920
 * 
 * Create a new sample package and copy files from this package over there.
 * 
 * service1_Is_Non_Transactional_Hence_On_Exception_Data_Is_Persisted
 * service2_Is_Transactional_At_Implementation_Hence_On_Exception_Data_Is_Not_Persisted
 * service3_Is_Transactional_At_Interface_Hence_On_Exception_Data_Is_Not_Persisted
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/freemindcafe/spring/jdbc/sample4/SampleServiceImplWorksAsItDoesNotImplementInterface.xml"})
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class SampleServiceImplWorksAsItDoesNotImplementInterfaceTest {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	@Autowired
	/*
	 * If the target class implements no interfaces, Spring will use 
	 * CGLIB to create a new class on the fly that is a subclass ("extends") 
	 * the target class.
	 * 
	 * If cglib jar is not in class path, we will get the following exception.
	 * 
	 * Cannot proxy target class because CGLIB2 is not available. Add CGLIB to 
	 * the class path or specify proxy interfaces.
	 * 
	 * HERE WE WILL HAVE A CGLIB PROXY AND NOT JDK PROXY
	 */
	private SampleServiceImplDoesNotImplementInterface sampleService;
	
	@BeforeClass
	public static void beforeClass(){
		System.out.println("before class");
	}
	
	@Test
	public void service1_Is_Non_Transactional_Hence_On_Exception_Data_Is_Persisted(){
		try{
			sampleService.service1();
		}
		catch(RuntimeException ex){
			//ignore
		}
		List<String> publishers = jdbcTemplate.query("select name from publisher where publisher_id=2", new ResultSetExtractor<List<String>>() {

					@Override
					public List<String> extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						List<String> publihers = new ArrayList<>();
						while(rs.next()){
							publihers.add(rs.getString("name"));
						}
						return publihers;
					}
				});
		Assert.assertEquals("test publisher 2",publishers.get(0));
	}
	
	@Test
	public void service2_Is_Transactional_At_Implementation_Hence_On_Exception_Data_Is_Not_Persisted(){
		
		try{
			sampleService.service2();
		}
		catch(RuntimeException ex){
			//ignore
		}
		List<String> publishers = jdbcTemplate.query("select name from publisher where publisher_id=3", new ResultSetExtractor<List<String>>() {

					@Override
					public List<String> extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						List<String> publihers = new ArrayList<>();
						while(rs.next()){
							publihers.add(rs.getString("name"));
						}
						return publihers;
					}
				});
		Assert.assertEquals(0,publishers.size());
	}
	
	@Test
	public void service3_Is_Transactional_At_Implementation_Hence_On_Exception_Data_Is_Not_Persisted(){
		
		try{
			sampleService.service3();
		}
		catch(RuntimeException ex){
			//ignore
		}
		List<String> publishers = jdbcTemplate.query("select name from publisher where publisher_id=4", new ResultSetExtractor<List<String>>() {

					@Override
					public List<String> extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						List<String> publihers = new ArrayList<>();
						while(rs.next()){
							publihers.add(rs.getString("name"));
						}
						return publihers;
					}
				});
		Assert.assertEquals(0,publishers.size());
	}
	
 
	@AfterClass
	public static void afterClass(){
		System.out.println("after class");
	}
	
}
