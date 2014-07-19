package com.freemindcafe.spring.expression.sample1;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/com/freemindcafe/spring/expression/sample1/FirstTest.xml"})
public class FirstTest {
	
	@Test
	public void spel_When_Employee_Object_Set_As_StandardEvaluationContext_Root(){
		ExpressionParser parser = new SpelExpressionParser();
		
		EvaluationContext context = new StandardEvaluationContext(new Employee("nikhil",1L)); 
		
		Expression exp1 = parser.parseExpression("name");
		Expression exp2 = parser.parseExpression("salary");
		
		//Object getValue(EvaluationContext context) 
		String name = (String) exp1.getValue(context);
		//Use Long.class for compile time safety
		//<T> T getValue(EvaluationContext context, Class<T> desiredResultType) 
		long salary = exp2.getValue(context,Long.class);
		
		Assert.assertEquals("nikhil", name);
		Assert.assertEquals(1l, salary);
		
	}
	
	@Test
	public void spel_When_Employee_Object_Set_As_StandardEvaluationContext_Variable(){
		ExpressionParser parser = new SpelExpressionParser();
		
		EvaluationContext context = new StandardEvaluationContext(); 
		context.setVariable("emp", new Employee("rikhil",2L));
		
		Expression exp1 = parser.parseExpression("#emp.name");
		Expression exp2 = parser.parseExpression("#emp.salary");

		
		//Object getValue(EvaluationContext context) 
		String name = (String) exp1.getValue(context);
		//Use Long.class for compile time safety
		//<T> T getValue(EvaluationContext context, Class<T> desiredResultType) 
		long salary = exp2.getValue(context,Long.class);
		
		Assert.assertEquals("rikhil", name);
		Assert.assertEquals(2l, salary);
		
		
	}

}
