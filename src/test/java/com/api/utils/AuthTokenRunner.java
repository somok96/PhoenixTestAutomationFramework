package com.api.utils;

import java.time.Duration;

import com.api.constants.Role;

public class AuthTokenRunner {

	public static void main(String[] args) throws InterruptedException {
		for(int i=1; i<=100; i++) {
			String token = AuthTokenProvider.getToken(Role.FD);
			Thread.sleep(Duration.ofSeconds(2));
			System.out.println(token);
		}
	}

}
