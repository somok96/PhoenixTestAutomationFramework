package com.database.dao;

import java.sql.SQLException;

import com.database.model.CustomerProductDBModel;

public class DemoDaoRunner {

	public static void main(String[] args) throws SQLException {
		
//		CustomerDBModel customerDBModel = CustomerDao.getCustomerInfo(174169);
//		System.out.println(customerDBModel.getTr_customer_address_id());
////		
////		Customer customer = new Customer("Florian", "Pagac", "8240967632", "", "somok@gmail.com", "");
////		Assert.assertEquals(customerDBModel.getFirst_name(), customer.first_name());
////		
//		
//		CustomerAddressDBModel address = CustomerAddressDao.getCustomerAddressInfo(customerDBModel.getTr_customer_address_id());
//		System.out.println(address);
//		
		
		CustomerProductDBModel product = CustomerProductDao.getCustomerProduct(174997);
		System.out.println(product);
		
	}

}
