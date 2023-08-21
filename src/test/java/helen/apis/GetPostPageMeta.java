package helen.apis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import helen.apiPojos.NewPost;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetPostPageMeta {

	public static void main(String[] args) {
		
		//login and get a token
		String token = helen.utilities.BoraTechApis.login("helenhjahn@gmail.com", "06102021");		
		
		//request
		String endpoint = "api/posts";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();
		
		//response back
		request.header("x-auth-token", token);
		Response response = request.get(endpoint);
		List<NewPost> actualPosts = response.jsonPath().getList("", NewPost.class);
		
		
		Object expectedPost = helen.utilities.BoraTechApis.addNewPost(token, "Helloooo " );
		

		//validate a list of json objects
		boolean found = false;
		for (NewPost actualPost : actualPosts) {
			if (expectedPost.equals(actualPost)) {
				found = true;
				break;
			}
		}
		
		if (found) {
			System.out.println("Pass");
		} else {	
			System.out.println("Fail");
		}
		
	
	}

}
