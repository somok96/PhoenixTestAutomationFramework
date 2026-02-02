package com.api.test;

import static io.restassured.RestAssured.given;

import java.time.LocalDate;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.utils.FakerDataGenerator;
import com.api.utils.SpecUtil;
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.CustomerProductDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class CreateJobAPITestwithFakeData4 {

	CreateJobPayload createJobPayload;

	@BeforeMethod(description = "Creating the create job api payload")
	public void setup() {

		createJobPayload = FakerDataGenerator.generateFakeCreateJobData();

	}

	@Test(description = "Verify if the create job api is able to create inwarranty jobs", groups = { "api",
			"regression", "smoke" })
	public void createJobAPITest() {

		Response response = given().spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload)).log().all().when()
				.post("job/create").then().spec(SpecUtil.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_")).extract().response();

		int customerID = response.then().extract().body().jsonPath().getInt("data.tr_customer_id");
		int productID = response.then().extract().body().jsonPath().getInt("data.tr_customer_product_id");

		Customer customerExpectedData = createJobPayload.customer();
		CustomerAddress customerExpectedAddressData = createJobPayload.customer_address();
		CustomerProduct customerExpectedProductData = createJobPayload.customer_product();
		CustomerDBModel customerDatafromDB = CustomerDao.getCustomerInfo(customerID);
		CustomerAddressDBModel customerAddressDataFromDB = CustomerAddressDao
				.getCustomerAddressInfo(customerDatafromDB.getTr_customer_address_id());
		CustomerProductDBModel customerProductDatafromDB = CustomerProductDao.getCustomerProduct(productID);

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
