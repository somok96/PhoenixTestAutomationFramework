package com.api.services;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class UserService {

	private static final String USER_DETAILS_ENDPOINT = "/userdetails";

	public Response userdetails(Role role) {

		Response response = given().spec(SpecUtil.requestSpecWithAuth(role)).when().get(USER_DETAILS_ENDPOINT);
		return response;

	}

}
