package com.freemindcafe.spring.security.sample3;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class MyPermissionEvaluator implements PermissionEvaluator {
	
	private Map<String, List<String>> userPermissionMapping = new HashMap<>();
	{
		userPermissionMapping.put("user2", Arrays.asList("permission2"));
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;
		return userPermissionMapping.get((String)token.getPrincipal()).contains((String)permission);
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
		throw new RuntimeException("Should not reach here");
	}

}
