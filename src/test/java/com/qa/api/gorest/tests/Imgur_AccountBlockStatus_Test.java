package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.GenerateToken;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class Imgur_AccountBlockStatus_Test {
	
	Map<Object, Object> tokenMap;
	String accessToken;
	String accountUsername;
	String accountId;
	String domainUrl = "https://api.imgur.com";
	String serviceUrl = null;
	
	@BeforeMethod
	public void setUp() {
		tokenMap = GenerateToken.getAccessToken();
		accessToken = tokenMap.get("access_token").toString();
		accountUsername = tokenMap.get("account_username").toString();
		accountId = tokenMap.get("account_id").toString();	
	}
	
	@Test
	public void AccountBlockStatus_Test() {
		serviceUrl = "/account/v1/" + accountUsername + "/block";
		Map<String, String> bearerTokenMap = GenerateToken.getBearerToken();
		Response response = RestClient.doGet(domainUrl, serviceUrl, bearerTokenMap, null, null, true);
		System.out.println("Status Code: " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("Response Body: " + response.prettyPrint());
		response.then().assertThat().body("data.blocked", equalTo(false));	
	}
	
	@Test
	public void AccountImage_Test() {
		serviceUrl = "/3/account/me/images";
		Map<String, String> bearerTokenMap = GenerateToken.getBearerToken();
		Response response = RestClient.doGet(domainUrl, serviceUrl, bearerTokenMap, null, null, true);
		System.out.println("Status Code: " + response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("Response Body: " + response.prettyPrint());
		response.then().assertThat().body("success", equalTo(true));		
		
	}
	
	@Test
	public void uploadImage_Test() {
		serviceUrl = "/3/upload";
		Map<String, String> clientTokenMap = GenerateToken.getClientId();
		
		Map<String, String> bodyFormMap = new HashMap<String, String>();
		bodyFormMap.put("title", "Imgur Image T1");
		bodyFormMap.put("description", "Nice Image");
		
		Response response = RestClient.doPost(domainUrl, serviceUrl, clientTokenMap, "multipart", null, bodyFormMap, true);
		System.out.println("Status Code: " + response.getStatusCode());
		System.out.println("Body: " + response.prettyPrint());
	}
	

}
