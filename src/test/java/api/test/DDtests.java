package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDtests {

	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostuser(String userId, String username, String firstName, String lastName, String email, String password, String phone) {
		
		
		User payload = new User();
		
		payload.setId(Integer.parseInt(userId));
		payload.setUsername(username);
		payload.setFirstName(firstName);	
		payload.setLastName(lastName);
		payload.setEmail(email);
		payload.setPassword(password);
		payload.setPhone(phone);
		
		Response response = UserEndPoints.CreateUser(payload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass= DataProviders.class)
	public void testdeleteUserByName(String username) {
		
		
		Response response = UserEndPoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
}
