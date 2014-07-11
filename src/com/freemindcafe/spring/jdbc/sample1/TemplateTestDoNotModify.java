package com.freemindcafe.spring.jdbc.sample1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
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

/**
 * @author ic033920
 * 
 * Create a new sample package and copy files from this package over there.
 * 
 * testToVerifyTheConnection
 *  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/freemindcafe/spring/jdbc/sample1/TemplateTestDoNotModify.xml"})
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class TemplateTestDoNotModify {

	@Autowired
	private ApplicationContext applicationContext;
	
	private JdbcTemplate jdbcTemplate;
	
	@BeforeClass
	public static void beforeClass(){
		System.out.println("before class");
	}
	
	@Before
	public void setup(){
		Assert.assertNotNull(applicationContext.getBean("jdbcTemplate"));
		this.jdbcTemplate = (JdbcTemplate)applicationContext.getBean("jdbcTemplate");
	}
 
	@Test
	public void testToVerifyTheConnection() {
		List<String> publishers = jdbcTemplate.query("select name from publisher where publisher_id=1", new ResultSetExtractor<List<String>>() {

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
		Assert.assertEquals("Addison Wesley Professional",publishers.get(0));
	}
	
	@AfterClass
	public static void afterClass(){
		System.out.println("after class");
	}
	
}
