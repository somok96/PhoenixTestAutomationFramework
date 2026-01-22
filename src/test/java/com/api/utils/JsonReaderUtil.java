package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.api.request.model.UserCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtil {
	
	private JsonReaderUtil() {}

	public static <T> Iterator<T> loadJson(String fileName, Class<T[]> clazz) {

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

		ObjectMapper objectmapper = new ObjectMapper();
		T[] classArray;
		List<T> list = null;
		
		try {
			classArray = objectmapper.readValue(is, clazz);
			list = Arrays.asList(classArray);
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return list.iterator();
	}

}
