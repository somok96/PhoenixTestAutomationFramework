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
import com.api.response.model.CreateJobResponseModel;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.CustomerProductDao;
import com.database.dao.JobHeadDao;
import com.database.dao.MapJobProblemDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;
import com.database.model.JobHeadDBModel;
import com.database.model.MapJobProblemModel;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPIwithDBValidationTest {

	private CreateJobPayload createJobPayload;
	private Customer customer;

	@BeforeMethod(description = "Creating the create job api payload")
	public void setup() {
		customer = new Customer("Somok", "Mukherjee", "8240967632", "", "somok@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("12", "Barisha", "Biren Roy Rd", "BSS", "Behala",
				"700008", "India", "West Bengal");
		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "27700110461009",
				"27700110461009", "27700110461009", DateTimeUtil.getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(),
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

		CreateJobResponseModel createJobResponseModel = given()
				.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload)).log().all().when().post("job/create")
				.then().spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_")).extract().as(CreateJobResponseModel.class);

		int jobID = createJobResponseModel.getData().getId();
		int customerID = createJobResponseModel.getData().getTr_customer_id();
		int productID = createJobResponseModel.getData().getTr_customer_product_id();

		Customer customerExpectedData = createJobPayload.customer();
		CustomerAddress customerExpectedAddressData = createJobPayload.customer_address();
		CustomerProduct customerExpectedProductData = createJobPayload.customer_product();
		CustomerDBModel customerDatafromDB = CustomerDao.getCustomerInfo(customerID);
		CustomerAddressDBModel customerAddressDataFromDB = CustomerAddressDao
				.getCustomerAddressInfo(customerDatafromDB.getTr_customer_address_id());
		CustomerProductDBModel customerProductDatafromDB = CustomerProductDao.getCustomerProduct(productID);
		MapJobProblemModel mapJobProblemDatafromDB = MapJobProblemDao.getProblemDetails(jobID);
		JobHeadDBModel jobHeadDBModel = JobHeadDao.getJobHeadInfo(customerID);
		
		

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

		System.out.println("-------------Show Records Fetched From Customer Address Table in Database--------");
		System.out.println("flat_number : " + customerAddressDataFromDB.getFlat_number());
		System.out.println("apartment_name : " + customerAddressDataFromDB.getApartment_name());
		System.out.println("street_name : " + customerAddressDataFromDB.getStreet_name());
		System.out.println("landmark : " + customerAddressDataFromDB.getLandmark());
		System.out.println("area : " + customerAddressDataFromDB.getArea());
		System.out.println("pincode : " + customerAddressDataFromDB.getPincode());
		System.out.println("country : " + customerAddressDataFromDB.getCountry());
		System.out.println("state : " + customerAddressDataFromDB.getState());

		System.out.println("---------------RUNNING DB VALIDATION CHECK FOR CUSTOMER ADDRESS DATA-------------");

		Assert.assertEquals(customerExpectedAddressData.flat_number(), customerAddressDataFromDB.getFlat_number());
		Assert.assertEquals(customerExpectedAddressData.apartment_name(),
				customerAddressDataFromDB.getApartment_name());
		Assert.assertEquals(customerExpectedAddressData.street_name(), customerAddressDataFromDB.getStreet_name());
		Assert.assertEquals(customerExpectedAddressData.landmark(), customerAddressDataFromDB.getLandmark());
		Assert.assertEquals(customerExpectedAddressData.area(), customerAddressDataFromDB.getArea());
		Assert.assertEquals(customerExpectedAddressData.pincode(), customerAddressDataFromDB.getPincode());
		Assert.assertEquals(customerExpectedAddressData.country(), customerAddressDataFromDB.getCountry());
		Assert.assertEquals(customerExpectedAddressData.state(), customerAddressDataFromDB.getState());
		System.out.println("✅ DB VALIDATION SUCCESSFUL");

		System.out.println("-------------Show Records Fetched From Problem Product Table in Database--------");
		System.out.println("mst_problem_id : " + mapJobProblemDatafromDB.getMst_problem_id());
		System.out.println("remarks : " + mapJobProblemDatafromDB.getRemark());

		System.out.println("---------------RUNNING DB VALIDATION CHECK FOR PRODUCT PROBLEM DATA-------------");
		Assert.assertEquals(createJobPayload.problems().get(0).id(), mapJobProblemDatafromDB.getMst_problem_id());
		Assert.assertEquals((createJobPayload.problems().get(0).remark()), mapJobProblemDatafromDB.getRemark());
		System.out.println("✅ DB VALIDATION SUCCESSFUL");
		
		
		System.out.println("-------------Show Records Fetched From Job Head Table in Database--------");
		System.out.println("mst_service_location_id : " + jobHeadDBModel.getMst_service_location_id());
		System.out.println("mst_platform_id : " + jobHeadDBModel.getMst_platform_id());
		System.out.println("mst_warrenty_status_id : " + jobHeadDBModel.getMst_warrenty_status_id());
		System.out.println("mst_oem_id : " + jobHeadDBModel.getMst_oem_id());
		
		
		
		System.out.println("---------------RUNNING DB VALIDATION CHECK FOR JOB HEAD DATA-------------");
		Assert.assertEquals(createJobPayload.mst_service_location_id(), jobHeadDBModel.getMst_service_location_id());
		Assert.assertEquals(createJobPayload.mst_platform_id(), jobHeadDBModel.getMst_platform_id());
		Assert.assertEquals(createJobPayload.mst_warrenty_status_id(), jobHeadDBModel.getMst_warrenty_status_id());
		Assert.assertEquals(createJobPayload.mst_oem_id(), jobHeadDBModel.getMst_oem_id());
		System.out.println("✅ DB VALIDATION SUCCESSFUL");
		
		System.out.println("-------------Show Records Fetched From Customer Product Table in Database--------");
		System.out.println("dop : " + customerProductDatafromDB.getDop());
		System.out.println("serial_number : " + customerProductDatafromDB.getSerial_number());
		System.out.println("imei1 : " + customerProductDatafromDB.getImei1());
		System.out.println("imei2 : " + customerProductDatafromDB.getImei2());
		System.out.println("popurl : " + customerProductDatafromDB.getPopurl());
		System.out.println("mst_model_id : " + customerProductDatafromDB.getMst_model_id());

		System.out.println("---------------RUNNING DB VALIDATION CHECK FOR CUSTOMER PRODUCT DATA-------------");
		Assert.assertEquals(customerExpectedProductData.dop(), customerProductDatafromDB.getDop());
		Assert.assertEquals(customerExpectedProductData.serial_number(), customerProductDatafromDB.getSerial_number());
		Assert.assertEquals(customerExpectedProductData.imei1(), customerProductDatafromDB.getImei1());
		Assert.assertEquals(customerExpectedProductData.imei2(), customerProductDatafromDB.getImei2());
		Assert.assertEquals(customerExpectedProductData.popurl(), customerProductDatafromDB.getPopurl());
		Assert.assertEquals(customerExpectedProductData.dop(), customerProductDatafromDB.getDop());
		Assert.assertEquals(customerExpectedProductData.mst_model_id(), customerProductDatafromDB.getMst_model_id());
	}

}
