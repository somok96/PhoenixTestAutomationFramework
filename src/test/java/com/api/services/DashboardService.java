package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class DashboardService {

	private static final String COUNT_ENDPOINT = "/dashboard/count";

	public static Response count(Role role) {

		Response response = given().spec(SpecUtil.requestSpecWithAuth(role)).when().get(COUNT_ENDPOINT);

		return response;

	}

	public static Response count() {

		Response response = given().spec(SpecUtil.requestSpec()).when().get(COUNT_ENDPOINT);

		return response;

	}

}
