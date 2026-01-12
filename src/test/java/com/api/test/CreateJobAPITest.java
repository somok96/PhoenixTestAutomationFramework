package com.api.test;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
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

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	
	@Test
	public void createJobAPITest() {
		
		
		Customer customer = new Customer("Somok","Mukherjee","8240967632","","somok@gmail.com","");
		CustomerAddress customerAddress = new CustomerAddress("12", "Barisha", "Biren Roy Rd", "BSS", "Behala", 700008, "India", "West Bengal");
		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "17700110461788", "17700110461788", "17700110461788", DateTimeUtil.getTimeWithDaysAgo(10), 
				Product.NEXUS_2.getCode(), Model.GALLEXY.getCode());
		
		Problems problem = new Problems(Problem.CHARGER_NOT_WORKING.getCode(), "battery issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problem);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode()
				,Platform.FRONTDESK.getCode(),WarrantyStatus.IN_WARRANTY_STATUS.getCode(),
				OEM.GOOGLE.getCode(),customer,customerAddress, customerProduct, problemList);

		
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
