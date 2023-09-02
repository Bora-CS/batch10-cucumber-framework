package utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

import apiPojos.ApiError;
import apiPojos.Education;
import apiPojos.Experience;
import apiPojos.LoginRequest;
import apiPojos.Post;
import apiPojos.PostRequest;
import apiPojos.User;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BoraTechAPIs {

	private static Response loginBase(String email, String password) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		LoginRequest body = new LoginRequest(email, password);

		request.body(body);
		request.header("Content-Type", "application/json");

		Response response = request.post(endpoint);
		return response;
	}

	public static String login(String email, String password) {
		Response response = loginBase(email, password);
		assertEquals(200, response.statusCode());

		JsonPath jp = response.jsonPath();
		String token = jp.get("token");

		assertNotNull(token);
		return token;
	}

	public static String loginUnhappy(String email, String password) {
		Response response = loginBase(email, password);
		assertEquals(400, response.statusCode());

		JsonPath jp = response.jsonPath();
		String msg = jp.get("errors[0].msg");
		assertNotNull(msg);

		return msg;
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

		PostRequest postBody = new PostRequest(text, false);

		request.body(postBody);

		Response response = request.post(endpoint);
		assertEquals(200, response.statusCode());

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

//	TODO: update the return type to Profile pojo
	public static List<Experience> addExperience(String token, Experience experience) {
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.header("x-auth-token", token);
		request.body(experience);

		Response response = request.put("/api/profile/experience");
		assertEquals(200, response.statusCode());

		List<Experience> experiences = response.jsonPath().getList("experience", Experience.class);
		assertTrue(experiences.size() > 0, "No experiences received");
		return experiences;
	}

	public static List<ApiError> addEducationUnhappy(String token, Education education) {
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.header("x-auth-token", token);

		request.body(education);

		Response response = request.put("/api/profile/education");
		assertEquals(400, response.getStatusCode());

		List<ApiError> errors = response.jsonPath().getList("errors", ApiError.class);
		assertTrue(errors.size() > 0, "Expected to received at least 1 error, none received.");
		return errors;
	}

}
