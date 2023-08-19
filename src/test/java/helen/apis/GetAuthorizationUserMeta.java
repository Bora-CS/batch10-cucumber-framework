package helen.apis;

import helen.apiPojos.User;

import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import helen.utilities.BoraTechApis;



public class GetAuthorizationUserMeta {
	
	public static void main(String[] args) {

	
		String token = helen.utilities.BoraTechApis.login("helenhjahn@gmail.com", "06102021");
		
		User user = helen.utilities.BoraTechApis.getAuthorizedUserMeta(token);
		
		System.out.println(user.email);
		System.out.println(user._id);
		
		
		
		
		
	}


}