package com.utils;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

//import com.apttus.sfdc.rudiments.utils.SFDCRestUtils;
//import com.jayway.restassured.RestAssured;
//import com.jayway.restassured.config.HttpClientConfig;
//import com.jayway.restassured.config.RestAssuredConfig;
//import com.jayway.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

public class RestUtils {

	public String accessToken;
	public  String instanceUrl;

	public Response postWithoutAppUrl(String url, String body) {
		return RestAssured.given()
				.header("Content-Type", "application/json")
				.log().all()
				 .config(RestAssuredConfig.config().httpClient(HttpClientConfig.
						  httpClientConfig() .setParam("http.connection.timeout",1000000000)
						 .setParam("http.socket.timeout", 1000000000)))
				.body(body)
				.post(url);		
	}
	
	public Response postWithForceUpdateHeader(String url, String body) {
		return RestAssured.given()
				.header("Authorization", "Bearer " + accessToken)
				.header("Content-Type", "application/json")
				.header("Tracer", "application/json")
				.header("forceupdate", true)
				.log().all()
				 .config(RestAssuredConfig.config().httpClient(HttpClientConfig.
						  httpClientConfig() .setParam("http.connection.timeout",1000000)
						 .setParam("http.socket.timeout", 1000000)))
				.body(body)
				.post(url);		
	}

	public Response putWithoutAppUrl(String url, String body) {
		return RestAssured.given()
				.header("Authorization", "Bearer " + accessToken)
				.header("Content-Type", "application/json")
				.log().all()
				 .config(RestAssuredConfig.config().httpClient(HttpClientConfig.
						  httpClientConfig() .setParam("http.connection.timeout",1000000)
						 .setParam("http.socket.timeout", 1000000)))
				.body(body)
				.put(url);		
	}
	
	public Response postElasticAppUrl(String url, String body) {
		return RestAssured.given()
				.header("Authorization", "Bearer " + accessToken)
				.header("Content-Type", "application/json")
				.log().all()
				.config(RestAssuredConfig.config().httpClient(HttpClientConfig.
						  httpClientConfig() .setParam("http.connection.timeout",1000000)
						 .setParam("http.socket.timeout", 1000000)))
				.body(body)
				.post(url);		
	}
	
	public Response patchWithPayload(String url, String body) {
		return RestAssured.given()
				.header("Authorization", "Bearer " + accessToken)
				.header("Content-Type", "application/json")
				.log().all()
				.config(RestAssuredConfig.config().httpClient(HttpClientConfig.
						  httpClientConfig() .setParam("http.connection.timeout",1000000)
						 .setParam("http.socket.timeout", 1000000)))
				.body(body)
				.patch(url);
	}

	public Response deleteWithPayload(String url, String body) {
		return RestAssured.given()
				.header("Authorization", "Bearer " + accessToken)
				.header("Content-Type", "application/json")
				.log().all()
				 .config(RestAssuredConfig.config().httpClient(HttpClientConfig.
						  httpClientConfig() .setParam("http.connection.timeout",1000000)
						 .setParam("http.socket.timeout", 1000000)))
				.body(body)
				.delete(url);
	}

	public Response postWithonlyUrl(String url) {
		return RestAssured.given()
				.header("Authorization", "Bearer " + accessToken)
				.header("Content-Type", "application/json")
				.urlEncodingEnabled(false)
				.log().all()
				.post(url);
	}

	public Response getDataWithBearertoken(String url) {
		return RestAssured.given()
				.header("Authorization", "Bearer " + accessToken)
				.header("Content-Type", "application/json")
				.log().all()
				.get(url);
	}
	
	public Response getDataWithOutBearertoken(String url, String accessToken) {
		return RestAssured.given()
				.header("authorization", accessToken)
				.header("accept", "application/json")
				.log().all()
				.get(url);
	}

	public Response deleteWithoutPayload(String url) {
		return RestAssured.given()
				.header("authorization", "Bearer " + accessToken)
				.header("accept", "application/json")
				.log().all()				
				 .config(RestAssuredConfig.config().httpClient(HttpClientConfig.
				  httpClientConfig() .setParam("http.connection.timeout",1000000)
				 .setParam("http.socket.timeout", 1000000)))				 
				.delete(url);
	}

	public Response putWithoutPayload(String url) {
		return RestAssured.given()
				.header("Authorization", "Bearer " + accessToken)
				.header("Content-Type", "application/json")
				.log().all()
				.config(RestAssuredConfig.config().httpClient(HttpClientConfig.
						  httpClientConfig() .setParam("http.connection.timeout",1000000)
						 .setParam("http.socket.timeout", 1000000)))
				.put(url);
	}
	

	
	public Response deleteWithoutPayloadSync(String url) {
		return RestAssured.given()
				.header("Authorization", "Bearer " + accessToken)
				.header("Content-Type", "application/json")
				.header("isconfirmed",true)
				.log().all()				
				 .config(RestAssuredConfig.config().httpClient(HttpClientConfig.
				  httpClientConfig() .setParam("http.connection.timeout",1000000)
				 .setParam("http.socket.timeout", 1000000)))
				 
				.delete(url);
	}

	public String getApplicationProperty(String propertyKey) {
		Properties props = new Properties();

		try {
			InputStream appPropFile = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/APITests/config.properties");
			props.load(appPropFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = props.getProperty(propertyKey);
		return String.valueOf(result);
	}

	public JsonPath convertResponsetoJsonPath(Response response){

		JsonPath js = new JsonPath(response.prettyPrint());
		return js;
	}
}