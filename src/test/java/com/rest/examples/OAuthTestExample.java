package com.rest.examples;



import static io.restassured.RestAssured.given;



import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import org.testng.Assert;

import com.rest.pojo.GetCourse;
import com.rest.pojo.WebAutomation;

import io.restassured.parsing.Parser;

import io.restassured.path.json.JsonPath;

import io.restassured.response.Response;

import io.restassured.response.ResponseBody;





public class OAuthTestExample {



public static void main(String[] args) throws InterruptedException {
	
	String[] courseTitles= {"Selenium Webdriver Java","Cypress","Protractor"};

// TODO Auto-generated method stub

String response =

                given() 

   

                   .formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

                        .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

                        .formParams("grant_type", "client_credentials")

                        .formParams("scope", "trust")

       

                       

                        .when().log().all()

                        .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

System.out.println(response);

JsonPath jsonPath = new JsonPath(response);

    String accessToken = jsonPath.getString("access_token");

    System.out.println(accessToken);

GetCourse getCourse=    given()

.queryParams("access_token", accessToken)

.when()

           .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);


System.out.println("==================================");
System.out.println(getCourse.getlinkedIn());
System.out.println(getCourse.getInstructor());
System.out.println(getCourse.getCourses().getMobile().get(0).getPrice());

//print all course titles of web automation

List<WebAutomation> webAuto=getCourse.getCourses().getWebAutomation();
ArrayList<String> gc=new ArrayList<String>();
for(int i=0; i<webAuto.size();i++) {
	System.out.println(webAuto.get(i).getCourseTitle());
	gc.add(webAuto.get(i).getCourseTitle());
}

List<String> expected=Arrays.asList(courseTitles);

Assert.assertTrue(gc.equals(expected));











}



}


