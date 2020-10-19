package com.qa.api.gorest.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
	
//	public static Object getDeserializPojo(String filePath, Object obj) {
//		Object vObj = null;
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			vObj = mapper.readValue(new File(filePath), obj.getClass());
//		} catch (JsonParseException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(vObj);
//		return vObj;
//	}

}
