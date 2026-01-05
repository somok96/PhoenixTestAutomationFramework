package com.api.test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;

public class CountAPITest {
	
	@Test
	public void verifyCountAPIResponse() {
		
		
			given()
				.baseUri(ConfigManager.getProperty("BASE_URI"))
			.and()
				.header("Authorization",AuthTokenProvider.getToken(Role.FD))
			.and()
				.log().uri()
				.log().body()
				.log().method()
				.log().headers()
			.when()
				.get("/dashboard/count")
			.then()
				.log().all()
				.statusCode(200)
				.body("message",equalTo("Success"))
				.time(lessThan(1000L))
				.body("data",notNullValue())
				.body("data.size()",equalTo(3))
				.body("data.count", everyItem(greaterThanOrEqualTo(0)))
				.body("data.label", everyItem(not(blankOrNullString())))
				.body("data.key", containsInAnyOrder("pending_for_delivery","pending_fst_assignment","created_today"))
				.body(matchesJsonSchemaInClasspath("response-schema/countAPIResponseSchema-FD.json"));
	}

	@Test
	public void countAPITest_MissingAuthToken()
	{
		given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
			.log().uri()
			.log().body()
			.log().method()
			.log().headers()
		.when()
			.get("/dashboard/count")
		.then()
			.log().all()
			.statusCode(401);
	}
	
}
