package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constants.Role;
import com.api.request.model.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {

	public static RequestSpecification requestSpec() {

		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).log(LogDetail.URI).log(LogDetail.BODY).log(LogDetail.METHOD)
				.log(LogDetail.HEADERS).build();

		return requestSpecification;

	}

	public static RequestSpecification requestSpec(Object payload) {

		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).setBody(payload).log(LogDetail.URI).log(LogDetail.BODY)
				.log(LogDetail.METHOD).log(LogDetail.HEADERS).build();

		return requestSpecification;

	}

	public static RequestSpecification requestSpecWithAuth(Role role) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).addHeader("Authorization", AuthTokenProvider.getToken(role.FD))
				.log(LogDetail.URI).log(LogDetail.BODY).log(LogDetail.METHOD).log(LogDetail.HEADERS).build();
		return requestSpecification;

	}

	
	
	public static RequestSpecification requestSpecWithAuth(Role role, Object payload) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).addHeader("Authorization", AuthTokenProvider.getToken(role.FD)).setBody(payload)
				.log(LogDetail.URI).log(LogDetail.BODY).log(LogDetail.METHOD).log(LogDetail.HEADERS).build();
		return requestSpecification;

	}
	
	
	
	
	public static ResponseSpecification responseSpec_OK() {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).log(LogDetail.ALL)
				.expectResponseTime(Matchers.lessThan(1000L)).build();

		return responseSpecification;
	}
	
	
	public static ResponseSpecification responseSpec_JSON(int responseCode) {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(responseCode).log(LogDetail.ALL)
				.expectResponseTime(Matchers.lessThan(1000L)).build();

		return responseSpecification;
	}

}
