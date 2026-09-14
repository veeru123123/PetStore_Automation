package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
     Faker faker;
     User userpayload;
    public Logger logger;
	
	@BeforeClass
	public void setup() 
	{
		faker=new Faker();
		userpayload=new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
	//logs
		logger=LogManager.getLogger(this.getClass());
		logger.debug("debugging.......");
	}
	
	@Test(priority=1)
	 public void testpostuser() {
		logger.info("*******creating user*******");
		Response response=UserEndPoints.CreateUser(userpayload);
		response.then().log().all();
	Assert.assertEquals(response.getStatusCode(),200);
	    logger.info("*******user created*******");
	}
	@Test(priority=2)
     public void testgetuserByName()
     {
		logger.info("*******reading user*******");
    	 Response response=UserEndPoints.ReadUser(this.userpayload.getUsername());
    	 response.then().log().all();
    	 Assert.assertEquals(response.getStatusCode(),200);
    	 logger.info("*******displayed user*******");
     }
	
	@Test(priority=3)
	 public void testUpdateuserByName()
	{
		logger.info("*******updating user*******");
		//update payload
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints.UpdateUser(this.userpayload.getUsername(), userpayload);
		 response.then().log().body();
		 Assert.assertEquals(response.getStatusCode(),200);
		 logger.info("******* user is updated*******");
	//checking data after update
		 Response responseAfterupdate=UserEndPoints.ReadUser(this.userpayload.getUsername());
		 Assert.assertEquals(responseAfterupdate.getStatusCode(),200);
	}
	 @Test(priority=4)
	 public void testDeleteuserByName()
	 {
		 logger.info("*******deleting user*******");
		 Response response=UserEndPoints.DeleteUser(this.userpayload.getUsername());
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(),200);
		 logger.info("******* user deleted*******");
	 }
}
