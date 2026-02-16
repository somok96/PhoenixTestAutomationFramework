package com.api.test.datadriven;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.services.JobService;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;
@Listeners(com.listeners.APITestListener.class)
public class CreateJobAPIExcelDataDrivenTest {

	private JobService jobService;

	@BeforeMethod(description = "Instantiating Job service reference")
	public void setup() {
		jobService = new JobService();
	}

	@Test(description = "Verify if the create job api is able to create inwarranty jobs", groups = { "api",
			"regression", "datadriven",
			"excel" }, dataProviderClass = com.dataprovider.DataProviderUtils.class, dataProvider = "CreateJobAPIExcelDataProvider")
	public void createJobAPITest(CreateJobPayload createJobPayload) {

		jobService.create(Role.FD, createJobPayload).then().spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));
	}

}
