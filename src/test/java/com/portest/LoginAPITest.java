//package com.portest;
//
//import com.google.gson.Gson;
//import com.helper.LoginAPI;
//import com.pojos.LoginPOJO;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import org.testng.Assert;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//import com.utils.*;
//
//import BaseSetup.TestBaseSetup;
//
//import java.util.HashMap;
//import java.util.Map;
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//public class LoginAPITest extends TestBaseSetup{
//	RestUtils restUtils = new RestUtils();
//	Gson gson = new Gson();
//	ProductUtils productUtils = new ProductUtils();
//	LoginPOJO loginPOJO = new LoginPOJO();
//	//LoginAPI loginAPI = new LoginAPI();
//
//
//	@Test(groups = { "Regression" }, description = "Verify Consumer Login")
//	public void verifyConsumerPortalLogin() throws Exception {
//
//		extentReportTest = extentReportLibrary.createTest("Consumer Portal API validation",
//				"Login Consumer API");
//		Response resp = loginAPI.postConsumerPortalResponseWithValidInput();
//
//		JsonPath js = restUtils.convertResponsetoJsonPath(resp);
//		Assert.assertEquals(js.get("Message.Type"), "Success");
//
//	}
//
//	@Test(groups = { "Regression" }, description = "Verify Consumer with invalid Org Id")
//	public void verifyConsumerPortalLoginwithInvalidOrgId() throws Exception {
//		extentReportTest = extentReportLibrary.createTest("Consumer Portal API validation with invalid Org Id",
//				"Login Consumer API");
//		Response resp = loginAPI.postConsumerPortalResponseWithInvalidOrgId();
//		JsonPath js = restUtils.convertResponsetoJsonPath(resp);
//		Assert.assertEquals(js.get("Message"), "Invalid username or password.");
//	}
//
//	@Test(groups = { "Regression" }, description = "Verify Consumer with invalid Email address and valid password")
//	public void verifyConsumerPortalLoginwithInvalidEmail() throws Exception {
//
//		extentReportTest = extentReportLibrary.createTest("Consumer Portal API validation with invalid Email Id",
//				"Login Consumer API");
//		Response resp = loginAPI.loginWithInvalidEmail();
//		JsonPath js = restUtils.convertResponsetoJsonPath(resp);
//		Assert.assertEquals(js.get("Message"), "Invalid username or password.");
//
//	}
//
//	@Test(groups = { "Regression" }, description = "Verify Consumer with valid Email address and invalid password")
//	public void verifyConsumerPortalLoginwithInvalidPassword() throws Exception {
//
//		extentReportTest = extentReportLibrary.createTest("Consumer Portal API validation with invalid Password",
//				"Login Consumer API");
//		Response resp = loginAPI.loginWithInvalidPassword();
//		JsonPath js = restUtils.convertResponsetoJsonPath(resp);
//		Assert.assertEquals(js.get("Message"), "Invalid username or password");
//
//	}
//
//}
