package com.freemindcafe.spring.aop.sample1;

import java.util.List;

public class ServiceClassWithNoInterface {
	
	public void service1(List<String> list) {
		list.add("service1");
	}

	public void service2(List<String> list) {
		list.add("service2");		
	}

}
