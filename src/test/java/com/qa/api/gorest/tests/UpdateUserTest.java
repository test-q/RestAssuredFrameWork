package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;
import com.qa.api.gorest.util.GenerateToken;
import com.qa.gorest.pojo.CreateUser;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateUserTest {
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
	public void updateSingleUserDetails_Test() {
		// To update data, I have to create data first and than update it
		userPojo = new CreateUser("priti", "priti2@gmail.com", "Female", "Active");

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

		// Update user with PUT
		updatedServiceUrl = serviceUrl + userId;
		System.out.println("Updated Service URL: " + updatedServiceUrl);
		userPojo = new CreateUser("Priti", "priti.sawant2@gmail.com", "Female", "Inactive");

		response = RestClient.doPut(domainUrl, updatedServiceUrl, goRestTokenMap, "JSON", null, userPojo, true);

		// Apply Assertion/Validation For PUT Call
		RestClient.getPrettyResponsePrint(response);
		RestClient.getResponseHeaders(response);
		RestClient.getStatusCode(response);
		RestClient.getResponseHeaderCount(response);
		headerValue = RestClient.getHeaderValue(response, "Server");
		Assert.assertEquals(headerValue, "nginx");
		jpath = RestClient.getJsonPath(response);

		System.out.println("==============USER UPDATED SUCCESSFULLY==============");

	}

	// How to update multiple users with data provider concept
	@DataProvider()
	public Object[][] createUserData() {
		createUserdata = ExcelUtil.getTestData("UpdateUser");
		return createUserdata;
	}

	@Test(dataProvider = "createUserData")
	public void updateMultipleUserDetails_Test(String name, String email, String gender, String status) {
		userPojo = new CreateUser(name, email, gender, status);
		response = RestClient.doPost(domainUrl, serviceUrl, goRestTokenMap, "JSON", null, userPojo, true);

		// Apply Assertion/Validation
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

		// Update User
		updatedServiceUrl = serviceUrl + userId;
		System.out.println("Updated Service URL Is: " + updatedServiceUrl);

		// Generate Random Email ID To Update Users
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(1000);
		String actualname = jpath.get("data.name");
		String updatedEmail = actualname + randomInt + "@gmail.com";
		System.out.println("Updated Email Id Is: " + updatedEmail);
		String updatedStatus = "Inactive";
		System.out.println("Updated Status Is: " + updatedStatus);
		userPojo = new CreateUser(name, updatedEmail, gender, updatedStatus);

		response = RestClient.doPut(domainUrl, updatedServiceUrl, goRestTokenMap, "JSON", null, userPojo, true);

		// Apply Assertion/Validation For PUT Call
		RestClient.getPrettyResponsePrint(response);
		RestClient.getResponseHeaders(response);
		RestClient.getStatusCode(response);
		RestClient.getResponseHeaderCount(response);
		headerValue = RestClient.getHeaderValue(response, "Server");
		Assert.assertEquals(headerValue, "nginx");
		jpath = RestClient.getJsonPath(response);

		System.out.println("==============USER UPDATED SUCCESSFULLY==============");

	}

}
