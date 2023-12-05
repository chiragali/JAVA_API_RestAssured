package com.helper;

//import com.apttus.lightsaber.rudiments.CartPOJO;

import com.google.gson.Gson;
import com.pojos.LoginPOJO_v2;
import com.utils.RestUtils;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ContactsAPI {
	//RestUtils restUtils = new RestUtils();
	Gson gson = new Gson();
	RestUtils restUtils = new RestUtils();
	LoginPOJO_v2 loginPOJO_v2 = new LoginPOJO_v2();
	LoginAPI_v2 loginAPI_v2 = new LoginAPI_v2();
	Response response;
	String baseUri = restUtils.getApplicationProperty("baseGAPIURI");
	String basePath = restUtils.getApplicationProperty("baseContacts");

	public Response getContactDetails() {
		// TODO Auto-generated method stub

		//basePath = basePath.replace("{customer_id}", "37");
		String url = baseUri+basePath;
		Map<String, String> Headers = new HashMap<>();
		Headers.put("X-Api-Key", "R1fiwC9QlwaP1kYCRBgtI3PAA27MAjLZ9z18BHch");
		Headers.put("X-Paging", "{\"page\": 1,\"pageSize\": 10}");


		try {
			response = restUtils.sendGETRequest(url, Headers, null, null);
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
