package helen.apis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import helen.apiPojos.NewPost;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateNewPost {


	public static void main(String[] args) {

		//login and get a token
		String token = helen.utilities.BoraTechApis.login("helenhjahn@gmail.com", "06102021");		
		
		//create new post
		String endpoint = "api/posts";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		//request
		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json"); 
		
		Map <String, String> expectedPost = new HashMap<>();
		expectedPost.put("text", "Hello... " + helen.utilities.Keywords.getTimeStamp());		
		
		//response back
		request.body(expectedPost);
		Response response = request.post(endpoint);
		
		
		//validate a list of json objects
		List<NewPost> actualPosts = response.jsonPath().getList("", NewPost.class);
		for (NewPost actualPost : actualPosts) {
			if (expectedPost.equals(actualPost)) {
				System.out.println("Pass. New Post added: " + actualPost);
			} else {
				System.out.println("Fail. Expected: " + expectedPost + "Actual: " + actualPost);
			}
		}
				

		
		
	
	}
}
