package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.filters.SensitiveDataFilter;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {

	@Step("Setting up the BaseURI, ContentType as Application/JSON and attaching the SensitiveData Filter")
	public static RequestSpecification requestSpec() {

		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).addFilter(new SensitiveDataFilter()).build();

		return requestSpecification;

	}
	
	@Step("Setting up the BaseURI, ContentType as Application/JSON and attaching the SensitiveData Filter")
	public static RequestSpecification requestSpec(Object payload) {

		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).setBody(payload).addFilter(new SensitiveDataFilter()).build();

		return requestSpecification;

	}

	@Step("Setting up the BaseURI, ContentType as Application/JSON and attaching the SensitiveData Filter for a Role")
	public static RequestSpecification requestSpecWithAuth(Role role) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).addHeader("Authorization", AuthTokenProvider.getToken(role.FD)).addFilter(new SensitiveDataFilter()).build();
		return requestSpecification;

	}

	@Step("Setting up the BaseURI, ContentType as Application/JSON and attaching the SensitiveData Filter for a Role and attaching payload")
	public static RequestSpecification requestSpecWithAuth(Role role, Object payload) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).addHeader("Authorization", AuthTokenProvider.getToken(role.FD)).addFilter(new SensitiveDataFilter()).build();
		return requestSpecification;

	}

	@Step("Expecting the response to have Content Type as Application/JSON, Status as 200 and Response time less than 1000ms")
	public static ResponseSpecification responseSpec_OK() {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(200)
				.expectResponseTime(Matchers.lessThan(1000L)).build();

		return responseSpecification;
	}
	
	@Step("Expecting the response to have Content Type as Application/JSON, Response time less than 1000ms and status code")
	public static ResponseSpecification responseSpec_JSON(int responseCode) {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(responseCode)
				.expectResponseTime(Matchers.lessThan(1000L)).build();

		return responseSpecification;
	}

}
