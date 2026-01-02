package com.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import com.api.pojos.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {

	@Test
	public void loginAPITest() {

		UserCredentials usercrendentials = new UserCredentials("iamfd", "password");

		given().baseUri("http://64.227.160.186:9000/v1").and().contentType(ContentType.JSON).and()
				.accept(ContentType.JSON).and().body(usercrendentials).log().uri().log().body().log().method().log()
				.headers().when().post("login").then().statusCode(200).log().body().time(lessThan(1000L)).and()
				.body("message", equalTo("Success")).and().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));

	}

}
