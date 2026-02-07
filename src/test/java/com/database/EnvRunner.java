package com.database;

import com.api.utils.EnvUtil;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvRunner {

	public static void main(String[] args) {

		System.out.println(EnvUtil.getValue("DB_URL"));
		System.out.println(EnvUtil.getValue("DB_USERNAME"));
		System.out.println(EnvUtil.getValue("DB_PASSWORD"));
		
		
	}

}
