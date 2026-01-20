package com.api.test;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Model;
import com.api.constants.OEM;
import com.api.constants.Platform;
import com.api.constants.Problem;
import com.api.constants.Product;
import com.api.constants.Role;
import com.api.constants.ServiceLocation;
import com.api.constants.WarrantyStatus;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;
import com.github.javafaker.Faker;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest2 {
	
	private final static String COUNTRY = "India";
	CreateJobPayload createJobPayload;

	@BeforeMethod(description = "Creating the create job api payload")
	public void setup() {
Faker faker = new Faker(new Locale("en-IND"));
		
		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String mobileNumber = faker.numerify("824#######");
		String altMobileNumber = faker.numerify("824#######");
		String emailId = faker.internet().emailAddress();
		String altemailId = faker.internet().emailAddress();
		
		Customer customer = new Customer(fname, lname, mobileNumber, altMobileNumber, emailId, altemailId);

	
		
		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("######");
		String state = faker.address().state();
		CustomerAddress address = new CustomerAddress(flatNumber, apartmentName, 
				streetName, landmark, area, 
				pincode, COUNTRY, state);

		
		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String serialNo = faker.numerify("###############");
		String imei1 = faker.numerify("###############");
		String imei2 = faker.numerify("###############");
		String popurl = faker.internet().url();
		
		CustomerProduct product = new CustomerProduct(dop, serialNo, imei1, imei2, popurl,1, 1);
		
		
		String remark = faker.lorem().sentence(5);
		Random random = new Random();
		int problemID = random.nextInt(28)+1;
		Problems problems = new Problems(problemID, remark);
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		createJobPayload = new CreateJobPayload(0,2,1,1,customer, address, product, problemList);
		
	}

	
	
	@Test(description = "Verify if the create job api is able to create inwarranty jobs", groups = { "api",
			"regression", "smoke" })
	public void createJobAPITest() {

		given().spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload)).log().all().when().post("job/create")
				.then().spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));
	}

}
