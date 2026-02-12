package com.dataprovider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredentials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtil;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.database.dao.CreateJobPayloadDataDao;
import com.dataprovider.api.bean.CreateJobBean;
import com.dataprovider.api.bean.UserBean;

public class DataProviderUtils {

	private static final Logger LOGGER = LogManager.getLogger(DataProviderUtils.class);

	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		LOGGER.info("Loading data from the CSV file testData/loginCreds.csv");
		return CSVReaderUtil.loadCSV("testData/loginCreds.csv", UserBean.class);
	}

	@DataProvider(name = "CreateJobAPIProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobDataProvider() {
		LOGGER.info("Loading data from the CSV file testData/CreateJobData.csv");
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
		String fakerCount = System.getProperty("fakerCount", "2");
		int fakerCountInt = Integer.parseInt(fakerCount);

		LOGGER.info("Generating fake Create job data with the faker count {}", fakerCountInt);
		Iterator<CreateJobPayload> payloadIterator = FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
		return payloadIterator;

	}

	@DataProvider(name = "LoginAPIJsonDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIJsonDataProvider() {
		LOGGER.info("Loading data from the JSON file testData/loginAPITestdata.json");
		return JsonReaderUtil.loadJson("testData/loginAPITestdata.json", UserBean[].class);
	}

	@DataProvider(name = "CreateJobAPIJsonDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIJsonDataProvider() {
		LOGGER.info("Loading data from the JSON file testData/CreateJobAPIData.json");
		return JsonReaderUtil.loadJson("testData/CreateJobAPIData.json", CreateJobPayload[].class);
	}

	@DataProvider(name = "LoginAPIExcelDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIExcelDataProvider() {
		LOGGER.info("Loading data from the Excel file testData/PhoenixTestData.xlsx and Sheet is LoginTestData");
		return ExcelReaderUtil.loadTestData("testData/PhoenixTestData.xlsx", "LoginTestData", UserBean.class);
	}

	@DataProvider(name = "CreateJobAPIDBDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobAPIDBDataProvider() {
		LOGGER.info("Loading data from Database for CreateJobPayload");
		List<CreateJobBean> beanList = CreateJobPayloadDataDao.getCreateJobPayloadData();
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();

		for (CreateJobBean createJobBean : beanList) {
			CreateJobPayload payload = CreateJobBeanMapper.mapper(createJobBean);
			payloadList.add(payload);
		}

		return payloadList.iterator();
	}

	@DataProvider(name = "CreateJobAPIExcelDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobExcelDataProvider() {
		LOGGER.info("Loading data from the Excel file testData/PhoenixTestData.xlsx and Sheet is CreateJobPayload");
		Iterator<CreateJobBean> createJobBeanIterator = ExcelReaderUtil.loadTestData("testData/PhoenixTestData.xlsx",
				"CreateJobPayload", CreateJobBean.class);

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

}
