package com.helper;

//import com.apttus.lightsaber.rudiments.CartPOJO;

import com.google.gson.Gson;
//import com.pojos.LoginPOJO;
import com.pojos.LoginPOJO_v2;
import com.pojos.RefreshLoginPOJO_v2;
import com.utils.RestUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Parameters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginAPI_v2 {
	//RestUtils restUtils = new RestUtils();
	Gson gson = new Gson();
	RestUtils restUtils = new RestUtils();
	LoginPOJO_v2 loginPOJO_v2 = new LoginPOJO_v2();
	RefreshLoginPOJO_v2 refreshLoginPOJO_v2 = new RefreshLoginPOJO_v2();
	Response response;
	String baseUri = restUtils.getApplicationProperty("baseURI");
	
	String baseUriPortal = restUtils.getApplicationProperty("baseURICons");
	String basePath = restUtils.getApplicationProperty("baseTokenPath");
	String baseTokenRefresh = restUtils.getApplicationProperty("baseTokenRefresh");

	public Response postConsumerPortalResponseDynamic() {

		// Creating Payload
		loginPOJO_v2.setEmail(restUtils.getApplicationProperty("userName"));
		String OrgId = restUtils.getApplicationProperty("OrgId");
		loginPOJO_v2.setOrganizationId(Integer.parseInt(OrgId));
		loginPOJO_v2.setPassword(restUtils.getApplicationProperty("OrgType"));

		// Creating Headers and Params
		Map<String, String> Headers = new HashMap<>();
		Headers.put("x-api-key", "aqZneQWpza1mCkiGv5QKA9QoGP607jyn7TU59kUe");
		Headers.put("Content-Type", "application/json");
		Map<String, String> queryparams = new HashMap<>();
		Map<String, String> pathParams = new HashMap<>();

		// Creating Endpoints
		String url = baseUri+basePath;

		try {
			response = restUtils.sendPOSTrequest(url,Headers,null,null,gson.toJson(loginPOJO_v2));
			Thread.sleep(2000);
			if (response.getStatusCode() != 200) {
				throw new Exception("Failed to login the Consumer portal, got status code as " + response.getStatusCode()
						+ "and the response body received is: " + response.getBody().asString());
			}
		}catch(Exception e) {
			System.out.print(" Error in API");
		}
		return  response;
	}

	public Response postConsumerPortalResponseRefresh(String accessToken, String refreToken) {

		// Creating Payload
		refreshLoginPOJO_v2.setEmail(restUtils.getApplicationProperty("userName"));
		String OrgId = restUtils.getApplicationProperty("OrgId");
		refreshLoginPOJO_v2.setOrganizationId(OrgId);
		refreshLoginPOJO_v2.setAccessToken(accessToken);
		refreshLoginPOJO_v2.setRefreshToken(refreToken);
		refreshLoginPOJO_v2.setInitialLogin(true);

		// Creating Headers and Params
		Map<String, String> Headers = new HashMap<>();
		Headers.put("Authorization", accessToken);
		Headers.put("Content-Type", "application/json");
		Map<String, String> queryparams = new HashMap<>();
		Map<String, String> pathParams = new HashMap<>();

		// Creating Endpoints
		String url = baseUriPortal+baseTokenRefresh;

		try {
			System.out.println(" Payload is : "+gson.toJson(refreshLoginPOJO_v2));
			System.out.println(" url is : "+url);
			response = restUtils.sendPOSTrequest(url,Headers,null,null,gson.toJson(refreshLoginPOJO_v2));
			//Thread.sleep(2000);
			
		}catch(Exception e) {
			System.out.print(" Error in API");
		}
		return  response;
	}

	public String generaterefreshAccessToken() {

		postConsumerPortalResponseDynamic();
		JsonPath jsonPath = restUtils.convertResponsetoJsonPath(response);
		String refreshtoken = jsonPath.get("Records.RefreshToken");
		return  refreshtoken;
	}

	public String generateAccessToken() {

		postConsumerPortalResponseDynamic();
		JsonPath jsonPath = restUtils.convertResponsetoJsonPath(response);
		String accessToken = jsonPath.get("Records.accessToken");
		return  accessToken;
	}

	public List<String> generateAccessRefreshToken() {

		List<String> ls = new ArrayList<>();
		postConsumerPortalResponseDynamic();
		JsonPath jsonPath = restUtils.convertResponsetoJsonPath(response);
		String accessToken = jsonPath.get("Records.accessToken");
		String refreshToken = jsonPath.get("Records.RefreshToken");
		ls.add(accessToken);
		ls.add(refreshToken);
		return  ls;
	}

//	public String portalResponseRefresh(String accessToken, String refreToken) {
//
//		postConsumerPortalResponseRefresh(accessToken,refreToken);
//		JsonPath jsonPath = restUtils.convertResponsetoJsonPath(response);
//		String token = jsonPath.get("Records.RefreshToken");
//		return  token;
//	}



}
