package com.portest;
import java.io.File;
import java.util.Iterator;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.report.ProcessingReport;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import java.io.File;

public class Demo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
//		//base URL
//	      RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts/2";
//
//	      //obtain response
//	      given()
//	      .when().get()
//	      .then().assertThat()
//	      .body(JsonSchemaValidator.
//	      matchesJsonSchema(new File("./schema.json")));
//	      System.out.println(RestAssured.baseURI);
		
		String bp = "/v1/customer/{customer_id}/contracts";
		String newbp = bp.replace("{customer_id}", "37");
		System.out.println(newbp);
	      
	}
}	
