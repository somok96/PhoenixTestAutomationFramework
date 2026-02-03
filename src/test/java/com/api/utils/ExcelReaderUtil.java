package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UserCredentials;

public class ExcelReaderUtil {

	private ExcelReaderUtil() {

	}

	public static Iterator<UserCredentials> loadTestData(String path){

		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(path);
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet("LoginTestData");

		XSSFRow headerRows = sheet.getRow(0);
		int userNameIndex = -1;
		int passwordIndex = -1;

		for (Cell cell : headerRows) {
			if (cell.getStringCellValue().trim().equalsIgnoreCase("username")) {
				userNameIndex = cell.getColumnIndex();
			}

			if (cell.getStringCellValue().trim().equalsIgnoreCase("password")) {
				passwordIndex = cell.getColumnIndex();
			}
		}

		int lastRowIndex = sheet.getLastRowNum();
		UserCredentials userCredentials = null;
		XSSFRow rowData;
		ArrayList<UserCredentials> userList = new ArrayList<UserCredentials>();

		for (int rowIndex = 1; rowIndex <= lastRowIndex; rowIndex++) {

			rowData = sheet.getRow(rowIndex);
			userCredentials = new UserCredentials(rowData.getCell(userNameIndex).toString(),
					rowData.getCell(passwordIndex).toString());
			userList.add(userCredentials);
		}

		return userList.iterator();
	}

}
