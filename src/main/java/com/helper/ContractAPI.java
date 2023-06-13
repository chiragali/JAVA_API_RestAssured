package com.helper;

//import com.apttus.lightsaber.rudiments.CartPOJO;

import com.google.gson.Gson;
import com.pojos.LoginPOJO;
import com.utils.RestUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Parameters;

public class ContractAPI {
	//RestUtils restUtils = new RestUtils();
	Gson gson = new Gson();
	RestUtils restUtils = new RestUtils();
	LoginPOJO loginPOJO = new LoginPOJO();
	LoginAPI loginAPI = new LoginAPI();
	Response response;
	String baseUri = restUtils.getApplicationProperty("baseURI");
	String basePath = restUtils.getApplicationProperty("baseContractPath");
	String accessToken = loginAPI.generateAccessToken();

	public String generaterefreshAccessToken() {

		JsonPath jsonPath = restUtils.convertResponsetoJsonPath(response);
		String refreshtoken = jsonPath.get("Records.refreshToken");
		return  refreshtoken;
	}


	public Response getContractList() {
		// TODO Auto-generated method stub
		
		basePath = basePath.replace("{customer_id}", "37");
		String url = baseUri+basePath;
		
		try {
			response = restUtils.getDataWithOutBearertoken(url, accessToken);
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
