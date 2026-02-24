package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.DetailsPayload;
import com.api.services.DashboardService;
import com.api.utils.SpecUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Job Management")
@Feature("Job Details")
public class DetailsAPITest {
	
	private DashboardService dashboardService;
	private DetailsPayload payload;
	
	@BeforeMethod(description = "Instantiating details service and creating payload")
	public void setup() {
		
		payload = new DetailsPayload("created_today");
		dashboardService = new DashboardService();
	}
	
	@Story("Job Details is shown correctly for FD")
	@Description("Verify if Details API is working correctly")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify if the details API is able to show all the JOBS created", groups = {"smoke", "sanity"})
	public void showDetailsAPITest() {
		dashboardService.details(Role.FD, payload)
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", Matchers.equalTo("Success"));
	}

}
