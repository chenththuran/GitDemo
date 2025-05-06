package com.rest.examples;

import io.restassured.*;
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


public class SerializeTest {
	
	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String res=given().queryParam("key", "qaclick123")
		.body("")
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asPrettyString();
		
		
		
	}

}
