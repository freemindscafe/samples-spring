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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author ic033920
 * 
 * Creating an in-memory database

 * To create an in-memory database, specify memory as the JDBC subprotocol. 
 * For example, to create an in-memory database named myDB using the embedded driver, 
 * use the following connection URL:
 * 
 * jdbc:derby:memory:embeddedDataSource;create=true
 * 
 * For the network client driver, use the following connection URL. 
 * Because the client driver does not understand the memory subprotocol, you must include it in the database name:
 * 
 * jdbc:derby://myhost:1527/memory:embeddedDataSource;create=true
 * 
 * Be careful to specify a colon (:) after memory.
 * 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/freemindcafe/spring/jdbc/sample1/TemplateTestDoNotModify.xml"})
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
