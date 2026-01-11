package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	@Test
	public void loginAPITest() throws IOException {
		
		
		UserCredentials usercrendentials = new UserCredentials("iamfd", "password");
		
		given()
		.spec(SpecUtil.requestSpec(usercrendentials))
		.when()
			.post("login")
		.then()
		.spec(SpecUtil.responseSpec_OK())
			.body("message", equalTo("Success"))
		.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));

	}

}
