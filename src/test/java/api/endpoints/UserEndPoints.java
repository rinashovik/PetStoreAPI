package api.endpoints;
import static io.restassured.RestAssured.*;
//import io.restassured.matcher.RestAssuredMatchers.*;
//import org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//implementation of CRUD operations



public class UserEndPoints {

	
	public static Response CreateUser(User payload){
		
		
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.post_url); // get the response
		//.then();//don't need to call here validation will be test case class
		return response;
	}
	
	public static Response getUser(String username){
		
			Response response =  given()
				.pathParam("username", username)
				.when()
				.get(Routes.get_url);
				return response;
				
	}
	
	
public static Response updateUser(String username, User payload){
		
	Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
			.when()
			.put(Routes.update_url);
			return response;
			
	}


public static Response deleteUser(String username){
	
	Response response =  given()
			.pathParam("username", username)
			.when()
			.delete(Routes.delete_url);
			 return response;	
}

	
}
