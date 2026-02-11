package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDataGenerator {

	private static Faker faker = new Faker(new Locale("en-IND"));
	private final static String COUNTRY = "India";
	private final static Random RANDOM = new Random();
	private final static int MST_SERVICE_LOCATION_ID = 1;
	private final static int MST_PLATFORM_ID = 2;
	private final static int MST_WARRENTY_STATUS_ID = 1;
	private final static int MST_OEM_ID = 2;
	private final static int PRODUCT_ID = 3;
	private final static int MST_MODEL_ID = 3;
	private final static int[] problemIdArr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 19, 20, 22, 24, 26,
			27, 28, 29 };
	private static final Logger LOGGER = LogManager.getLogger(FakerDataGenerator.class);

	private FakerDataGenerator() {

	}

	public static CreateJobPayload generateFakeCreateJobData() {
		
		LOGGER.info("Generating the fake payload for CreateJob");
		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddressData();
		CustomerProduct customerProduct = generateFakeCustomerProduct();
		List<Problems> problemsList = generateFakeProblemsList();

		CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
				MST_WARRENTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);

		return payload;

	}

	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
		
		LOGGER.info("Generating the fake {} payloads for CreateJob", count);
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		for (int i = 1; i <= count; i++) {
			Customer customer = generateFakeCustomerData();
			CustomerAddress customerAddress = generateFakeCustomerAddressData();
			CustomerProduct customerProduct = generateFakeCustomerProduct();
			List<Problems> problemsList = generateFakeProblemsList();

			CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
					MST_WARRENTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemsList);

			payloadList.add(payload);
		}

		return payloadList.iterator();

	}

	private static List<Problems> generateFakeProblemsList() {

		int count = RANDOM.nextInt(3) + 1;
		String remark;
		int index;
		Problems problems;
		List<Problems> problemList = new ArrayList<Problems>();;

		for (int i = 1; i <= count; i++) {
			index = RANDOM.nextInt(problemIdArr.length);
			remark = faker.lorem().sentence(5);
			problems = new Problems(problemIdArr[index], remark);
			problemList.add(problems);
		}
		return problemList;
	}

	private static CustomerProduct generateFakeCustomerProduct() {

		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String serialNo = faker.numerify("###############");
		String imei1 = faker.numerify("###############");
		String imei2 = faker.numerify("###############");
		String popurl = faker.internet().url();

		CustomerProduct product = new CustomerProduct(dop, serialNo, imei1, imei2, popurl, PRODUCT_ID, MST_MODEL_ID);

		return product;
	}

	private static CustomerAddress generateFakeCustomerAddressData() {
		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("######");
		String state = faker.address().state();
		CustomerAddress address = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area, pincode,
				COUNTRY, state);
		return address;
	}

	private static Customer generateFakeCustomerData() {

		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String mobileNumber = faker.numerify("824#######");
		String altMobileNumber = faker.numerify("824#######");
		String emailId = faker.internet().emailAddress();
		String altemailId = faker.internet().emailAddress();

		Customer customer = new Customer(fname, lname, mobileNumber, altMobileNumber, emailId, altemailId);

		return customer;
	}
}
