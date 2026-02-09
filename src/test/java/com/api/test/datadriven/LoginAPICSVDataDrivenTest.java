package com.api.test.datadriven;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.api.utils.SpecUtil;
import com.dataprovider.api.bean.UserBean;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPICSVDataDrivenTest {
	
	private AuthService authService;
	
	@BeforeMethod(description = "Initializing the Auth Service")
	public void setup() {
		authService = new AuthService();
	}

	@Test(description = "Verifying if login API is working for iamfd", groups = {"api", "regression", "datadriven"},
			dataProviderClass = com.dataprovider.DataProviderUtils.class, dataProvider = "LoginAPIDataProvider")
	public void loginAPITest(UserBean userbean){
		
		authService.login(userbean)
		.then()
		.spec(SpecUtil.responseSpec_OK())
			.body("message", equalTo("Success"))
		.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));

	}

}
