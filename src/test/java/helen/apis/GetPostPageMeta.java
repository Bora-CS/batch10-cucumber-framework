package helen.apis;

import java.util.List;

import helen.apiPojos.NewPost;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetPostPageMeta {

	public static void main(String[] args) {

		String token = helen.utilities.BoraTechApis.login("helenhjahn@gmail.com", "06102021");		
		String endpoint = "api/posts";
		
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("x-auth-token", token);
		
		Response response = request.get(endpoint);
		
		JsonPath jp = response.jsonPath();
		
		List<NewPost> newPosts = jp.getList("text"  + helen.utilities.Keywords.getTimeStamp(), NewPost.class);


		//validate
		boolean found = false;
		for (NewPost actualNewPost : newPosts) {
			if (expectedNewPost.equals(actualNewPost)) {
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

}
