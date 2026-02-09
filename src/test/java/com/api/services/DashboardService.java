package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.constants.Role;
import com.api.request.model.DetailsPayload;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class DashboardService {

	private static final String COUNT_ENDPOINT = "/dashboard/count";
	private static final String DETAILS_ENDPOINT = "/dashboard/details";

	public static Response count(Role role) {

		Response response = given().spec(SpecUtil.requestSpecWithAuth(role)).when().get(COUNT_ENDPOINT);

		return response;

	}

	public static Response count() {

		Response response = given().spec(SpecUtil.requestSpec()).when().get(COUNT_ENDPOINT);

		return response;

	}
	
	public static Response details(Role role, DetailsPayload payload) {
		Response response = given().spec(SpecUtil.requestSpecWithAuth(role))
				.body(payload)
				.when().post(DETAILS_ENDPOINT);
		return response;
	}

}
