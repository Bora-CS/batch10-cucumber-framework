package ardal_practice.apis;

import java.util.List;

import ardal_practice.pojo.AddingPost;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Keywords;
import utilities.BoraTechAPIs;

public class AddingNewPost {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();
		String token = BoraTechAPIs.login("ardal002713@gmail.com", "ardal123");
		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");

		AddingPost newPost = new AddingPost("Is there anything I'am missing?" + Keywords.getTimeStamp());

		request.body(newPost);
		Response response = request.post("/api/posts");

		List<AddingPost> posts = response.jsonPath().getList("text", AddingPost.class);

		boolean found = false;
		for (AddingPost actualPost : posts) {
			if (newPost.equals(actualPost)) {
				found = true;
				break;
			}
		}

		if (found) {
			System.out.println("Test passed");
		} else {
			System.out.println("Test failed");
		}

	}

}
