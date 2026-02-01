package com.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerAddressDBModel {
	
	private int id;
	private String flat_number;
	private String apartment_name;
	private String street_name;
	private String landmark;
	private String area;
	private String pincode;
	private String country;
	private String state;
	

}
