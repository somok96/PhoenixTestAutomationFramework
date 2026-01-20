package com.api.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDemo2 {
	private final static String COUNTRY = "India";

	public static void main(String[] args) {
		Faker faker = new Faker(new Locale("en-IND"));
		
		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String mobileNumber = faker.numerify("824#######");
		String altMobileNumber = faker.numerify("824#######");
		String emailId = faker.internet().emailAddress();
		String altemailId = faker.internet().emailAddress();
		
		Customer customer = new Customer(fname, lname, mobileNumber, altMobileNumber, emailId, altemailId);
		System.out.println(customer);
	
		
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
		System.out.println(address);
		
		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String serialNo = faker.numerify("###############");
		String imei1 = faker.numerify("###############");
		String imei2 = faker.numerify("###############");
		String popurl = faker.internet().url();
		
		CustomerProduct product = new CustomerProduct(dop, serialNo, imei1, imei2, popurl,1, 1);
		System.out.println(product);
		
		String remark = faker.lorem().sentence(10);
		Random random = new Random();
		int problemID = random.nextInt(28)+1;
		Problems problems = new Problems(problemID, remark);
		
		System.out.println(problems);
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		CreateJobPayload payload = new CreateJobPayload(0,2,1,1,customer, address, product, problemList);
		System.out.println(payload);
	
	}

}
