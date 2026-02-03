package com.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerProductDBModel {

	private int id;
	private int tr_customer_id;
	private int mst_model_id;
	private String dop;
	private String popurl;
	private String imei2;
	private String imei1;
	private String serial_number;

}
