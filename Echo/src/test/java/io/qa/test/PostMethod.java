package io.qa.test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
public class PostMethod {

	/*We are using map in java to store all the contents using put() method 
	 * and then simply sending the map object in body() function
	*/
	
	public static Map<String, String> map = new HashMap<String, String>();
	@BeforeTest
	public void postdata(){
		map.put("userId", "23");
		map.put("id", "234");
		map.put("title", "this is projectdebug.com");
		map.put("body", "this is REST-Assured Tutorial");	
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
		RestAssured.basePath = "/posts/";
	}
	
	/*If you write above code,means specyfying RestAssured.baseURI & RestAssured.basePath you dont need to store the URL in any variable, 
	 * REST-Assured will automatically provide the URL, whenever you call any HTTP method, 
	 * in this case we called post() method, without providing any URL parameter in it.
	*/
	@Test
	public void testPOST(){
		given()
		.contentType("application/json")
		.body(map)
		.when()
		.post()
		.then()
		.statusCode(201)
		.and()
		.body("title", equalTo("this is projectdebug.com"));
	}
	
	@Test
	public void testPOST2(){
		Response response = 
				given()//--->Given Header as content type 
					.contentType("application/json")
					.body(map)//---> pass paylod as its body of POST method which is availble in form of Map
				.when()//---> when this will be perfomed
				.post()//--->on call of POST Method. No need to paas URL here EST-Assured will automatically provide the URL
				.then()//--> then after call what need to be performed
					.statusCode(201)//----> check status code shall be 201 in response
					.and()//---> another check
					.body("title", equalTo("this is projectdebug.com"))
					.contentType(ContentType.JSON)
					.extract().response();//---->body shall have title exactly as expected String
		
		System.out.println("POST Call Response"+response.asString());
}
}
