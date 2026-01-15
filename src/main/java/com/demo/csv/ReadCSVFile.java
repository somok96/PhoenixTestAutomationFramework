package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {

	public static void main(String[] args) throws IOException, CsvException {

//		File file = new File("E:\\Automation\\myworkspace\\seleniumwebdriver\\PhoenixTestAutomationFramework\\src\\"
//								+ "main\\resources\\testData\\loginCreds.csv");
//		FileReader fileReader = new FileReader(file);
		
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/loginCreds.csv");
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(inputStreamReader);
		
		List<String[]> dataList = csvReader.readAll();
		
		for(String[] datalist : dataList) {
			
			for(String data : datalist) {
				System.out.print(data + " ");
			}
			System.out.println();
		}

	}

}
