package api.test;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTestCases {

	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setUpData() {
		
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());	
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(6, 12));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
//		userPayload.setUserStatus(faker.numerify(null));
		
		
	}
	
	@Test (priority=1)
	public void testPostUser() {
		
		Response response = UserEndPoints.CreateUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		
	}
	
	@Test (priority=2)
	public void testGetUserByName() {
		
		Response response = UserEndPoints.getUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	@Test (priority=3)
	public void testUpdateUserByname() {
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		//update data by payload
			Response response = UserEndPoints.updateUser(userPayload.getUsername(),userPayload);
			response.then().log().body();
			Assert.assertEquals(response.getStatusCode(), 200); // testNG assertion
	
//			response.then().log().body();
//			response.statusCode(200);//restAssured assertion
//			
		//Checking data after updated
			
			Response responseAfterUpdate = UserEndPoints.getUser(userPayload.getUsername());
//			response.then().log().all();
			Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
				
		
	}
	
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		
		
		Response response = UserEndPoints.deleteUser(userPayload.getUsername());
//		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
		
		
	}
	
}
