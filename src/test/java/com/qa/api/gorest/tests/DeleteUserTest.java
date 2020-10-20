package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;
import com.qa.gorest.pojo.CreateUser;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeleteUserTest {
	Map<String, String> goRestTokenMap = new HashMap<String, String>();
	String domainUrl = "https://gorest.co.in";
	String serviceUrl = "/public-api/users/";
	String token = "0ea226be7a379cc3f7951c9841679e1b584bcb4e3601914b1626b444726ee655";
	Response response;
	JsonPath jpath;
	String headerValue;
	CreateUser userPojo;
	Object[][] createUserdata;
	Object[][] updateUserdata;
	String updatedServiceUrl;
	
	@BeforeMethod
	public void setUp() {
		// Set Token
		goRestTokenMap.put("Authorization", "Bearer " + token);
	}

	@Test
	public void deleteSingleUserDetails_Test() {
		// To delete data, I have to create data first and than delete it
		userPojo = new CreateUser("Nayan", "nayan17@gmail.com", "Female", "Inactive");

		response = RestClient.doPost(domainUrl, serviceUrl, goRestTokenMap, "JSON", null, userPojo, true);

		// Apply Assertion/Validation For POST Call
		RestClient.getPrettyResponsePrint(response);
		RestClient.getResponseHeaders(response);
		RestClient.getStatusCode(response);
		RestClient.getResponseHeaderCount(response);
		headerValue = RestClient.getHeaderValue(response, "Server");
		Assert.assertEquals(headerValue, "nginx");
		jpath = RestClient.getJsonPath(response);
		int userId = jpath.get("data.id");
		System.out.println("User Id: " + userId);

		System.out.println("==============USER CREATED SUCCESSFULLY==============");

		// Delete user with DELETE CALL
		updatedServiceUrl = serviceUrl + userId;
		System.out.println("Updated Service URL: " + updatedServiceUrl);
		
		response = RestClient.doDelete(domainUrl, updatedServiceUrl, goRestTokenMap, null, null, true);

		// Apply Assertion/Validation For DELETE Call
		RestClient.getPrettyResponsePrint(response);
		RestClient.getResponseHeaders(response);
		RestClient.getStatusCode(response);
		RestClient.getResponseHeaderCount(response);
		headerValue = RestClient.getHeaderValue(response, "Server");
		Assert.assertEquals(headerValue, "nginx");
		jpath = RestClient.getJsonPath(response);

		System.out.println("==============USER DELETED SUCCESSFULLY==============");

	}
	
	
	@DataProvider
	public Object[][] getUserData() {
		Object[][] userData = ExcelUtil.getTestData("DeleteUser");
		return userData;
	}
	
	@Test(dataProvider = "getUserData")
	public void deleteUser_WithDataProvider_Test(String name, String email, String gender, String status) {
		
		
		CreateUser userPojo_Obj = new CreateUser(name, email, gender, status);
		Response response = RestClient.doPost(domainUrl, serviceUrl, goRestTokenMap, "JSON", null, userPojo_Obj, true);
		
		// Apply Assertion/Validation For POST Call
		RestClient.getPrettyResponsePrint(response);
		RestClient.getResponseHeaders(response);
		RestClient.getStatusCode(response);
		RestClient.getResponseHeaderCount(response);
		headerValue = RestClient.getHeaderValue(response, "Server");
		Assert.assertEquals(headerValue, "nginx");
		jpath = RestClient.getJsonPath(response);
		int userId = jpath.get("data.id");
		System.out.println("User Id: " + userId);

		System.out.println("==============USER CREATED SUCCESSFULLY==============");
		
		// Delete user with DELETE CALL
		updatedServiceUrl = serviceUrl + userId;
		System.out.println("Updated Service URL: " + updatedServiceUrl);
		
		response = RestClient.doDelete(domainUrl, updatedServiceUrl, goRestTokenMap, null, null, true);

		// Apply Assertion/Validation For DELETE Call
		RestClient.getPrettyResponsePrint(response);
		RestClient.getResponseHeaders(response);
		RestClient.getStatusCode(response);
		RestClient.getResponseHeaderCount(response);
		headerValue = RestClient.getHeaderValue(response, "Server");
		Assert.assertEquals(headerValue, "nginx");
		jpath = RestClient.getJsonPath(response);

		System.out.println("==============USER DELETED SUCCESSFULLY==============");
	}
	
	

}
