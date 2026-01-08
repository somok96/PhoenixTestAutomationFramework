package com.api.test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;

public class CountAPITest {
	
	@Test
	public void verifyCountAPIResponse() {
		
		
			given()
			.spec(SpecUtil.requestSpecWithAuth(Role.FD))
			.when()
				.get("/dashboard/count")
			.then()
			.spec(SpecUtil.responseSpec_OK())
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
		.spec(SpecUtil.requestSpec())
		.when()
			.get("/dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_JSON(401));
	}
	
}
