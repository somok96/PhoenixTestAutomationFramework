package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	
	private UserCredentials usercrendentials;
	
	
	@BeforeMethod(description = "Create the Payload for the Login API")
	public void setup() {
		usercrendentials = new UserCredentials("iamfd", "password");
	}
	
	

	@Test(description = "Verifying if login API is working for iamfd", groups = {"api", "regression", "smoke"})
	public void loginAPITest() throws IOException {
		
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
