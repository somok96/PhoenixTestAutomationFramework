package com.api.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtil {

	private static Dotenv dotEnv;

	private EnvUtil() {

	}

	static {

		dotEnv = Dotenv.load();

	}

	public static String getValue(String varName) {
		return dotEnv.get(varName);
	}

}
