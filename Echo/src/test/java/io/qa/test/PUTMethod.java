package io.qa.test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class PUTMethod {
	public static Map<String, String> map = new HashMap<String, String>();
	@BeforeTest
	public void putdata(){
		map.put("userId", "2");
		map.put("id", "19");
		map.put("title", "this is projectdebug.com");
		map.put("body", "i am testing REST api with REST-Assured and sending a PUT request.");	
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
		RestAssured.basePath = "/posts/";
	}

	@Test
	public void testPUT(){
		Response response = 
		given()
			.contentType("application/json")
			.body(map)
		.when()
			.put("/100")//---------> Specify which resource we need to update 
		.then()
			.statusCode(200)
			/*.and()*/
			.body("title", equalTo("this is projectdebug.com"))
			.extract().response();
		
		System.out.println("PUT Method Response---->"+response.asString());
	}
}