package com.api.services;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class AuthService {
	
	private static final String LOGIN_ENDPOINT = "/login";
	private static final Logger LOGGER = LogManager.getLogger(AuthService.class);

	public static Response login(Object usercredentials) {
		LOGGER.info("Making login request for the payload {}", ((UserCredentials)usercredentials).username());
		Response response = given().spec(SpecUtil.requestSpec(usercredentials)).when().post(LOGIN_ENDPOINT);
		return response;
	}

}
