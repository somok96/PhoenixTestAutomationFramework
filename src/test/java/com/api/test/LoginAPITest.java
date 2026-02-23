package com.api.test;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.api.utils.SpecUtil;
import com.dataprovider.api.bean.UserBean;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)
@Epic("User Management")
@Feature("Authentication")
public class LoginAPITest {

	private UserBean usercredentials;
	private AuthService authService;

	@BeforeMethod(description = "Create the Payload for the Login API")
	public void setup() {
		usercredentials = new UserBean("iamfd", "password");
		authService = new AuthService();
	}

	@Story("Valid user should be able to login into system")
	@Description("Verify if FD user is able to login")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Verifying if login API is working for iamfd", groups = { "api", "regression", "smoke" })
	public void loginAPITest() throws IOException {

		authService.login(usercredentials).then().spec(SpecUtil.responseSpec_OK()).body("message", equalTo("Success"))
				.and()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));

	}

}
