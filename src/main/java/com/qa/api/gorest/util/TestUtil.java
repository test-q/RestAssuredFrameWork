package com.qa.api.gorest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
	
	/**
	 * This method is used to convert POJO Object to JSON String
	 * @param obj
	 * @return Return JSON String
	 */
	public static String getSerializedJSON(Object obj) {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonstring = null;
		try {
			jsonstring = mapper.writeValueAsString(obj);
			System.out.println("JSON String : " +jsonstring );
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonstring;
	}

}
