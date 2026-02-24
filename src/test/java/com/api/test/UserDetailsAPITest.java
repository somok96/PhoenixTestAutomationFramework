package com.api.test;

import static com.api.constants.Role.FD;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.UserService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(com.listeners.APITestListener.class)
@Epic("User Management")
@Feature("User Details")
public class UserDetailsAPITest {

	UserService userService;

	@BeforeMethod(description = "Initializing the UserDetails Service")
	public void setup() {
		userService = new UserService();

	}

	@Story("User details should be shown")
	@Description("Verify if the Userdetails API is shown correctly")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify if the userDetails API response is shown correctly", groups = { "api", "smoke",
			"regression" })
	public void userDetailsAPITest() throws IOException {

		userService.userdetails(FD).then().spec(SpecUtil.responseSpec_OK()).body("message", equalTo("Success"))
				.body(matchesJsonSchemaInClasspath("response-schema/userDetailsResponseSchema.json"));

	}

}
