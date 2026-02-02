package com.database.model;

public class DemoRunner {
	
	public static void main(String[] args) {
		
//		CustomerDBModel customer = new CustomerDBModel("Somok", "Mukherjee", "8240967643", "", "abc@yopmail.com", "abc@yopmail.com");
//		System.out.println(customer);
//		
//		CustomerAddressDBModel address = new CustomerAddressDBModel("67", "MukherjeeHouse", "BirenRoyRoad",
//				"DominoesPizza", "Barisha", "700008", "India", "West Bengal");
//		System.out.println(address);
		
		
		CustomerProductDBModel product = new CustomerProductDBModel(1,2,3, "2025-10-01", "2025-10-01T18:30:00.000Z", "13033535175409", "13033535175409", "13033535175409");
		System.out.println(product);
	
	
	}
	
	
	

}
