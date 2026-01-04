package com.api.test;

import static com.api.utils.AuthTokenProvider.getToken;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.constants.Role.*;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
	@Test
	public void userDetailsAPITest() throws IOException {
		
		Header authToken = new Header("Authorization", getToken(FD));
	
		
		
		given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
			.header(authToken)
		.and()
			.contentType(ContentType.JSON)
			.log().uri()
			.log().body()
			.log().method()
			.log().headers()
		.when()
			.get("userdetails")
		.then()
			.statusCode(200)
			.log().all()
			.time(lessThan(1500L))
		.and()
			.body("message", equalTo("Success"))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/userDetailsResponseSchema.json"));

	}

}
