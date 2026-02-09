package com.api.services;

import static io.restassured.RestAssured.given;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class MasterService {

	private static final String MASTER_ENDPOINT = "/master";

	public static Response master(Role role) {
		Response response = given().spec(SpecUtil.requestSpecWithAuth(role)).when().post(MASTER_ENDPOINT);
		return response;
	}

	public static Response master() {
		Response response = given().spec(SpecUtil.requestSpec()).when().post(MASTER_ENDPOINT);
		return response;
	}

}
