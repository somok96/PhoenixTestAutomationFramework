package com.api.test.datadriven;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIDataDrivenTest {


	@Test(description = "Verify if the create job api is able to create inwarranty jobs", groups = { "api",
			"regression", "datadriven", "csv" }, dataProviderClass = com.dataprovider.DataProviderUtils.class,
			dataProvider = "CreateJobAPIProvider")
	public void createJobAPITest(CreateJobPayload createJobPayload) {

		given().spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload)).log().all().when().post("job/create")
				.then().spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));
	}

}
