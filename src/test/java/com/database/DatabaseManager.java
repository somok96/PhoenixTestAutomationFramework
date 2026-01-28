package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseManager {

	private static final String DB_URL = ConfigManager.getProperty("DB_URL");
	private static final String DB_USERNAME = ConfigManager.getProperty("DB_USERNAME");
	private static final String DB_PASSWORD = ConfigManager.getProperty("DB_PASSWORD");
	private static final int MAXIMUM_POOL_SIZE = Integer.parseInt(ConfigManager.getProperty("MAXIMUM_POOL_SIZE"));
	private static final int MINIMUM_IDLE_COUNT = Integer.parseInt(ConfigManager.getProperty("MINIMUM_IDLE_COUNT"));
	private static final int CONNECTION_TIMEOUT_IN_SECS = Integer
			.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT_IN_SECS"));
	private static final int IDLE_TIMEOUT_IN_SECS = Integer.parseInt(ConfigManager.getProperty("IDLE_TIMEOUT_IN_SECS"));
	private static final int MAX_LIFE_TIME_IN_MINS = Integer
			.parseInt(ConfigManager.getProperty("MAX_LIFE_TIME_IN_MINS"));
	private static final String HIKARI_CP_POOL_NAME = ConfigManager.getProperty("MAXIMUM_POOL_SIZE");

	private static HikariConfig hikariConfig;
	private volatile static HikariDataSource hikariDataSource;

	private volatile static Connection conn;

	private DatabaseManager() {

	}

	public static void initializePool() {

		if (hikariDataSource == null) {
			synchronized (DatabaseManager.class) {
				if (hikariDataSource == null) {

					hikariConfig = new HikariConfig();
					hikariConfig.setJdbcUrl(ConfigManager.getProperty("DB_URL"));
					hikariConfig.setUsername(ConfigManager.getProperty("DB_USERNAME"));
					hikariConfig.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
					hikariConfig.setMaximumPoolSize(MAXIMUM_POOL_SIZE);
					hikariConfig.setMinimumIdle(MINIMUM_IDLE_COUNT);
					hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT_IN_SECS * 1000);
					hikariConfig.setIdleTimeout(IDLE_TIMEOUT_IN_SECS);
					hikariConfig.setMaxLifetime(MAX_LIFE_TIME_IN_MINS * 60 * 1000); // 30Mins
					hikariConfig.setPoolName(HIKARI_CP_POOL_NAME);

					hikariDataSource = new HikariDataSource(hikariConfig);

				}
			}
		}

	}

	public static Connection getConnection() throws SQLException {

		if (hikariDataSource == null) {
			initializePool();
		} else if (hikariDataSource.isClosed()) {
			throw new SQLException("HIKARICP DB IS CLOSED!");
		}

		conn = hikariDataSource.getConnection();

		return conn;
	}
}
