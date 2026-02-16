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

@Listeners(com.listeners.APITestListener.class)
public class UserDetailsAPITest {

	UserService userService;

	@BeforeMethod(description = "Initializing the UserDetails Service")
	public void setup() {
		userService = new UserService();

	}

	@Test(description = "Verify if the userDetails API response is shown correctly", groups = { "api", "smoke",
			"regression" })
	public void userDetailsAPITest() throws IOException {

		userService.userdetails(FD).then().spec(SpecUtil.responseSpec_OK()).body("message", equalTo("Success"))
				.body(matchesJsonSchemaInClasspath("response-schema/userDetailsResponseSchema.json"));

	}

}
