package com.freemindcafe.spring.aop.sample1;

import java.util.List;

public class ServiceClassWithInterface implements ServiceInterface {

	@Override
	public void service1(List<String> list) {
		list.add("service1");
	}

	@Override
	public void service2(List<String> list) {
		list.add("service2");		
	}

}
