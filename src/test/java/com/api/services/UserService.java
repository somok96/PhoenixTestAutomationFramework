package com.api.services;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class UserService {

	private static final String USER_DETAILS_ENDPOINT = "/userdetails";
	private static final Logger LOGGER = LogManager.getLogger(UserService.class);

	public Response userdetails(Role role) {
		LOGGER.info("Making request to {} for the role {}", USER_DETAILS_ENDPOINT, role);
		Response response = given().spec(SpecUtil.requestSpecWithAuth(role)).when().get(USER_DETAILS_ENDPOINT);
		return response;

	}

}
