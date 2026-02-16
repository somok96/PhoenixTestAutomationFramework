package com.api.test.datadriven;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.api.utils.SpecUtil;
import com.dataprovider.api.bean.UserBean;

import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)
public class LoginAPIExcelDataDrivenTest {

	private AuthService authService;

	@BeforeMethod(description = "Setting up the Auth Service reference")
	public void setup() {
		authService = new AuthService();
	}

	@Test(description = "Verifying if login API is working for iamfd", groups = {"api", "regression", "datadriven"},
			dataProviderClass = com.dataprovider.DataProviderUtils.class, dataProvider = "LoginAPIExcelDataProvider")
	public void loginAPITest(UserBean userBean){
		
		authService.login(userBean)
		.then()
		.spec(SpecUtil.responseSpec_OK())
			.body("message", equalTo("Success"))
		.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));

	}

}
