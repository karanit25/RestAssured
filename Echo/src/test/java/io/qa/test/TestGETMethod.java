package io.qa.test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.response.Response;
public class TestGETMethod {
	
	//verify the Status Code in the response
	@Test(priority = 1)
	public void testStatusCode(){
		Response response = get("https://postman-echo.com/GET")
				.then().statusCode(200)
				.extract().response();
		System.out.println("GET Method Response"+response.asString());
	}
	//verify response body
	@Test(priority = 2)
	public void testBody(){
		Response response =get("https://postman-echo.com/GET")
				.then().assertThat()
               .body("headers.host", equalTo("postman-echo.com"))
               .extract().response();
		System.out.println("GET Method Response"+response.asString());
	}
	//verify response Header
	@Test(priority = 3)
	public void testHeader(){
		Response response =get("https://postman-echo.com/GET")
				.then()
               .header("Content-Encoding", "gzip")
               .extract().response();
		
		System.out.println("GET Method Response"+response.asString());
	}
}