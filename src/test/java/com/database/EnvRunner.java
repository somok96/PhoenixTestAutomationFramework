package com.database;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvRunner {

	public static void main(String[] args) {

		Dotenv dotEnv = Dotenv.load();
		String url = dotEnv.get("DB_URL");
		System.out.println(url);
		
		
		
		
		
	}

}
