package api.test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.Dataproviders;
import io.restassured.response.Response;

public class DDTests {

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProvider.class)
    public void testPostuser(String userID, String userName, String fname,
                           String lname, String useremail, String pwd,
                           String phno) {

        User userPayload = new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(useremail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(phno);
        
        Response response=UserEndPoints.CreateUser(userPayload);
        Assert.assertEquals(response.statusCode(), 200);
        
    }
     @Test(priority=2,dataProvider="UserNames",dataProviderClass=Dataproviders.class)
      public void testDeleteUserByName(String userName) 
      {
    	  Response response=UserEndPoints.DeleteUser(userName);
    	  Assert.assertEquals(response.statusCode(), 200);
      }
}