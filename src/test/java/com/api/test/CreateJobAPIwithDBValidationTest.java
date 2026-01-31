package com.api.test;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
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
import com.database.dao.CustomerDao;
import com.database.model.CustomerDBModel;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIwithDBValidationTest {

	private CreateJobPayload createJobPayload;
	private Customer customer;

	@BeforeMethod(description = "Creating the create job api payload")
	public void setup() {
		customer = new Customer("Somok", "Mukherjee", "8240967632", "", "somok@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("12", "Barisha", "Biren Roy Rd", "BSS", "Behala",
				"700008", "India", "West Bengal");
		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "27700110461793",
				"27700110461793", "27700110461793", DateTimeUtil.getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(),
				Model.GALLEXY.getCode());

		Problems problem = new Problems(Problem.CHARGER_NOT_WORKING.getCode(), "battery issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problem);

		createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(),
				Platform.FRONTDESK.getCode(), WarrantyStatus.IN_WARRANTY_STATUS.getCode(), OEM.GOOGLE.getCode(),
				customer, customerAddress, customerProduct, problemList);
	}

	@Test(description = "Verify if the create job api is able to create inwarranty jobs", groups = { "api",
			"regression", "smoke" })
	public void createJobAPITest() {

		int customerId = given().spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload)).log().all().when()
				.post("job/create").then().spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_")).extract().body().jsonPath()
				.getInt("data.tr_customer_id");

		System.out.println(customerId);

		CustomerDBModel customerDatafromDB = CustomerDao.getCustomerInfo(customerId);
		System.out.println(customerDatafromDB);

		System.out.println("---------------RUNNING DB VALIDATION CHECK--------------");

		try {

			Assert.assertEquals(customer.first_name(), customerDatafromDB.getFirst_name());
			Assert.assertEquals(customer.last_name(), customerDatafromDB.getLast_name());
			Assert.assertEquals(customer.mobile_number(), customerDatafromDB.getMobile_number());
			Assert.assertEquals(customer.mobile_number_alt(), customerDatafromDB.getMobile_number_alt());
			Assert.assertEquals(customer.email_id(), customerDatafromDB.getEmail_id());
			Assert.assertEquals(customer.email_id_alt(), customerDatafromDB.getEmail_id_alt());
			System.out.println("✅ DB VALIDATION SUCCESSFUL");

		} catch (AssertionError e) {

			System.out.println("❌ DB VALIDATION FAILED : Customer data mismatch");
			throw e;

		}
	}

}
