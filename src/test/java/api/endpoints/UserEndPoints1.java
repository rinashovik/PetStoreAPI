package api.endpoints;
import static io.restassured.RestAssured.*;
//import io.restassured.matcher.RestAssuredMatchers.*;
//import org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//implementation of CRUD operations

// Reads url-data from properties file

public class UserEndPoints1 {

	
	
	static ResourceBundle getURL() {
		
		
		ResourceBundle routes = ResourceBundle.getBundle("routes");// Load properties file
		return routes;
		
	}
	
	
	
	public static Response CreateUser(User payload){
		
		String post_url = getURL().getString("post_url");
		
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(post_url); // get the response
		//.then();//don't need to call here validation will be test case class
		return response;
	}
	
	public static Response getUser(String username){
		
		String get_url = getURL().getString("get_url");

			Response response =  given()
				.pathParam("username", username)
				.when()
				.get(get_url);
				return response;
				
	}
	
	
public static Response updateUser(String username, User payload){
		
	
	String update_url = getURL().getString("update_url");

	Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(payload)
			.when()
			.put(update_url);
			return response;
			
	}


public static Response deleteUser(String username){
	
	
	String delete_url = getURL().getString("delete_url");

	Response response =  given()
			.pathParam("username", username)
			.when()
			.delete(delete_url);
			 return response;	
}

	
}
