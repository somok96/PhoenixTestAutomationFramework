package com.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DemoRunner2 {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = DatabaseManager.getConnection();
		System.out.println(connection);
	}

}
