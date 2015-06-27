package com.freemindcafe.spring.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sun.net.www.protocol.file.FileURLConnection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/freemindcafe/spring/core/io/FirstTest.xml"})
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class FirstTest {
	
	@Autowired
	private ApplicationContext applicationContext;	
	
	@Test
	public void file_loaded_from_directory_classpath_results_in_a_FileURLConnection() throws Exception {
		Properties props = new Properties();
		URL url = this.getClass().getClassLoader().getResource("com/freemindcafe/spring/core/io/FirstTest.properties");
		URLConnection connection = url.openConnection();
		Assert.assertTrue(connection instanceof FileURLConnection);
		props.load(connection.getInputStream());
		Assert.assertEquals(props.get("key1"), "value1");
	}
	
	@Test
	public void folder_loaded_from_directory_classpath_results_in_a_FileURLConnection() throws Exception {
		Properties props = new Properties();
		URL url = this.getClass().getClassLoader().getResource("com/freemindcafe/spring/core/io");
		Assert.assertTrue(url.getProtocol().equalsIgnoreCase("file"));
		URLConnection connection = url.openConnection();
		Assert.assertTrue(connection instanceof FileURLConnection);
		InputStream inputStream = null;;
		File file = new File(url.getPath());
		if(file.isDirectory()){
			for(File propFile : file.listFiles()){
				if(propFile.getName().contains(".properties")){
					inputStream = new FileInputStream(propFile);
					break;
				}
			}
			
		}
		else{
			inputStream = connection.getInputStream();
		}
		props.load(inputStream);
		Assert.assertEquals(props.get("key1"), "value1");
	}
	
	@Test
	public void file_loaded_from_jar_classpath_results_in_a_JarURLConnection() throws Exception {
		URL url = this.getClass().getClassLoader().getResource("com/freemindcafe/spring/core/io/sample/sample1.properties");
		URLConnection connection = url.openConnection();
		Assert.assertTrue(connection instanceof JarURLConnection);
		//InputStream inputStream = connection.getInputStream();
		//Assert.assertTrue(inputStream instanceof JarURLConnection$);
		Properties props = new Properties();
		props.load(connection.getInputStream());
		Assert.assertEquals(props.get("key1"), "value1");
	}

		
	@Test
	public void folder_loaded_from_jar_classpath_results_in_a_JarURLConnection() throws Exception {
		Properties props = new Properties();
		URL url = this.getClass().getClassLoader().getResource("com/freemindcafe/spring/core/io/sample");
		URLConnection connection = url.openConnection();
		Assert.assertTrue(connection instanceof JarURLConnection);
		JarURLConnection jarConnection = (JarURLConnection)connection;
		//InputStream inputStream = connection.getInputStream();
		//Assert.assertTrue(inputStream instanceof JarInputStream);
		JarFile jarFile = jarConnection.getJarFile();
		JarEntry jarEntry;
		for (Enumeration<JarEntry> entries = jarFile.entries() ; entries.hasMoreElements() ;)
	    {
	        JarEntry entry = entries.nextElement();
	        String file = entry.getName();
	        if (file.endsWith(".properties"))
	        {
	        	props.load(jarFile.getInputStream(entry));
	        }
	    }
		Assert.assertEquals(props.get("key1"), "value1");
		Assert.assertEquals(props.get("key3"), "value3");
	}
	
	@Test
	public void file_loaded_from_jar_or_directory_classpath_can_be_read_by_getting_a_stream() throws Exception {
		Properties props = new Properties();
		//JAR
		URL url = this.getClass().getClassLoader().getResource("com/freemindcafe/spring/core/io/sample/sample2.properties");
		URLConnection connection = url.openConnection();
		props.load(connection.getInputStream());
		//DIRECTORY CLASSPATH
		url = this.getClass().getClassLoader().getResource("com/freemindcafe/spring/core/io/FirstTest.properties");
		connection = url.openConnection();
		props.load(connection.getInputStream());
		Assert.assertEquals(props.get("key1"), "value1");
		Assert.assertEquals(props.get("key3"), "value3");
	}
	
	@Test
	public void url_loader_can_load_the_file_from_classpath_or_from_file_system(){
		//FIXME
	}
	
	@Test
	public void resource_loader_can_load_the_file_from_classpath_or_from_file_system(){
		//FIXME
	}

}
