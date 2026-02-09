package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_ENDPOINT = "/job/create";

	public static Response create(Role role, CreateJobPayload createJobPayload) {
		Response response = given().spec(SpecUtil.requestSpecWithAuth(role, createJobPayload)).log().all().when()
				.post("job/create");
		
		return response;
	}
}
