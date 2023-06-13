package com.utils;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonToXmlConverter {
    public static void main(String[] args) {
        String jsonResponse = "{\"ModelType\":\"M01\",\"Id\":\"01\",\"Name\":\"Model Name\",\"Identifiers\":{},\"Description\":\"This is chirag\",\"DisplayOrder\":1,\"Division\":\"Div\",\"Images\":[{\"Uri\":\"/image/animal\",\"AltText\":\"Animal\"}],\"ParentCategoryId\":\"Par01\",\"Tags\":[\"T1\"],\"AdditionalFields\":{},\"CreatedDateTime\":\"2023-03-22T14:15:22Z\",\"UpdatedDateTime\":\"2023-03-22T16:15:22Z\",\"Hidden\":0}";

        try {
//            // Convert JSON to XML
//            JSONObject jsonObject = new JSONObject(jsonResponse);
//            String xmlData = XML.toString(jsonObject);

            // Write XML to file
            FileWriter fileWriter = new FileWriter("response.xml");
            fileWriter.write(jsonResponse);
            fileWriter.close();

            System.out.println("XML data has been written to response.xml successfully.");

            // Read XML from file
            BufferedReader bufferedReader = new BufferedReader(new FileReader("response.xml"));
            StringBuilder xmlContent = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                xmlContent.append(line);
            }
            bufferedReader.close();

            // Format XML content
            String formattedXmlContent = StringEscapeUtils.unescapeXml(xmlContent.toString());

            // Print XML content to console
            System.out.println("XML content:");
            System.out.println(formattedXmlContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
