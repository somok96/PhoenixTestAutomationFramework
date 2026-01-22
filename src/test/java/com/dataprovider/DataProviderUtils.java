package com.dataprovider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.dataprovider.api.bean.CreateJobBean;
import com.dataprovider.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {

		return CSVReaderUtil.loadCSV("testData/loginCreds.csv", UserBean.class);
	}

	@DataProvider(name = "CreateJobAPIProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobDataProvider() {
		Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtil.loadCSV("testData/CreateJobData.csv",
				CreateJobBean.class);

		CreateJobBean tempBean;
		CreateJobPayload tempPayload;
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		while (createJobBeanIterator.hasNext()) {
			tempBean = createJobBeanIterator.next();
			tempPayload = CreateJobBeanMapper.mapper(tempBean);
			payloadList.add(tempPayload);
		}

		return payloadList.iterator();
	}

	@DataProvider(name = "CreateJobAPIFakerDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobFakeDataProvider() {

		String fakerCount = System.getProperty("fakerCount", "5");
		int fakerCountInt = Integer.parseInt(fakerCount);
		Iterator<CreateJobPayload> payloadIterator = FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
		return payloadIterator;

	}
	

	@DataProvider(name = "LoginAPIJsonDataProvider", parallel = true)
	public static Iterator<UserCredentials> loginAPIJsonDataProvider() {

		return JsonReaderUtil.loadJson("testData/loginAPITestdata.json", UserCredentials[].class);
	}


}
