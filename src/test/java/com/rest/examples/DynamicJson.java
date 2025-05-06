package com.rest.examples;
import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rest.files.PayLoad;
import com.rest.files.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class DynamicJson {
	
	@Test(dataProvider = "BookData")	
	public void addBook(String isbn, String isle) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().header("Content-Type","application/json").
		body(PayLoad.addBook(isbn,isle)).when()
		.post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asPrettyString();
		
		JsonPath js=ReusableMethods.rawToJson(response);
		String id=js.getString("ID");
		System.out.println("chen test starts.....");
		System.out.println("ID :"+id);
		

}
	
	@DataProvider(name="BookData")
	public Object[][]getData(){
		return new Object[][] {{"htyk","8876"},{"ddss","6543"},{"rrll","1232"}};
	}
}
