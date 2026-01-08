package com.api.test;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {
	
	@Test
	public void verifyMasterAPIResponse() {
		
			given()
			.spec(SpecUtil.requestSpecWithAuth(Role.FD))
			.when()
				.post("master")
			.then()
			.spec(SpecUtil.responseSpec_OK())
				.body("message",Matchers.equalTo("Success"))
				.body("data",Matchers.notNullValue())
				.body("data",Matchers.hasKey("mst_oem"))
				.body("data",Matchers.hasKey("mst_model"))
				.body("$", Matchers.hasKey("data"))
				.body("$",Matchers.hasKey("message"))
				.body("data.mst_oem.size()",Matchers.equalTo(2))
				.body("data.mst_model.size()",Matchers.greaterThan(0))
				.body("data.mst_oem.id",Matchers.everyItem(Matchers.notNullValue()))
				.body("data.mst_oem.name",Matchers.everyItem(Matchers.notNullValue()))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/masterAPIResponseSchema.json"));
	}
	
	
	@Test
	public void invalidTokenMasterAPITest() {
		
			given()
			.baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
			.header("Authorization","")
		.and()
			.contentType("")
		.and()
			.log().uri()
			.log().body()
			.log().method()
			.log().headers()
		.when()
			.post("master")
		.then()
			.log().all()
			.statusCode(401);
			
	}

}
