package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile2_MaptoPOJO {

	public static void main(String[] args) throws IOException, CsvException {


		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData/loginCreds.csv");
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(inputStreamReader);
		
		CsvToBean<UserPOJO> csvToBean = new CsvToBeanBuilder(csvReader)
										.withType(UserPOJO.class)
										.withIgnoreEmptyLine(true)
										.build();
		
		List<UserPOJO> userList = csvToBean.parse();
		System.out.print(userList.get(0).getUsername());
		System.out.print(" " + userList.get(0).getPassword());
		System.out.println();
		System.out.print(userList.get(1).getUsername());
		System.out.print(" " + userList.get(1).getPassword());
	}

}
