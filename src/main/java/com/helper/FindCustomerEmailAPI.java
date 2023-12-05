package com.helper;

//import com.apttus.lightsaber.rudiments.CartPOJO;

import com.google.gson.Gson;
import com.pojos.LoginPOJO;
import com.pojos.LoginPOJO_v2;
import com.utils.RestUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Parameters;

import java.util.HashMap;
import java.util.Map;

public class FindCustomerEmailAPI {
	//RestUtils restUtils = new RestUtils();
	Gson gson = new Gson();
	RestUtils restUtils = new RestUtils();
	LoginPOJO_v2 loginPOJO_v2 = new LoginPOJO_v2();
	LoginAPI_v2 loginAPI_v2 = new LoginAPI_v2();
	Response response;
	String baseUri = restUtils.getApplicationProperty("baseURICons");
	String basePath = restUtils.getApplicationProperty("baseCustEmailPath");


//	public String generaterefreshAccessToken() {
//
//		JsonPath jsonPath = restUtils.convertResponsetoJsonPath(response);
//		String refreshtoken = jsonPath.get("Records.refreshToken");
//		return  refreshtoken;
//	}


//	public Response getContractList() {
//		// TODO Auto-generated method stub
//
//		basePath = basePath.replace("{customer_id}", "37");
//		String url = baseUri+basePath;
//
//		try {
//			response = restUtils.getDataWithOutBearertoken(url, accessToken);
//			Thread.sleep(2000);
//			if (response.getStatusCode() != 200) {
//				throw new Exception("Failed to login the Consumer portal and " + response.getStatusCode()
//						+ "and the response body received is: " + response.getBody().asString());
//			}
//		}catch(Exception e) {
//			System.out.print(" Error in API");
//		}
//		return  response;
//	}

//	/


	public Response getCUstomerbyEmail(String accessToken) {
		// TODO Auto-generated method stub
		//String accessToken = loginAPI_v2.generateAccessToken();

		//basePath = basePath.replace("{customer_id}", "37");
		String url = baseUri+basePath;
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", accessToken);

		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("email", restUtils.getApplicationProperty("userName"));


		try {

			String startTime = java.time.OffsetDateTime.now(java.time.ZoneOffset.UTC).toString();
			response = restUtils.sendGETRequest(url, headers, queryParams, null);
			String endTime = java.time.OffsetDateTime.now(java.time.ZoneOffset.UTC).toString();

			// Append request, response, and UTC time to the log file
			restUtils.appendToLogExcelFile(url, headers, queryParams, response, startTime, endTime);
			restUtils.appendToLogFile(url, headers, queryParams, response, startTime, endTime);

			//Thread.sleep(2000);
		}catch(Exception e) {
			System.out.print(" Error in API");
		}
		return  response;
	}

}
