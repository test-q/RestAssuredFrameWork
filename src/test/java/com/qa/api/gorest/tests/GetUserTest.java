package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetUserTest {
	
	String domainUrl = "https://gorest.co.in";
	String serviceUrl = "/public-api/users";
	String token = "0ea226be7a379cc3f7951c9841679e1b584bcb4e3601914b1626b444726ee655";
	Map<String, String> tokenMap = new HashMap<String, String>();
	JsonPath jpath;
	
	@Test
	public void getAllUser_WithoutQueryParam_Test() {
		tokenMap.put("Authorization", "Bearer " + token);
		Response response = RestClient.doGet(domainUrl, serviceUrl, tokenMap, "JSON", null, true);
		
		RestClient.getPrettyResponsePrint(response);
		RestClient.getStatusCode(response);
		jpath = RestClient.getJsonPath(response);
		int pageLimit =  jpath.get("meta.pagination.limit");
		Assert.assertEquals(pageLimit, 20);
		
		String serverName = RestClient.getHeaderValue(response, "Server");
		Assert.assertEquals(serverName, "nginx");
		
		System.out.println(RestClient.getResponseHeaders(response));
	}
	
	@Test
	public void getUser_WithQueryParam_Test() {
		tokenMap.put("Authorization", "Bearer " + token);
		
		Map<String, String> userParam = new HashMap<String, String>();
		userParam.put("name", "Goswami Varma");
		userParam.put("gender", "Male");
		
		Response response = RestClient.doGet(domainUrl, serviceUrl, tokenMap, "JSON", userParam, true);
		JsonPath jpath = response.jsonPath();
		System.out.println("Status Code: " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
	    System.out.println(response.prettyPrint());	
	}

}
