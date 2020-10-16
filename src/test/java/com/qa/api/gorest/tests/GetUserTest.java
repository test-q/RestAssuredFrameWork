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
	
	@Test
	public void getAllUser_WithoutQueryParam_Test() {
		Response response = RestClient.doGet(domainUrl, serviceUrl, token, "JSON", null, true);
		JsonPath jpath = response.jsonPath();
		System.out.println("Status Code: " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
	    System.out.println(response.prettyPrint());	
	    
	    int limit = jpath.get("meta.pagination.limit");
	    Assert.assertEquals(limit, 20);	    
	}
	
	@Test
	public void getUser_WithQueryParam_Test() {
		Map<String, String> userParam = new HashMap<String, String>();
		userParam.put("name", "Goswami Varma");
		userParam.put("gender", "Male");
		
		Response response = RestClient.doGet(domainUrl, serviceUrl, token, "JSON", userParam, true);
		JsonPath jpath = response.jsonPath();
		System.out.println("Status Code: " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
	    System.out.println(response.prettyPrint());	
	}

}
