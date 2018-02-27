package io.qa.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

/*Schema is the structure behind data organization. 
 * It is a visual representation of how different fields, values are organized in a JSON/XML file.
JSON schema describes your existing data format, clear, 
human and machine readable documentation, complete structural validation, 
useful for automated testing ad validating client-submitted data.

We are not dealing with validation of data here,validation of schema means, 
checking whether our JSON response is in its standard structure or not,
 all the required fields are there or not, with the required data types.
 
 Generate JSON schema for Respose from https://jsonschema.net/#/ and save is in .jsd file for comparison
*/
public class SchemaValidationTest {
	@Test
	public void schemaValidationTest(){
		given()
	        .accept(ContentType.JSON)
	        .when()
	        .get("https://jsonplaceholder.typicode.com/posts/1")
	        .then()
	        .assertThat()
	        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemaFile.jsd"))
	        .statusCode(is(HttpStatus.SC_OK));
	}
}
       
