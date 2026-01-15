package com.api.test.datadriven;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.api.utils.SpecUtil;
import com.dataprovider.api.bean.UserBean;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIDataDrivenTest {
	

	@Test(description = "Verifying if login API is working for iamfd", groups = {"api", "regression", "datadriven"},
			dataProviderClass = com.dataprovider.DataProviderUtils.class, dataProvider = "LoginAPIDataProvider")
	public void loginAPITest(UserBean userbean){
		
		given()
		.spec(SpecUtil.requestSpec(userbean))
		.when()
			.post("login")
		.then()
		.spec(SpecUtil.responseSpec_OK())
			.body("message", equalTo("Success"))
		.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));

	}

}
