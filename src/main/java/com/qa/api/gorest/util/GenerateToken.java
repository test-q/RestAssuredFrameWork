package com.qa.api.gorest.util;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GenerateToken {
	private static String domainUrl = "https://api.imgur.com";
	private static String serviceUrl = "/oauth2/token";
	
	public static Map<Object, Object> getAccessToken() {
		Map<String, String> formParamMap = new HashMap<String, String>();
		formParamMap.put("refresh_token", "78f43cf2b1e2de3b72835c5ca1c132109ffc19f1");
		formParamMap.put("client_id", "ca68daf2029d573");
		formParamMap.put("client_secret", "b722d176dd01b538513b844a0839dca8726cbe79");
		formParamMap.put("grant_type", "refresh_token");
		
		RequestSpecification request = given().baseUri(domainUrl).formParams(formParamMap);
		Response responce = request.when().post(serviceUrl);
		
		JsonPath jpath = responce.jsonPath();
		System.out.println(jpath.getMap(""));	
		
		return jpath.getMap("");
	}


}
