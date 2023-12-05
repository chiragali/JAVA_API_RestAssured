package com.portest;

import BaseSetup.TestBaseSetup;
import com.google.gson.Gson;
//import com.helper.LoginAPI;
import com.helper.LoginAPI_v2;
import com.pojos.LoginPOJO;
import com.utils.ProductUtils;
import com.utils.RestUtils;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.File;

import static org.junit.Assert.assertThat;

public class LoginAPITest_v2 extends TestBaseSetup{
	RestUtils restUtils = new RestUtils();
	Gson gson = new Gson();
	ProductUtils productUtils = new ProductUtils();
	LoginPOJO loginPOJO = new LoginPOJO();
	//LoginAPI loginAPI = new LoginAPI();
	LoginAPI_v2 loginAPI_v2  = new LoginAPI_v2();


//	@Test(groups = { "Regression" }, description = "Verify Consumer Login")
//	public void verifyConsumerPortalLogin_v2() throws Exception {
//
//		extentReportTest = extentReportLibrary.createTest("Consumer Portal API validation",
//				"Login Consumer API");
//		Response resp = loginAPI_v2.postConsumerPortalResponseWithValidInput();
//
//		JsonPath js = restUtils.convertResponsetoJsonPath(resp);
//		Assert.assertEquals(js.get("Message.Type"), "Success");
//
//	}


	@Test(groups = { "Regression" }, description = "Verify Consumer Login")
	public void verifyConsumerPortalLogin_v2Dynamic() throws Exception {

		extentReportTest = extentReportLibrary.createTest("Consumer Portal API validation",
				"Login Consumer API");
		Response resp = loginAPI_v2.postConsumerPortalResponseDynamic();

		JsonPath js = restUtils.convertResponsetoJsonPath(resp);
		Assert.assertEquals(js.get("Message.Type"), "Success");

//		// Validate the Schema i.e Key and format of response
//		assertThat(resp.asString(), JsonSchemaValidator.matchesJsonSchema(new File("./src/main/java/com/schema/loginSchema.json")));

	}


}
