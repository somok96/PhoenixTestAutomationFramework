package com.database.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.api.constants.Model;
import com.api.constants.OEM;
import com.api.constants.Platform;
import com.api.constants.Problem;
import com.api.constants.Product;
import com.api.constants.ServiceLocation;
import com.api.constants.WarrantyStatus;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.database.model.JobHeadDBModel;

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
		
//		CustomerProductDBModel product = CustomerProductDao.getCustomerProduct(174997);
//		System.out.println(product);
//		
//		MapJobProblemDao.getProblemDetails(175278);
		
		JobHeadDBModel jobHeadDBModel = JobHeadDao.getJobHeadInfo(175777);
		System.out.println(jobHeadDBModel);
		
		
//		Customer customer = new Customer("Somok", "Mukherjee", "8240967632", "", "somok@gmail.com", "");
//		CustomerAddress customerAddress = new CustomerAddress("12", "Barisha", "Biren Roy Rd", "BSS", "Behala",
//				"700008", "India", "West Bengal");
//		CustomerProduct customerProduct = new CustomerProduct(DateTimeUtil.getTimeWithDaysAgo(10), "27700110461000",
//				"27700110461000", "27700110461000", DateTimeUtil.getTimeWithDaysAgo(10), Product.NEXUS_2.getCode(),
//				Model.GALLEXY.getCode());
//
//		Problems problem = new Problems(Problem.CHARGER_NOT_WORKING.getCode(), "battery issue");
//		List<Problems> problemList = new ArrayList<Problems>();
//		problemList.add(problem);
//
////		CreateJobPayload createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(),
//				Platform.FRONTDESK.getCode(), WarrantyStatus.IN_WARRANTY_STATUS.getCode(), OEM.GOOGLE.getCode(),
//				customer, customerAddress, customerProduct, problemList);
		
		
		
	}

}
