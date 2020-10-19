package com.qa.api.gorest.restclient;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.qa.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

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
	public static Response doGet(String domainUrl, String serviceUrl, Map<String, String> token, String contentType, Map<String, String> queryParamMap, boolean log) {
		if(setDomainUrl(domainUrl)) {
			RequestSpecification request = createRequest(token,contentType,queryParamMap, log);
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
	
	public static Response doPut(String domainUrl, String serviceUrl, Map<String, String> token, String contentType, Map<String, String> queryParamMap, Object pojoObj, boolean log) {
		if(setDomainUrl(domainUrl)) {
			RequestSpecification request = createRequest(token, contentType, queryParamMap, log);
			addPayload(request, pojoObj);
			return getResponse("PUT", request, serviceUrl);
		}
		return null;
	}
	
	public static Response doDelete(String domainUrl, String serviceUrl, Map<String, String> token, String contentType, Map<String, String> queryParamMap, boolean log) {
		if(setDomainUrl(domainUrl)) {
			RequestSpecification request = createRequest(token, contentType, queryParamMap, log);
			return getResponse("DELETE", request, serviceUrl);
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
	
	/**
	 * This method Validate response status code
	 * @param response
	 */
	public static void getStatusCode(Response response) {
		if(response != null) {
			int code = response.getStatusCode();
			System.out.println("Response Status Code Is: " +code);
			Assert.assertEquals(200, code);
		}else {
			System.out.println("Something wrong in Response.");
		}
	}
	
	/**
	 * This method return json path
	 * @param response
	 * @return JsonPath
	 */
	public static JsonPath getJsonPath(Response response) {
		if(response != null) {
			JsonPath jPath = response.jsonPath();
			return jPath;
		}else {
			System.out.println("Something wrong in Response");
			return null;
		}	
	}
	
	/**
	 * This method return Header Name
	 * @param response
	 * @param headerName
	 * @return Header Name in string format
	 */
	public static String getHeaderValue(Response response, String headerName) {
		if(response != null) {
			return response.getHeader(headerName);
		}else {
			System.out.println("Something wrong in Response");
			return null;
		}
	}
	
	/**
	 * 
	 * @param response
	 * @return Header size 
	 */
	public static int getResponseHeaderCount(Response response) {
		Headers headers = response.getHeaders();
		System.out.println("Response Header Count Is : " +headers.size());
		return headers.size();
	}

	/**
	 * 
	 * @param response
	 * @return
	 */
	public static List<Header> getResponseHeaders(Response response) {
		Headers headers = response.getHeaders();
		List<Header> headerList = headers.asList();
		System.out.println("==============Response Header List==============");
		System.out.println(headerList);
		System.out.println("===============================================");
		return headerList;
	}

	/**
	 * This method display Response in Pretty Print format
	 * @param response
	 */
	public static void getPrettyResponsePrint(Response response) {
		System.out.println("==============Response In Pretty Format============");
		response.prettyPrint();
	}
	

}
