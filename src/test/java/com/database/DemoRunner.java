package com.database;

import java.sql.SQLException;

public class DemoRunner {

	public static void main(String[] args) throws SQLException {
		
		long startTime = System.currentTimeMillis();

		DatabaseManager.createConnection();
		DatabaseManager.createConnection();
		DatabaseManager.createConnection();
		DatabaseManager.createConnection();

		long endTime = System.currentTimeMillis();
		
		System.out.println(endTime - startTime + "ms");
	}

}
