package com.utils;
import static io.restassured.RestAssured.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import com.apttus.sfdc.rudiments.utils.SFDCRestUtils;
//import com.jayway.restassured.RestAssured;
//import com.jayway.restassured.config.HttpClientConfig;
//import com.jayway.restassured.config.RestAssuredConfig;
//import com.jayway.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

public class RestUtils {

	public String accessToken;
	public  String instanceUrl;


	public Response sendPOSTrequest(String url, Map<String, String> headers, Map<String, String> queryParams, Map<String, String> pathParams, String payload) {
		RequestSpecification requestSpecification = createRequestSpecification(headers, queryParams, pathParams);

		
		return RestAssured.given()
				.spec(requestSpecification)
				.body(payload)
				.post(url);
	}

	public static Response sendGETRequest(String endpoint, Map<String, String> headers, Map<String, String> queryParams, Map<String, String> pathParams) {
		RequestSpecification requestSpecification = createRequestSpecification(headers, queryParams, pathParams);
		return RestAssured.given()
				.spec(requestSpecification)
				.when()
				.get(endpoint);
	}

	private static RequestSpecification createRequestSpecification(Map<String, String> headers, Map<String, String> queryParams, Map<String, String> pathParams) {
		RequestSpecification requestSpecification = RestAssured.given();

		if (headers != null) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				requestSpecification.header(entry.getKey(), entry.getValue());
			}
		}

		if (queryParams != null) {
			requestSpecification.queryParams(queryParams);
		}

		if (pathParams != null) {
			requestSpecification.pathParams(pathParams);
		}

		// Add other common headers, query parameters, or path parameters if needed
		// requestSpecification.header("common-header", "value");
		// requestSpecification.queryParam("common-query-param", "value");
		// requestSpecification.pathParam("common-path-param", "value");

		return requestSpecification;
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

	public static void appendToLogFile(String url, Map<String, String> headers, Map<String, String> queryParams, Response response, String startTime, String endTime) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy");
		String currentDate = dateFormat.format(new Date());
		String logDirectory = "./logs/";
		new File(logDirectory).mkdirs();
		String logFileName = logDirectory + "log_" + currentDate + ".txt";


		try (FileWriter file = new FileWriter(logFileName, true)) {

			// Get current UTC time
			String currentTime = java.time.OffsetDateTime.now(java.time.ZoneOffset.UTC).toString();
			// Append request, response, and UTC time to the log file
			file.write("URL Endpoint: " + url +",");
			if(response.getStatusCode() == 200) {
				file.write("Result: Pass,");
			}else{
				file.write("Result: Fail,");
			}
				file.write("Status Code: " + response.getStatusCode() + ",");
				file.write("Request Headers: " + headers + ",");
				if(queryParams!=null){
				file.write("Query Parameters: " + queryParams + ",");}
				file.write("Response Body: " + response.getBody().asString() + ",");
				file.write("UTC Start Time: " + startTime + ",");
				file.write("UTC End Time: " + endTime + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void clearLogFile() {
		try (PrintWriter writer = new PrintWriter(new FileWriter("./log.txt"))) {
			// Clear the log file by truncating its content
			writer.print("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void appendToLogCSVFile(String url, Map<String, String> headers, Map<String, String> queryParams, Response response, String startTime, String endTime) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy");
		String currentDate = dateFormat.format(new Date());
		String csvDirectory = "./csv/";
		new File(csvDirectory).mkdirs();
		String csvFileName = csvDirectory + "log_" + currentDate + ".csv";

		// Header line for the CSV file
		String csvHeader = "URL Endpoints, Result, Status Code, Request Headers,Query Parameters,Response Body,UTC Start Time, UTC End Time";

		
		try (PrintWriter writer = new PrintWriter(new FileWriter(csvFileName, true))) {

			writer.println(csvHeader);
			// Get current UTC time
			String currentTime = java.time.OffsetDateTime.now(java.time.ZoneOffset.UTC).toString();
			String result;
			String responsestr = ""; //response.getBody().asString()

			if(response.getStatusCode() == 200) {
				result="PASS";
			}else{
				result="FAIL";
			}

			// Format log entry as CSV line
			String csvLine = "\""+url+"\",\""+result+"\",\"" + response.getStatusCode() + "\",\"" +
					headers + "\",\"" + queryParams + "\",\"" + responsestr + "\",\"" +
					startTime + "\",\"" + endTime + "\"";

			// Write CSV line to the file
			writer.println(csvLine);


			// Append request, response, and UTC time to the log file
//			file.write("URL Endpoint: " + url +","+ "\n");
//			if(response.getStatusCode() == 200) {
//				file.write("Result: Pass," + "\n");
//			}else{
//				file.write("Result: Fail," + "\n");
//			}
//			file.write("Status Code: " + response.getStatusCode() + "," + "\n");
//			file.write("Request Headers: " + headers + "," + "\n");
//			file.write("Query Parameters: " + queryParams + "," + "\n");
//			//file.write("Response Body: " + response.getBody().asString() + "," + "\n");
//			file.write("UTC Start Time Time: " + startTime + "," + "\n");
//			file.write("UTC End Time Time: " + endTime + "\n\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void appendToLogExcelFile(String url, Map<String, String> headers, Map<String, String> queryParams, Response response, String startTime, String endTime) {

		String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String excelFolderPath = "./Excel"; // Folder where you want to save the file
		String fileName = excelFolderPath + "/log_" + currentDate + ".xlsx"; // Updated file path

		// Create the directory if it does not exist
		File directory = new File(excelFolderPath);
		if (!directory.exists()) {
			boolean created = directory.mkdirs();
			if (created) {
				System.out.println("Directory created: " + excelFolderPath);
			} else {
				System.out.println("Failed to create directory: " + excelFolderPath);
				return; // Exit the method if directory creation fails
			}
		}

		// Create or load the existing Excel workbook
		Workbook workbook;
		Sheet sheet;
		if (new File(fileName).exists()) {
			try (FileInputStream fis = new FileInputStream(fileName)) {
				workbook = new XSSFWorkbook(fis);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		} else {
			workbook = new XSSFWorkbook();
		}

		// Get or create the sheet for the current day's log
		if (workbook.getSheet(currentDate) != null) {
			sheet = workbook.getSheet(currentDate);
		} else {
			sheet = workbook.createSheet(currentDate);
			// Create headers for the new sheet
			Row headerRow = sheet.createRow(0);
			String[] headersExcel = {"URL Endpoints", "Result", "Status Code", "Request Headers",
					"Query Parameters", "Response Body", "UTC Start Time", "UTC End Time"};
			for (int i = 0; i < headersExcel.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headersExcel[i]);
			}
		}

		// Create a new row for the data
		int lastRowNum = sheet.getLastRowNum();
		Row dataRow = sheet.createRow(lastRowNum + 1);

		String result;
		String responsestr = ""; //response.getBody().asString()

		if(response.getStatusCode() == 200) {
			result="PASS";
		}else{
			result="FAIL";
		}

		// Populate the data into the row
		String[] rowData = {url, result, String.valueOf(response.getStatusCode()), String.valueOf(headers),
				String.valueOf(queryParams), response.getBody().asString(), startTime, endTime};
		for (int i = 0; i < rowData.length; i++) {
			Cell cell = dataRow.createCell(i);
			cell.setCellValue(rowData[i]);
		}

		// Save the updated workbook to the file
		try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
			workbook.write(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


