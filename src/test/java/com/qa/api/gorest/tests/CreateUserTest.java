package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;
import com.qa.gorest.pojo.CreateUser;

import io.restassured.response.Response;

public class CreateUserTest {
	String domainUrl = "https://gorest.co.in";
	String serviceUrl = "/public-api/users";
	String token = "0ea226be7a379cc3f7951c9841679e1b584bcb4e3601914b1626b444726ee655";
	
	@Test
	public void createSingleUser_Test() {
		CreateUser userPojo_Obj = new CreateUser("Vinita", "vini12@gmail.com", "Female", "Inactive");
		Response response = RestClient.doPost(domainUrl, serviceUrl, token, "JSON", null, userPojo_Obj, true);
		
		System.out.println("Status Code: " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println(response.prettyPrint());		    
	}
	
	@DataProvider
	public Object[][] getUserData() {
		Object[][] userData = ExcelUtil.getTestData("CreateUser");
		return userData;
	}
	
	@Test(dataProvider = "getUserData")
	public void createUser_WithDataProvider_Test(String name, String email, String gender, String status) {
		CreateUser userPojo_Obj = new CreateUser(name, email, gender, status);
		Response response = RestClient.doPost(domainUrl, serviceUrl, token, "JSON", null, userPojo_Obj, true);
		
		System.out.println("Status Code: " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println(response.prettyPrint());		
		System.out.println("***************************************************");
	}
	

}
