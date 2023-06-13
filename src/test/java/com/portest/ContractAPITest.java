package com.portest;

import com.google.gson.Gson;
import com.helper.ContractAPI;
import com.helper.LoginAPI;
import com.pojos.LoginPOJO;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.utils.*;

import BaseSetup.TestBaseSetup;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;



public class ContractAPITest extends TestBaseSetup{
	RestUtils restUtils = new RestUtils();
	Gson gson = new Gson();
	ProductUtils productUtils = new ProductUtils();
	LoginPOJO loginPOJO = new LoginPOJO();
	LoginAPI loginAPI = new LoginAPI();
	ContractAPI contractAPI = new ContractAPI();


	@Test(groups = { "Regression" }, description = "Verify the Contract endpoint")
	public void verifyGetContract() throws Exception {
		
		Response resp = contractAPI.getContractList();
		System.out.println(resp.asString());
	}

}
