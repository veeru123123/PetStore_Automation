package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.java
//created for perfome CRUD requests for user API.

public class UserEndPoints {
	
	public static Response CreateUser(User payload) {
		
		      Response response=given()
		      .contentType(ContentType.JSON)
		      .accept(ContentType.JSON)
		      .body(payload)
		     .when()
		      .post(Routes.post_url);
		     return response;
	}
	
	//read user

	public static Response ReadUser(String username)
	{
	    Response response = given()
	            .pathParam("username", username)
	        .when()
	            .get(Routes.get_url);

	    return response;
	}
	//update user
	
	public static Response UpdateUser(String userName,User payload) {
		
	      Response response=given()
	      .contentType(ContentType.JSON)
	      .accept(ContentType.JSON)
	      .pathParam("username",userName)
	      .body(payload)
	     .when()
	      .put(Routes.update_url);
	     return response;
    }
	//delete user
	public static Response DeleteUser(String userName) {
		
	      Response response=given()
	       .pathParam("username",userName)
	     .when()
	      .delete(Routes.delete_url);
	     return response;
    }
}
