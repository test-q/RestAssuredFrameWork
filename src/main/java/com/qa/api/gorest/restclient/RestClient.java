package com.qa.api.gorest.restclient;

import java.util.Map;

import com.qa.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class is having all HTTP methods which will call the APIs and having generic methods
 * for getting the response and fetch the value from response.
 * 4 HTTP METHODS >> GET POST PUT DELETE
 * @author rupal
 *
 */
public class RestClient {
	//HTTP METHODS -- GET, POST, PUT, DELETE
	
	/**
	 * This is HTTP GET Method
	 * @param domainUrl
	 * @param serviceUrl
	 * @param token
	 * @param contentType
	 * @param paramMap
	 * @param log
	 * @return Response from GET Call
	 */
	public static Response doGet(String domainUrl, String serviceUrl, String token, String contentType, Map<String, String> paramMap, boolean log) {
		if(setDomainUrl(domainUrl)) {
			RequestSpecification request = createRequest(token,contentType,paramMap, log);
			return getResponse("GET",request, serviceUrl);		
		}	
		return null;
	}
	
	/**
	 * This is HTTP POST Method
	 * @param domainUrl
	 * @param serviceUrl
	 * @param token
	 * @param contentType
	 * @param paramMap
	 * @param pojoObj
	 * @param log
	 * @return Response from POST Call
	 */
	public static Response doPost(String domainUrl, String serviceUrl, String token, String contentType, Map<String, String> paramMap, Object pojoObj, boolean log) {
		if(setDomainUrl(domainUrl)) {
			RequestSpecification request = createRequest(token, contentType, paramMap, log);
			String jsonPayload = TestUtil.getSerializedJSON(pojoObj);
			request.body(jsonPayload);
			return getResponse("POST", request, serviceUrl);
		}
		return null;	
	}
	
	
	private static boolean setDomainUrl(String domainUrl) {
		if(domainUrl == null || domainUrl.isEmpty()) {
			System.out.println("Please pass the correct Domain/Base URL. It is either null or blank");
			return false;
		}
		try {
			RestAssured.baseURI = domainUrl;
			return true;
		}catch(Exception e){
			System.out.println("Problem Occuured While Assigning Domain Url to Rest Assured...");
			return false;
		}	
	}
	
	
	private static RequestSpecification createRequest(String token, String contentType, Map<String, String> paramMap, boolean log) {
		RequestSpecification request = null;
		
		if(log) {
			request = RestAssured.given().log().all();
		}else {
			request = RestAssured.given();
		}
		
		if(token != null) {
			request.header("Authorization", "Bearer " + token);
		}
	
		if(paramMap != null) {
			request.queryParams(paramMap);
		}
		
		if(contentType != null) {
			if(contentType.equalsIgnoreCase("JSON")) {
				request.contentType(ContentType.JSON);
			}else if(contentType.equalsIgnoreCase("XML")) {
				request.contentType(ContentType.XML);
			}else if(contentType.equalsIgnoreCase("TEXT")){
				request.contentType(ContentType.TEXT);
			}
		}
		return request;
	}
	
	
	private static Response getResponse(String httpMethod, RequestSpecification request, String serviceUrl) {
		Response response = null;
		
		switch(httpMethod) {
		case "GET":
			response = request.get(serviceUrl);
			break;
		case "POST":
			response = request.post(serviceUrl);
			break;
		case "PUT":
			response = request.put(serviceUrl);
			break;
		case "DELETE":
			response = request.delete(serviceUrl);
			break;
		default:
			System.out.println("Please Pass Correct HTTP Method....");
			break;
			
		}	
		return response;
		
	}

}
