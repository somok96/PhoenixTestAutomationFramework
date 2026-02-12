package com.api.utils;

import static com.api.constants.Role.ENG;
import static com.api.constants.Role.FD;
import static com.api.constants.Role.QC;
import static com.api.constants.Role.SUP;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

public class AuthTokenProvider {

	private static Map<Role, String> tokenCache = new ConcurrentHashMap<Role, String>();
	private static final Logger LOGGER = LogManager.getLogger(AuthTokenProvider.class);
	private AuthTokenProvider() {
	}

	public static String getToken(Role role) {

		LOGGER.info("Checking if the token for {} is present in the cache", role);
		if (tokenCache.containsKey(role)) {
			LOGGER.info("token found for role {}", role);
			return tokenCache.get(role);
		}
		
		LOGGER.info("token Not found making login request for the role {}", role);
		UserCredentials usercredentials = null;

		if (role == FD) {
			usercredentials = new UserCredentials("iamfd", "password");
		} else if (role == SUP) {
			usercredentials = new UserCredentials("iamsup", "password");
		} else if (role == ENG) {
			usercredentials = new UserCredentials("iameng", "password");
		} else if (role == QC) {
			usercredentials = new UserCredentials("iamqc", "password");
		}

		String token = given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(JSON).body(usercredentials)
				.when().post("login").then().log().ifValidationFails().statusCode(200)
				.body("message", equalTo("Success")).extract().body().jsonPath().getString("data.token");
		
		LOGGER.info("Token cached for the future request");
		tokenCache.put(role, token);
		return token;

	}

}
