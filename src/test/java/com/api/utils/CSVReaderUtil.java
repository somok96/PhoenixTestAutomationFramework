package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dataprovider.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {

	private static final Logger LOGGER = LogManager.getLogger(CSVReaderUtil.class);

	private CSVReaderUtil() {

	}

	public static <T> Iterator<T> loadCSV(String pathofCSVFile, Class<T> bean) {

		LOGGER.info("Loading the CSV file from the path {}", pathofCSVFile);
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathofCSVFile);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(inputStreamReader);

		LOGGER.info("Converting the CSV to the Bean Class {}", bean);
		CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader).withType(bean).withIgnoreEmptyLine(true).build();

		List<T> list = csvToBean.parse();
		return list.iterator();
	}

}
