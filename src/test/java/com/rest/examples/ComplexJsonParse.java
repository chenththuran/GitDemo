package com.rest.examples;
import org.testng.Assert;

import com.rest.files.PayLoad;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) {
		
		JsonPath js=new JsonPath(PayLoad.getCoursePrice());
		System.out.println(js.prettify());
		
		String purchaseAmt=js.getString("dashboard.purchaseAmount");
		int purchAmt=Integer.parseInt(purchaseAmt);
		System.out.println("purchase amount is :"+purchAmt);
		Assert.assertEquals(9110, purchAmt);
		
		String website=js.getString("dashboard.website");
		System.out.println("website :"+website);
		
		//fetching second course details
		String title=js.getString("courses[1].title");
		double price=js.getDouble("courses[1].price");
		int copies=js.getInt("courses[1].copies");
		System.out.println("title is :"+title);
		System.out.println("copies :"+copies);
		System.out.println("price :"+price);
		
		Assert.assertEquals("Cypress", title);
		Assert.assertEquals(4, copies);
		Assert.assertEquals(40.0, price);
		
		//assert the number of courses
		System.out.println("number of courses :"+js.getInt("courses.size()"));
		Assert.assertEquals(3, js.getInt("courses.size()"));
		
		int courseSize=js.getInt("courses.size()");
		
		boolean isTitleCyprus=false;
		for(int i=0;i<courseSize;i++) {
			String title1=js.getString("courses["+i+"].title");
			System.out.println(title1.toLowerCase());
			if(title1.equalsIgnoreCase("cypress")) {
				isTitleCyprus=true;
				break;
			}
			
			
		}
		
		System.out.println("Title boolean :"+isTitleCyprus);
		
		
		//check total price match with purchase amount
		int totPrice=0;
		for(int i=0;i<courseSize;i++) {
			totPrice+=js.getInt("courses["+i+"].price");
		}
		
		System.out.println("total price :"+totPrice);
		//Assert.assertEquals(purchAmt, totPrice);
		
		int price1=0;
		for(int i=0;i<courseSize;i++) {
			String title1=js.getString("courses["+i+"].title");
			if(title1.equalsIgnoreCase("RPA")) {
				price1=js.getInt("courses["+i+"].price");
				break;
			}
		}
		
		Assert.assertEquals(price1, 45);
		
		
		
	}

}
