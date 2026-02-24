package com.api.test;

import static com.api.constants.Role.FD;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.DashboardService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(com.listeners.APITestListener.class)
@Epic("Job Management")
@Feature("Job Count")
public class CountAPITest {

	private DashboardService dashboardService;

	@BeforeTest(description = "Initializing the Dashboard Service")
	public void setup() {
		dashboardService = new DashboardService();
	}

	@Story("Job Count details should be shown correctly")
	@Description("Verify if the Count API is giving correct response")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify if the count API response is shown correctly", groups = { "api", "smoke",
			"regression" })
	public void verifyCountAPIResponse() {

		dashboardService.count(FD).then().spec(SpecUtil.responseSpec_OK()).body("data", notNullValue())
				.body("data.size()", equalTo(3)).body("data.count", everyItem(greaterThanOrEqualTo(0)))
				.body("data.label", everyItem(not(blankOrNullString())))
				.body("data.key", containsInAnyOrder("pending_for_delivery", "pending_fst_assignment", "created_today"))
				.body(matchesJsonSchemaInClasspath("response-schema/countAPIResponseSchema-FD.json"));
	}

	@Test(description = "Verify if the count API response is giving correct status code for invalid token", groups = {
			"api", "negative", "smoke", "regression" })
	public void countAPITest_MissingAuthToken() {

		dashboardService.count().then().spec(SpecUtil.responseSpec_JSON(401));
	}

}
