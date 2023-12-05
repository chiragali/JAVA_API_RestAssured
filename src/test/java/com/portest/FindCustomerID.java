package com.portest;

import BaseSetup.TestBaseSetup;
import com.helper.FindCustomerEmailAPI;
import com.helper.FindCustomerIDAPI;
import com.helper.LoginAPI_v2;
import com.utils.RestUtils;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


public class FindCustomerID extends TestBaseSetup{
	RestUtils restUtils = new RestUtils();
	FindCustomerIDAPI findCustomerIDAPI = new FindCustomerIDAPI();
	FindCustomerEmailAPI findCustomerAPI = new FindCustomerEmailAPI();
	LoginAPI_v2 loginAPI_v2 = new LoginAPI_v2();
	//private static final Logger logger = LogManager.getLogger(ApiRequestHandler.class);


//	@Test(groups = { "Regression" }, description = "Verify the Contract endpoint")
//	public void verifyGetContractbyID() throws Exception {
//
//		extentReportTest = extentReportLibrary.createTest("Verify the Contract endpoint",
//				"Contract GET API");
//
//		String accessToken = loginAPI_v2.generateAccessToken();
//		Response resp;
//
//		for(int i=1; i<=5; i++)
//		{
//			resp = findCustomerAPI.getCUstomerbyEmail(accessToken);
//			JsonPath js = restUtils.convertResponsetoJsonPath(resp);
//			int custId;
//
//			// Call GAPI endpoint is status is not equal to 200
//			if(resp.getStatusCode() != 200 || (!js.get("Message.Type").equals("Success")))
//			{
//				custId = js.getInt("Records[0].customerId");
//				Response resp1 = findCustomerIDAPI.getCustomerbyID(custId);
//
//				JsonPath js1 = restUtils.convertResponsetoJsonPath(resp);
//				String id = js1.getString("[0].Id");
//				System.out.println(" ID is --> "+id);
//			}
//		}
//
//	}

	@Test(groups = { "Regression" }, description = "Verify the Contract endpoint")
	public void verifyGetContractbyID_log() throws Exception {

		extentReportTest = extentReportLibrary.createTest("Verify the Contract endpoint",
				"Contract GET API");

		// Generate Access Token

		List<String> list_token = loginAPI_v2.generateAccessRefreshToken();

		String accessToken = list_token.get(0); //abc
		String refreshToken = list_token.get(1);
		Response findCustresponse;

		for(int j=1; j<=2; j++)
		{

		int time = 30;
		// Iterate n times -- Find Customer CP
		for(int i=1; i<=2; i++)
		{
			System.out.println(" ************ Waiting for "+time+ " secs ************ ");
			Thread.sleep(time*1000);

			// Call Find Customer CP
			findCustresponse = findCustomerAPI.getCUstomerbyEmail(accessToken);

			//JsonPath js = restUtils.convertResponsetoJsonPath(findCustresponse);
			String email = restUtils.getApplicationProperty("userName");

			// Call GAPI endpoint if status is not equal to 200
			System.out.println("Find Customer CP Status code is: "+findCustresponse.getStatusCode());
			if(findCustresponse.getStatusCode() != 200)
			{
				Response getAllContactsresp = findCustomerIDAPI.getAllContacts(email);
				System.out.println("GAPI Get Contract Status code is: "+getAllContactsresp.getStatusCode());
				Assert.assertEquals(getAllContactsresp.getStatusCode(),200);
			}
			time = time+30;
		}
			
		// Call Refresh Token	
		Response resp = loginAPI_v2.postConsumerPortalResponseRefresh(accessToken, refreshToken);
		JsonPath jsonPath = restUtils.convertResponsetoJsonPath(resp);
		//String accessToken = jsonPath.get("Records.accessToken");
		accessToken = jsonPath.get("Records.RefreshToken");
		System.out.println(" Taking up Refresh Token :"+accessToken);

		}
	}

	@Test(groups = { "Regression" }, description = "Verify the Contract endpoint")
	public void verifyGetContractbyID_CSV() throws Exception {

		extentReportTest = extentReportLibrary.createTest("Verify the Contract endpoint",
				"Contract GET API");

		// Generate Access Token
		String accessToken = loginAPI_v2.generateAccessToken();
		Response findCustresponse;

		// restUtils.clearLogFile(); If we want to clear content

		// Iterate n times -- Find Customer CP
		for(int i=1; i<=3; i++)
		{

			// Call Find Customer CP
			findCustresponse = findCustomerAPI.getCUstomerbyEmail(accessToken);
			//JsonPath js = restUtils.convertResponsetoJsonPath(findCustresponse);
			String email = restUtils.getApplicationProperty("userName");

			// Call GAPI endpoint if status is not equal to 200
			System.out.println("Status code is: "+findCustresponse.getStatusCode());
			if(findCustresponse.getStatusCode() != 200)
			{
				Response getAllContactsresp = findCustomerIDAPI.getAllContacts(email);
				JsonPath js1 = restUtils.convertResponsetoJsonPath(getAllContactsresp);

				// Code to fetch the email from response
				//String emailResponse = js1.getString("[0].Emails[0].Email");
				Assert.assertEquals(getAllContactsresp.getStatusCode(),200);

			}
			//Thread.sleep(60000);
		}

	}

}
