	package api.test;

//	import org.apache.logging.log4j.LogManager;
//	import org.apache.logging.log4j.core.Logger;
	import org.testng.Assert;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import com.github.javafaker.Faker;

	import api.endpoints.UserEndPoints1;
	import api.payload.User;
	import io.restassured.response.Response;



public class UserTestCases1 {

		Faker faker;
		User userPayload;
//		public Logger logger;
		
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
//			userPayload.setUserStatus(faker.numerify(null));
			
//			logger = (Logger) LogManager.getLogger(this.getClass());
			
			
			
		}
		
		@Test (priority=1)
		public void testPostUser() {
			
//		logger.info("**************Create User******************");
			
			Response response = UserEndPoints1.CreateUser(userPayload);

			
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
		
		}
		
		@Test (priority=2)
		public void testGetUserByName() {
			
			
//			logger.info("**************Getting User******************");

			
			Response response = UserEndPoints1.getUser(userPayload.getUsername());
			

			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			
			
		}
		@Test (priority=3)
		public void testUpdateUserByname() {
			
			userPayload.setFirstName(faker.name().firstName());
			userPayload.setLastName(faker.name().lastName());
			userPayload.setEmail(faker.internet().safeEmailAddress());
			
			//update data by userPayload
				Response response = UserEndPoints1.updateUser(userPayload.getUsername(),userPayload);

				response.then().log().body();
				Assert.assertEquals(response.getStatusCode(), 200); // testNG assertion
		
//				response.then().log().body();
//				response.statusCode(200);//restAssured assertion
//				
			//Checking data after updated
				
//				logger.info("************** User Updated******************");

				
				Response responseAfterUpdate = UserEndPoints1.getUser(userPayload.getUsername());

//				response.then().log().all();
				Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
					
			
		}
		
		
		@Test(priority=4)
		public void testDeleteUserByName() {
			
//			logger.info("**************Deleting  User******************");

			
			Response response = UserEndPoints1.deleteUser(userPayload.getUsername());

			response.then().log().all();
			Assert.assertEquals(response.getStatusCode(), 200);
			
			
			
			
		}
		
		
		
		
	}

	
	

