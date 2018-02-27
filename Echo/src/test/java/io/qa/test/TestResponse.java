package io.qa.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

/*For testing the real world API’s we need to get the response and validate that, perform test on it, 
 * and also need to store it for the record. 
 * 	Here we will store it in Response object.
*/
public class TestResponse {
	@BeforeTest
	public void setup(){
		
		RestAssured.baseURI = "https://postman-echo.com";
		RestAssured.basePath = "/get";
	}
	@Test
	public void testResponse(){
		Response response = 
				given().
				when().
				get().
				then().contentType("application/json").
				body("headers.x-forwarded-port", equalTo("443")).
				extract().
				response(); 
		//-->extract the JSON response in Response object and later use it for validation.
		
		String stringResponse = response.path("headers.x-forwarded-proto");
		System.out.println(stringResponse);

		/*In some scenarios that URI may be dependent on the first URI, 
		 * without calling the first URI we will not get the link to the next resource URI. 
		 * So in last four lines of code you can see, we stored the URI to a string variable nextURI 
		 * and then again we called that URI using get() and performed a validation on the new URI.
		*/
		
		String nextURI = response.path("url");
		get(nextURI)
		.then()
		.body("headers.host", equalTo("postman-echo.com"));	
	}
}