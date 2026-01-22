package com.api.test.datadriven;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIJSONDataDrivenTest {
	

	@Test(description = "Verifying if login API is working for iamfd", groups = {"api", "regression", "datadriven"},
			dataProviderClass = com.dataprovider.DataProviderUtils.class, dataProvider = "LoginAPIJsonDataProvider")
	public void loginAPITest(UserCredentials usercredentials){
		
		given()
		.spec(SpecUtil.requestSpec(usercredentials))
		.when()
			.post("login")
		.then()
		.spec(SpecUtil.responseSpec_OK())
			.body("message", equalTo("Success"))
		.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));

	}

}
