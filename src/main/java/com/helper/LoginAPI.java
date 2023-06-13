package com.helper;

//import com.apttus.lightsaber.rudiments.CartPOJO;

import com.google.gson.Gson;
import com.pojos.LoginPOJO;
import com.utils.RestUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Parameters;

public class LoginAPI {
	//RestUtils restUtils = new RestUtils();
	Gson gson = new Gson();
	RestUtils restUtils = new RestUtils();
	LoginPOJO loginPOJO = new LoginPOJO();
	Response response;
	String baseUri = restUtils.getApplicationProperty("baseURI");
	String basePath = restUtils.getApplicationProperty("basePath");
	
	//@Parameters({ "environment"})
	public Response postConsumerPortalResponseWithValidInput() {

		loginPOJO.setEmail(restUtils.getApplicationProperty("userName"));
		loginPOJO.setOrganizationId(restUtils.getApplicationProperty("OrgId"));
		loginPOJO.setPassword(restUtils.getApplicationProperty("password"));

		String url = baseUri+basePath;
		
		try {
			response = restUtils.postWithoutAppUrl(url, gson.toJson(loginPOJO));
			Thread.sleep(2000);
			if (response.getStatusCode() != 200) {
				throw new Exception("Failed to login the Consumer portal and " + response.getStatusCode()
						+ "and the response body received is: " + response.getBody().asString());
			}
		}catch(Exception e) {
			System.out.print(" Error in API");
		}
		return  response;
	}

	public String generaterefreshAccessToken() {

		JsonPath jsonPath = restUtils.convertResponsetoJsonPath(response);
		String refreshtoken = jsonPath.get("Records.refreshToken");
		return  refreshtoken;
	}
	
	public String generateAccessToken() {
		
		postConsumerPortalResponseWithValidInput();
		JsonPath jsonPath = restUtils.convertResponsetoJsonPath(response);
		String accessToken = jsonPath.get("Records.accessToken");
		return  accessToken;
	}

	@Parameters({ "environment"})
	public Response postConsumerPortalResponseWithInvalidOrgId() {

		loginPOJO.setEmail(restUtils.getApplicationProperty("userName"));
		loginPOJO.setOrganizationId("2057");
		loginPOJO.setPassword(restUtils.getApplicationProperty("password"));
		
		String url = baseUri+basePath;
		
		try {
			response = restUtils.postWithoutAppUrl(url, gson.toJson(loginPOJO));
			Thread.sleep(2000);
			if (response.getStatusCode() != 200) {
				throw new Exception("Failed to login the Consumer portal and " + response.getStatusCode()
						+ "and the response body received is: " + response.getBody().asString());
			}
		}catch(Exception e) {
			System.out.print(" Error in API");
		}
		return  response;
	}

	public Response loginWithInvalidEmail() {
		// TODO Auto-generated method stub
		loginPOJO.setEmail("testconsumerforpor4233@gmail.com");
		loginPOJO.setOrganizationId(restUtils.getApplicationProperty("OrgId"));
		loginPOJO.setPassword(restUtils.getApplicationProperty("password"));

		String url = baseUri+basePath;
		
		try {
			response = restUtils.postWithoutAppUrl(url, gson.toJson(loginPOJO));
			Thread.sleep(2000);
			if (response.getStatusCode() != 200) {
				throw new Exception("Failed to login the Consumer portal and " + response.getStatusCode()
						+ "and the response body received is: " + response.getBody().asString());
			}
		}catch(Exception e) {
			System.out.print(" Error in API");
		}
		return  response;
	}

	public Response loginWithInvalidPassword() {
		// TODO Auto-generated method stub
		loginPOJO.setEmail("testconsumerforpor4233@gmail.com");
		loginPOJO.setOrganizationId(restUtils.getApplicationProperty("OrgId"));
		loginPOJO.setPassword("U2FsdGVkX1");

		String url = baseUri+basePath;
		
		try {
			response = restUtils.postWithoutAppUrl(url, gson.toJson(loginPOJO));
			Thread.sleep(2000);
			if (response.getStatusCode() != 200) {
				throw new Exception("Failed to login the Consumer portal and " + response.getStatusCode()
						+ "and the response body received is: " + response.getBody().asString());
			}
		}catch(Exception e) {
			System.out.print(" Error in API");
		}
		return  response;
	}

}
