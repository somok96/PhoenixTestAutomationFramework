package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DatabaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {

	private static final String CUSTOMER_DETAIL_QUERY = """

			SELECT * from tr_customer where id= ?

			""";

	public static CustomerDBModel getCustomerInfo(int customerId) {
		Connection conn;
		PreparedStatement preparedStatement;
		ResultSet resultSet = null;
		CustomerDBModel customerDBModel = null;
		try {
			conn = DatabaseManager.getConnection();
			preparedStatement = conn.prepareStatement(CUSTOMER_DETAIL_QUERY);
			preparedStatement.setInt(1, customerId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				customerDBModel = new CustomerDBModel(resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getString("mobile_number"),
						resultSet.getString("mobile_number_alt"), resultSet.getString("email_id"),
						resultSet.getString("email_id_alt"), resultSet.getInt("tr_customer_address_id"));
			}
			

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return customerDBModel;
	}

}
