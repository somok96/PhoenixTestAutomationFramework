package com.api.services;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constants.Role;
import com.api.utils.SpecUtil;

import io.restassured.response.Response;

public class MasterService {

	private static final String MASTER_ENDPOINT = "/master";
	private static final Logger LOGGER = LogManager.getLogger(MasterService.class);

	public static Response master(Role role) {
		LOGGER.info("Making request to {} with role {}", MASTER_ENDPOINT, role);
		Response response = given().spec(SpecUtil.requestSpecWithAuth(role)).when().post(MASTER_ENDPOINT);
		return response;
	}

	public static Response master() {
		LOGGER.info("Making request to {} with no authentication token", MASTER_ENDPOINT);
		Response response = given().spec(SpecUtil.requestSpec()).when().post(MASTER_ENDPOINT);
		return response;
	}

}
