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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author ic033920
 * 
 * Create a new sample package and copy files from this package over there.
 * 
 * service1_Is_Non_Transactional_Hence_On_Exception_Data_Is_Persisted
 * service2_Is_Transactional_At_Implementation_Hence_Cglib_Runs_It_In_Txn_And_On_Exception_Data_Is_Not_Persisted
 * service3_Is_Transactional_At_Interface_But_Cglib_Ignores_Annotation_On_Interface_Hence_On_Exception_Data_Is_Persisted
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/freemindcafe/spring/jdbc/sample4/SampleServiceImplWorksAsItUsesCglibProxy.xml"})
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class SampleServiceImplWorksAsItUsesCglibProxyTest {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	/*
	 * Both ISampleService and SampleServiceImpl will work as spring uses cglib proxy.
	 */
	@Autowired
	private ISampleService sampleService;
	//private SampleServiceImpl sampleService;
	
	

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
	public void service2_Is_Transactional_At_Implementation_Hence_Cglib_Runs_It_In_Txn_And_On_Exception_Data_Is_Not_Persisted(){
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
	
	/*
	 * Spring recommends that you only annotate concrete classes (and methods of concrete classes) 
	 * with the @Transactional annotation, as opposed to annotating interfaces. 
	 * 
	 * You certainly can place the @Transactional annotation on an interface (or an interface method), 
	 * but this works only as you would expect it to if you are using interface-based proxies. 
	 * 
	 * The fact that Java annotations are not inherited from interfaces means that if you are using 
	 * class-based proxies (proxy-target-class="true") or the weaving-based aspect (mode="aspectj"), 
	 * then the transaction settings are not recognized by the proxying and weaving infrastructure, 
	 * and the object will not be wrapped in a transactional proxy, which would be decidedly bad.
	 */
	@Test
	public void service3_Is_Transactional_At_Interface_But_Cglib_Ignores_Annotation_On_Interface_Hence_On_Exception_Data_Is_Persisted(){
		System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
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
		Assert.assertEquals(1,publishers.size());
	}
	
 
	@AfterClass
	public static void afterClass(){
		System.out.println("after class");
	}
	
}
