package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOLD {
	
	private static Properties prop = new Properties();
	
	private ConfigManagerOLD() {
	}
	
	static {
		
		File fileRef = new File(System.getProperty("user.dir") +File.separator+ "src" + File.separator + "test" + File.separator + "resources" + File.separator + "config"+ File.separator + "config.properties");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(fileRef);
			prop.load(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public static String getProperty(String key) {
		
		//Special Class : Properties : Help me read the property file
		
		return prop.getProperty(key);
	}

}
