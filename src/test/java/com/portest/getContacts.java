package com.portest;

import BaseSetup.TestBaseSetup;
import com.helper.ContactsAPI;
import com.utils.RestUtils;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


public class getContacts extends TestBaseSetup{
	RestUtils restUtils = new RestUtils();
	ContactsAPI contactsAPI = new ContactsAPI();

	@Test(groups = { "Regression" }, description = "Verify the Contract endpoint")
	public void verifyContactsDetails() throws Exception {

		extentReportTest = extentReportLibrary.createTest("Verify the Contract endpoint",
				"Contract GET API");

		Response resp = contactsAPI.getContactDetails();
		JSONArray jsonArray = new JSONArray(resp.asString());
		JSONObject jsonObject = jsonArray.getJSONObject(0);
		String modelType = jsonObject.getString("ModelType");
		System.out.println("Model type got is -- "+modelType);
		Assert.assertEquals(modelType, "Contact");

	}

}
