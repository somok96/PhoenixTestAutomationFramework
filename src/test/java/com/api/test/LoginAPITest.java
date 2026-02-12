package com.api.test;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.api.utils.SpecUtil;
import com.dataprovider.api.bean.UserBean;

import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)
public class LoginAPITest {

	private UserBean usercredentials;
	private AuthService authService;

	@BeforeMethod(description = "Create the Payload for the Login API")
	public void setup() {
		usercredentials = new UserBean("iamfd", "password");
		authService = new AuthService();
	}

	@Test(description = "Verifying if login API is working for iamfd", groups = { "api", "regression", "smoke" })
	public void loginAPITest() throws IOException {

		authService.login(usercredentials).then().spec(SpecUtil.responseSpec_OK()).body("message", equalTo("Success"))
				.and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));

	}

}
