package com.demo;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Demo {
	
	private static Logger logger = LogManager.getLogger(Demo.class);

	public static void main(String[] args) {
		logger.info("Inside the main method");
		
		int a = 10;
		logger.info("Value of a {} ", a);
		
		int b = 20;
		logger.info("Value of b {} ", b);
		
		int result = a + b;
		logger.info("Final Result {} ", result);
		
		System.out.println(result);

	}

}
