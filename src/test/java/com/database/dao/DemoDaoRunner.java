package com.database.dao;

import java.sql.SQLException;

import org.testng.Assert;

import com.api.request.model.Customer;
import com.database.model.CustomerDBModel;

public class DemoDaoRunner {

	public static void main(String[] args) throws SQLException {
		
		CustomerDBModel customerDBModel = CustomerDao.getCustomerInfo(173046);
		System.out.println(customerDBModel);
		
		Customer customer = new Customer("Florian", "Pagac", "8240967632", "", "somok@gmail.com", "");
		Assert.assertEquals(customerDBModel.getFirst_name(), customer.first_name());
		
	}

}
