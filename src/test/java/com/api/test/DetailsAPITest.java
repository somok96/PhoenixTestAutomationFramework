package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.DetailsPayload;
import com.api.services.DashboardService;
import com.api.utils.SpecUtil;

public class DetailsAPITest {
	
	private DashboardService dashboardService;
	private DetailsPayload payload;
	
	@BeforeMethod(description = "Instantiating details service and creating payload")
	public void setup() {
		
		payload = new DetailsPayload("created_today");
		dashboardService = new DashboardService();
	}
	
	@Test(description = "Verify if the details API is able to show all the JOBS created", groups = {"smoke", "sanity"})
	public void showDetailsAPITest() {
		dashboardService.details(Role.FD, payload)
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", Matchers.equalTo("Success"));
	}

}
