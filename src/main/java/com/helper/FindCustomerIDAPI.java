package com.helper;

//import com.apttus.lightsaber.rudiments.CartPOJO;

import com.google.gson.Gson;
import com.pojos.LoginPOJO_v2;
import com.utils.RestUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class FindCustomerIDAPI {
	//RestUtils restUtils = new RestUtils();
	Gson gson = new Gson();
	RestUtils restUtils = new RestUtils();
//	LoginPOJO_v2 loginPOJO_v2 = new LoginPOJO_v2();
//	LoginAPI_v2 loginAPI_v2 = new LoginAPI_v2();
	Response response;
	String baseUri = restUtils.getApplicationProperty("baseGAPIURI");
	String basePath = restUtils.getApplicationProperty("baseContacts");
	//String accessToken = loginAPI_v2.generateAccessToken();
	//FindCustomerEmailAPI findCustomerAPI = new FindCustomerEmailAPI();

	public Response getAllContacts(String mail_id) {
		// TODO Auto-generated method stub

		//basePath = basePath.replace("{customer_id}", "37");

		// Fetch the response using endpoint -- /v1_dev/apikey/customers
		String url = baseUri+basePath;
		Map<String, String> headers = new HashMap<>();
		headers.put("X-Api-Key", "R1fiwC9QlwaP1kYCRBgtI3PAA27MAjLZ9z18BHch");
		headers.put("X-Paging", "{\"page\": 1,\"pageSize\": 10}");
		headers.put("X-Filter", "[{\"field\":\"Emails.Email\",\"type\":\"LIKE\",\"value\":\""+mail_id+"\"}]");

		try {
			String startTime = java.time.OffsetDateTime.now(java.time.ZoneOffset.UTC).toString();
			response = restUtils.sendGETRequest(url, headers, null, null);
			String endTime = java.time.OffsetDateTime.now(java.time.ZoneOffset.UTC).toString();
			Thread.sleep(2000);
			// Append request, response, and UTC time to the log file
			restUtils.appendToLogExcelFile(url, headers, null, response, startTime, endTime);
			restUtils.appendToLogFile(url, headers, null, response, startTime, endTime);

		}catch(Exception e) {
			System.out.print(" Error in API");
		}
		return  response;
	}

}
