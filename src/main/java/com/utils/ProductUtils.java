package com.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import com.apttus.lightsaber.rudiments.CartPOJO;
import com.google.gson.Gson;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;

public class ProductUtils {
	//RestUtils restUtils = new RestUtils();
	Gson gson = new Gson();
	RestUtils restUtils = new RestUtils();
	Response response;



//	public String generaterefreshAccessToken(String url, String payload) {
//
//		try {
//			response = restUtils.postWithoutAppUrl(url, payload);
//			Thread.sleep(2000);
//			if (response.getStatusCode() != 200) {
//				throw new Exception("Failed to login the Consumer portal and " + response.getStatusCode()
//						+ "and the response body received is: " + response.getBody().asString());
//			}
//		}catch(Exception e) {
//			System.out.print(" Error in API");
//		}
//		JsonPath js = new JsonPath(response.prettyPrint());
//		String refreshtoken = js.get("Records.refreshToken");
//		System.out.print("Refreshtoken "+ refreshtoken);
//		return  refreshtoken;
//	}
}
