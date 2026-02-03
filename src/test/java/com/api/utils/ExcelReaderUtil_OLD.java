package com.api.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtil_OLD {

	public static void main(String[] args) throws IOException {

		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/PhoenixTestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		XSSFSheet sheet = workbook.getSheet("LoginTestData");
		XSSFRow myRow;
		Cell myCell;

		int lastRowNum = sheet.getLastRowNum();

		XSSFRow rowHeader = sheet.getRow(0);
		int lastColNum = rowHeader.getLastCellNum() - 1;

		for (int rowIndex = 0; rowIndex <= lastRowNum; rowIndex++) {

			for (int colIndex = 0; colIndex <= lastColNum; colIndex++) {

				myRow = sheet.getRow(rowIndex);
				myCell = myRow.getCell(colIndex);

				System.out.print(myCell + " ");

			}
			
			System.out.println("");

		}

	}

}
