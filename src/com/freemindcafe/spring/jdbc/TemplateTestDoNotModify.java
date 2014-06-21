package com.freemindcafe.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/freemindcafe/spring/jdbc/TemplateTestDoNotModify.xml"})
public class TemplateTestDoNotModify {

	@Autowired
	private ApplicationContext applicationContext;
	
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup(){
		Assert.assertNotNull(applicationContext.getBean("jdbcTemplate"));
		this.jdbcTemplate = (JdbcTemplate)applicationContext.getBean("jdbcTemplate");
	}
 
	@Test
	public void testToVerifyTheConnection() {
		//truncate the table
		//insert the data
		//select the data

	}
	
}
