package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.api.request.model.CreateJobPayload;
import com.dataprovider.api.bean.CreateJobBean;

public class ExcelReaderUtil2 {

	public static void main(String[] args) {
		Iterator<CreateJobBean> userList = ExcelReaderUtil.loadTestData("testData/PhoenixTestData.xlsx",
				"CreateJobPayload", CreateJobBean.class);

		List<CreateJobPayload> customer = new ArrayList<CreateJobPayload>();
		while (userList.hasNext()) {
			CreateJobBean bean = userList.next();
			CreateJobPayload payload = CreateJobBeanMapper.mapper(bean);
			customer.add(payload);
		}
		
		System.out.println(customer);

	}

}
