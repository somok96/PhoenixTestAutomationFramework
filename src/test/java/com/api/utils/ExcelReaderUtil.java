package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

import io.qameta.allure.Step;

public class ExcelReaderUtil {
	
	private static final Logger LOGGER = LogManager.getLogger(ExcelReaderUtil.class);

	private ExcelReaderUtil() {

	}

	@Step("Loading the test data from the Excel File")
	public static <T> Iterator<T> loadTestData(String xlsxFile, String sheetName, Class<T> clazz){
		
		LOGGER.info("Reading the test data from .xlsx file {} and the sheet name is {}", xlsxFile,sheetName);
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(xlsxFile);
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			LOGGER.error("Cannot read the excel {}", xlsxFile, e);
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		LOGGER.info("Converting the XSSFSheet to POJO Class of type {}", sheetName, clazz);
		List<T> dataList = Poiji.fromExcel(sheet, clazz);	
		
		return dataList.iterator();
	}

}
