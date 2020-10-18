package com.qa.api.gorest.restclient;

import java.io.File;
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
	public static Response doGet(String domainUrl, String serviceUrl, Map<String, String> token, String contentType, Map<String, String> paramMap, boolean log) {
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
	public static Response doPost(String domainUrl, String serviceUrl, Map<String, String> token, String contentType, Map<String, String> queryParamMap, Object pojoObj, boolean log) {
		if(setDomainUrl(domainUrl)) {
			RequestSpecification request = createRequest(token, contentType, queryParamMap, log);
			addPayload(request, pojoObj);
			return getResponse("POST", request, serviceUrl);
		}
		return null;	
	}
	
	private static void addPayload(RequestSpecification request, Object pojoObj) {
		if (pojoObj instanceof Map) { 
			//The java instanceof operator is used to test whether the object is an instance of the specified type 
			//The instanceof in java is also known as type comparison operator because it compares the instance with type. 
			//It returns either true or false. If we apply the instanceof operator with any variable that has null value, it returns false.
			request.formParams((Map<String, String>) pojoObj); // here we type cast Object to Map
		} else {
			String jsonPayload = TestUtil.getSerializedJSON(pojoObj);
			request.body(jsonPayload);
		}

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
	
	
	private static RequestSpecification createRequest(Map<String, String> tokenMap, String contentType, Map<String, String> queryParamMap, boolean log) {
		RequestSpecification request = null;
		
		if(log) {
			request = RestAssured.given().log().all();
		}else {
			request = RestAssured.given();
		}
		
		if(tokenMap.size() > 0) {
			//tokenMap is map hence check size if size is not zero/null that time only execute this if statement.
			request.headers(tokenMap);
		}
	
		if(queryParamMap != null) {
			request.queryParams(queryParamMap);
		}
		
		if(contentType != null) {
			if(contentType.equalsIgnoreCase("JSON")) {
				request.contentType(ContentType.JSON);
			}else if(contentType.equalsIgnoreCase("XML")) {
				request.contentType(ContentType.XML);
			}else if(contentType.equalsIgnoreCase("TEXT")){
				request.contentType(ContentType.TEXT);
			}else if(contentType.equalsIgnoreCase("MULTIPART")){
				//There is no ContentType as "multipart" so how to add "multipart" content type.
				request.multiPart("image", new File("C:/Users/rupal/OneDrive/Pictures/Saved Pictures/1.jpg"));
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
