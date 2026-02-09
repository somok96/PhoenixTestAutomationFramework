package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class AuthService {
	
	private static final String LOGIN_ENDPOINT = "login";

	public static Response login(UserCredentials usercredentials) {
		Response response = given().spec(SpecUtil.requestSpec(usercredentials)).when().post(LOGIN_ENDPOINT);
		return response;
	}

}
