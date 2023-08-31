package utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

import apiPojos.Post;
import apiPojos.PostBody;
import apiPojos.User;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BoraTechAPIs {

	public static String login(String email, String password) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		HashMap<String, String> body = new HashMap<>();
		body.put("email", email);
		body.put("password", password);

		request.body(body);
		request.header("Content-Type", "application/json");

		Response response = request.post(endpoint);

		assertEquals(200, response.statusCode());

		JsonPath jp = response.jsonPath();
		String token = jp.get("token");

		assertNotNull(token);
		return token;
	}

	public static User getAuthorizedUserMeta(String token) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("x-auth-token", token);

		Response response = request.get(endpoint);
		User user = response.as(User.class);

		return user;
	}

	public static Post createPost(String token, String text) {
		String endpoint = "/api/posts";

		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");

		PostBody postBody = new PostBody(text, false);

		request.body(postBody);

		Response response = request.post(endpoint);
		Post post = response.as(Post.class);
		return post;
	}

	public static List<Post> getPosts(String token) {
		String endpoint = "/api/posts";

		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("x-auth-token", token);
		Response response2 = request.get(endpoint);

		List<Post> posts = response2.jsonPath().getList("", Post.class);
		return posts;
	}

}
