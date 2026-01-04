package com.api.utils;

import static com.api.constants.Role.ENG;
import static com.api.constants.Role.FD;
import static com.api.constants.Role.QC;
import static com.api.constants.Role.SUP;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

import com.api.constants.Role;
import com.api.pojos.UserCredentials;

public class AuthTokenProvider {

	private AuthTokenProvider() {
	}

	public static String getToken(Role role) {
		
		UserCredentials usercredentials = null;
		
		if(role == FD) {
			usercredentials = new UserCredentials("iamfd", "password");
		}
		else if(role == SUP)
		{
			usercredentials = new UserCredentials("iamsup", "password");
		}
		else if(role == ENG)
		{
			usercredentials = new UserCredentials("iameng", "password");
		}
		else if(role == QC)
		{
			usercredentials = new UserCredentials("iamqc", "password");
		}

		String token = given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(JSON)
				.body(usercredentials).when().post("login").then().log().ifValidationFails()
				.statusCode(200).body("message", equalTo("Success")).extract().body().jsonPath()
				.getString("data.token");

		return token;

	}

}
