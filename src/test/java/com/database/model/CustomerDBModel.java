package com.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerDBModel {

	private String first_name;
	private String last_name;
	private String mobile_number;
	private String mobile_number_alt;
	private String email_id;
	private String email_id_alt;
	private int tr_customer_address_id;

}
