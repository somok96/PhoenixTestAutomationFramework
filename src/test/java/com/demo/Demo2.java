package com.demo;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Demo2 {

	private static Logger logger = LogManager.getLogger(Demo2.class);

	public static void main(String[] args) {
		logger.info("Inside the main method");

		int a = 10;
		logger.info("Value of a {} ", a);

		int b = 0;

		if (b == 0) {
			logger.warn("Value of b {} ", b);
		} else {
			logger.info("Value of b {} ", b);
		}

		int result = 0;
		try {
			result = a / b;
			logger.info("Final Result {} ", result);
			System.out.println(result);
		} catch (Exception e) {
			logger.error("Division is not possible", e);
		}

	}

}
