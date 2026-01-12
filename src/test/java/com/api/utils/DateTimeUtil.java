package com.api.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.testng.annotations.Test;

public class DateTimeUtil {

	private DateTimeUtil() {

	}

	@Test
	public static String getTimeWithDaysAgo(int days) {
		return Instant.now().minus(days,ChronoUnit.DAYS).toString();
				
	}

}
