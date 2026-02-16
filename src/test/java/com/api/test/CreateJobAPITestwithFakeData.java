package com.api.test;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.services.JobService;
import com.api.utils.FakerDataGenerator;
import com.api.utils.SpecUtil;
import com.database.dao.CustomerDao;
import com.database.model.CustomerDBModel;

import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(com.listeners.APITestListener.class)
public class CreateJobAPITestwithFakeData {

	private CreateJobPayload createJobPayload;
	private JobService jobService;

	@BeforeMethod(description = "Creating the create job api payload")
	public void setup() {

		createJobPayload = FakerDataGenerator.generateFakeCreateJobData();
		jobService = new JobService();

	}

	@Test(description = "Verify if the create job api is able to create inwarranty jobs with database validation for customer personal information", groups = {
			"api", "regression", "smoke" })
	public void createJobAPITest() {

		int customerID = jobService.create(Role.FD, createJobPayload).then().spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_")).extract().body().jsonPath()
				.getInt("data.tr_customer_id");

		Customer customerExpectedData = createJobPayload.customer();
		CustomerDBModel customerDatafromDB = CustomerDao.getCustomerInfo(customerID);

		System.out.println("-------------Show Records Fetched From Customer Table in Database--------");
		System.out.println("first_name : " + customerDatafromDB.getFirst_name());
		System.out.println("last_name : " + customerDatafromDB.getLast_name());
		System.out.println("mobile_number : " + customerDatafromDB.getMobile_number());
		System.out.println("mobile_number_alt : " + customerDatafromDB.getMobile_number_alt());
		System.out.println("email_id : " + customerDatafromDB.getEmail_id());
		System.out.println("email_id_alt : " + customerDatafromDB.getEmail_id_alt());

		System.out.println("---------------RUNNING DB VALIDATION CHECK FOR CUSTOMER PERSONAL DATA-------------");

		Assert.assertEquals(customerExpectedData.first_name(), customerDatafromDB.getFirst_name());
		Assert.assertEquals(customerExpectedData.last_name(), customerDatafromDB.getLast_name());
		Assert.assertEquals(customerExpectedData.mobile_number(), customerDatafromDB.getMobile_number());
		Assert.assertEquals(customerExpectedData.mobile_number_alt(), customerDatafromDB.getMobile_number_alt());
		Assert.assertEquals(customerExpectedData.email_id(), customerDatafromDB.getEmail_id());
		Assert.assertEquals(customerExpectedData.email_id_alt(), customerDatafromDB.getEmail_id_alt());
		System.out.println("✅ DB VALIDATION SUCCESSFUL");

	}

}
