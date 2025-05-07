package com.rest.examples;
import io.restassured.RestAssured;
import com.rest.files.*;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import com.rest.files.*;

public class Basics {
	
	public static void main(String[] args) throws IOException {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String responseData=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")//text/plain
		.body(GenerateStringFromResource("\\RestAssuredAutomation\\src\\test\\java\\com\\rest\\files\\AddBook.json"))
		.when().post("maps/api/place/add/json")//submitting the api, resource, http method
		.then().assertThat().statusCode(200).body("scope" ,equalTo("APP"))
		.body("status", equalTo("OK")).body("id", notNullValue()).header("Server", containsString("Apache"))
		.extract().response().asPrettyString();
		
		System.out.println(responseData);
		System.out.println("test 1");
		System.out.println("test 2");
		//convert response into json and retrive data
		JsonPath js=ReusableMethods.rawToJson(responseData);
		String placeId=js.getString("place_id");
		String reference=js.getString("reference");
		String id=js.getString("id");
		String status=js.getString("status");
		
		//update address
		String newAddress="72 winter walk, USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","text/plain")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().statusCode(200)
		.body("msg",equalTo("Address successfully updated"));
		
		
		//get address
		String getPlaceResponse=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200)
		.extract().response().asPrettyString();
		
		JsonPath js2=ReusableMethods.rawToJson(getPlaceResponse);
		String actualAddress=js2.getString("address");
		System.out.println(actualAddress);
		System.out.println("==============");
		Assert.assertEquals(actualAddress, newAddress);
		
	}
	
	public static String GenerateStringFromResource(String path) throws IOException {



	    return new String(Files.readAllBytes(Paths.get(path)));



	}

}
