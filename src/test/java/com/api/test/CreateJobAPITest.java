package com.api.test;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.pojos.CreateJobPayload;
import com.api.pojos.Customer;
import com.api.pojos.CustomerAddress;
import com.api.pojos.CustomerProduct;
import com.api.pojos.Problems;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	
	@Test
	public void createJobAPITest() {
		
		
		Customer customer = new Customer("Somok","Mukherjee","8240967632","","somok@gmail.com","");
		CustomerAddress customerAddress = new CustomerAddress("12", "Barisha", "Biren Roy Rd", "BSS", "Behala", 700008, "India", "West Bengal");
		CustomerProduct customerProduct = new CustomerProduct("2025-10-01T18:30:00.000Z", "17800110461748", "17800110461748", "17800110461748", "2025-10-01T18:30:00.000Z", 3, 3 );
		Problems problem = new Problems(1, "battery issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problem);
		CreateJobPayload createJobPayload = new CreateJobPayload(0,2,1,2,customer,customerAddress, customerProduct, problemList);

		
		given()
			.spec(SpecUtil.requestSpecWithAuth(Role.FD,createJobPayload))
			.log().all()
			.when()
			.post("job/create")
			.then()
			.spec(SpecUtil.responseSpec_OK())
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
			.body("message",Matchers.equalTo("Job created successfully. "))
			.body("data.mst_service_location_id", Matchers.equalTo(1))
			.body("data.job_number", Matchers.startsWith("JOB_"));
	}
	
	
}
