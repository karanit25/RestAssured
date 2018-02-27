package io.qa.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class DeleteMethod {
	@BeforeTest
	public void setup(){
		
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
		RestAssured.basePath = "/posts/";
	}

	@Test
	public void testDelete(){
		Response response =
		given()
		.when()
		.delete("/1") //--->the resource identifier which need to be deleted
		.then()
		.header("Expires", equalTo("-1")).extract().response();	
		
		System.out.println("DELETE Call Response ---->"+response.toString());
	}
}
