package com.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Step;

public class EnvUtil {

	private static final Logger LOGGER = LogManager.getLogger(EnvUtil.class);
	private static Dotenv dotEnv;

	private EnvUtil() {

	}

	static {

		LOGGER.info("Loading the .env file.....");
		dotEnv = Dotenv.load();

	}

	@Step("Retrieving the secret from .env file")
	public static String getValue(String varName) {
		LOGGER.info("Reading the value of {} from .env", varName);
		return dotEnv.get(varName);
	}

}
