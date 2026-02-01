package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DatabaseManager;
import com.database.model.CustomerAddressDBModel;

public class CustomerAddressDao {

	private static final String CUSTOMER_ADDRESS_QUERY = """
						SELECT id,
						flat_number,
						apartment_name,
						street_name,
						landmark,
						area,
						pincode,
						country,
						state from tr_customer_address where id= ?;
						""";

	public static CustomerAddressDBModel getCustomerAddressInfo(int customerAddressId) throws SQLException {
		Connection conn = DatabaseManager.getConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(CUSTOMER_ADDRESS_QUERY);
		preparedStatement.setInt(1, customerAddressId);
		ResultSet resultSet = preparedStatement.executeQuery();
		CustomerAddressDBModel customerAddressDBModel = null;

		while (resultSet.next()) {
			customerAddressDBModel = new CustomerAddressDBModel(resultSet.getInt("id"),
					resultSet.getString("flat_number"), resultSet.getString("apartment_name"),
					resultSet.getString("street_name"), resultSet.getString("landmark"), resultSet.getString("area"),
					resultSet.getString("pincode"), resultSet.getString("country"), resultSet.getString("state"));
		}

		return customerAddressDBModel;
	}

}
