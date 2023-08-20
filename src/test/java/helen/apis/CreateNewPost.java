package helen.apis;

import java.util.List;

import helen.apiPojos.Education;
import helen.apiPojos.NewPost;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateNewPost {


	public static void main(String[] args) {

		String token = helen.utilities.BoraTechApis.login("helenhjahn@gmail.com", "06102021");		
		String endpoint = "api/posts";
		
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json"); 
		
		Map <String, String> expectedNewPost = new HashMap<>();
		
		expectedNewPost.put = ("Hi again"  + helen.utilities.Keywords.getTimeStamp());		
		
		request.body(expectedNewPost);
		
		Response response = request.post(endpoint);
		
		JsonPath jp = response.jsonPath();
		
		String actualNewPost = jp.getString("title");
		List <NewPost> np = response.jsonPath().getList("education", NewPost.class);
		
		
		//validate a single json object
		if (expectedNewPost.equals(actualNewPost)) {
			System.out.println("Pass");
		} else {
			System.out.println("Fail");
		}
		
	}
		
	
}
