package com.api.services;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.utils.SpecUtil;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_ENDPOINT = "/job/create";
	private static final String SEARCH_ENDPOINT = "/job/search";
	private static final Logger LOGGER = LogManager.getLogger(JobService.class);
	
	@Step("Creating Inwarranty job with Create Job API")
	public static Response create(Role role, CreateJobPayload createJobPayload) {
		LOGGER.info("Making request to {} with the role {} and payload {}", CREATE_ENDPOINT, role, createJobPayload);
		Response response = given().spec(SpecUtil.requestSpecWithAuth(role, createJobPayload)).log().all().when()
				.post("job/create");

		return response;
	}

	@Step("Making Search API Request")
	public static Response search(Role role, Object payload) {
		LOGGER.info("Making request to {} and payload {}", SEARCH_ENDPOINT, payload);

		Response response = given().spec(SpecUtil.requestSpecWithAuth(role)).body(payload).post(SEARCH_ENDPOINT);
		return response;
	}
}
