package com.database.dao;

import java.util.ArrayList;
import java.util.List;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CreateJobBeanMapper;
import com.dataprovider.api.bean.CreateJobBean;

public class DAODemoRunner {

	public static void main(String[] args) {

		List<CreateJobBean> beanList = CreateJobPayloadDataDao.getCreateJobPayloadData();
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();

		for (CreateJobBean createJobBean : beanList) {
			CreateJobPayload payload = CreateJobBeanMapper.mapper(createJobBean);
			payloadList.add(payload);
		}

		System.out.println("---------------------------------------");
		for (CreateJobPayload payload : payloadList) {

			System.out.println(payload);
		}
	}

}
