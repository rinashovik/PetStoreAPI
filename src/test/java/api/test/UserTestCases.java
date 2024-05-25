package api.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	public Logger logger;
	
	@BeforeClass
	public void setUp() {
		
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());	
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(6, 12));
		userPayload.setPhone(faker.phoneNumber().cellPhone());		
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	
	@Test (priority=1)
	public void testPostUser() {
		
	logger.info("**************Create the User******************");
		
		Response response = UserEndPoints.CreateUser(userPayload);

		
		System.out.println("\n****************\n");

		System.out.print("\nLength : " +response.contentType().length());
		System.out.println("\n****************\n");
		response.then().log().body();

		
		System.out.println("\n*******Cookies********\n");
		
		response.then().log().cookies();
		
		System.out.println("\n****************\n");

		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		Assert.assertTrue(true);
		logger.info("**************User created******************");

		
	}
	
	@Test (priority=2)
	public void testGetUserByName() {
		
		
		logger.info("**************Getting User Info******************");

		
		Response response = UserEndPoints.getUser(userPayload.getUsername());
		

		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************User Info is Displayed******************");

		
	}
	@Test (priority=3)
	public void testUpdateUserByname() {
		
		logger.info("**************User Info is Updating******************");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		//update data by userPayload
			Response response = UserEndPoints.updateUser(userPayload.getUsername(),userPayload);

			response.then().log().body();
			Assert.assertEquals(response.getStatusCode(), 200); // testNG assertion
	
			response.then().log().body();
//			response.statusCode(200);//restAssured assertion
//			
		//Checking data after updated
			
//			logger.info("************** User Updated******************");

			
			Response responseAfterUpdate = UserEndPoints.getUser(userPayload.getUsername());

			response.then().log().all();
			Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
			logger.info("**************User Info is Updated******************");

		
	}
	
	
	@Test(priority=4)
	public void testDeleteUserByName() {
		
		logger.info("**************Deleting User******************");

		
		Response response = UserEndPoints.deleteUser(userPayload.getUsername());

		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************User Deleted**********");

		
		
		
	}
	
	
	
	
}
