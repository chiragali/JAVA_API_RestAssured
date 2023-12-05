package com.portest;

import BaseSetup.TestBaseSetup;
import com.helper.FindCustomerEmailAPI;
import com.helper.LoginAPI_v2;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.utils.*;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;

public class FindCustomerEmail extends TestBaseSetup {
	RestUtils restUtils = new RestUtils();
	FindCustomerEmailAPI findCustomerAPI = new FindCustomerEmailAPI();
	LoginAPI_v2 loginAPI_v2 = new LoginAPI_v2();


	@Test(groups = { "Regression" }, description = "Verify the Contract endpoint")
	public void verifyGetContractEmailDynamic() throws Exception {

		extentReportTest = extentReportLibrary.createTest("Verify the Contract endpoint",
				"Contract GET API");

		String accessToken = loginAPI_v2.generateAccessToken();
		Response resp = findCustomerAPI.getCUstomerbyEmail(accessToken);
		System.out.println(resp.asString());

		JsonPath js = restUtils.convertResponsetoJsonPath(resp);
		Assert.assertEquals(js.get("Message.Type"), "Success");

		// Validate the Schema i.e Key and format of response
		assertThat(resp.asString(),JsonSchemaValidator.matchesJsonSchema(new File("./src/main/java/com/schema/findCustomerSchema.json")));
	}

}
