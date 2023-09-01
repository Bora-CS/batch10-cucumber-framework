package hui_automation.utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hui_automation.api_pojos.Education;
import hui_automation.api_pojos.Experience;
import hui_automation.api_pojos.Post;
import hui_automation.api_pojos.Profile;
import hui_automation.api_pojos.User;
import io.restassured.RestAssured;
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
		assertEquals(200, response.getStatusCode());

		String token = response.jsonPath().get("token");
		assertNotNull(token);
		return token;
	}

	public static User getAuthUserMeta(String token) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("X-Auth-Token", token);

		Response response = request.get(endpoint);
		assertEquals(200, response.getStatusCode());

		User user = response.as(User.class);
		return user;
	}

	public static Profile getUserProfile(String token) {
		String endpoint = "/api/profile/me";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("X-Auth-Token", token);

		Response response = request.get(endpoint);
		assertEquals(200, response.getStatusCode());

		Profile userProfile = response.as(Profile.class);
		return userProfile;
	}

	public static List<Experience> putExperience(String token, Experience exp) {
		String endpoint = "/api/profile/experience";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		// setting request
		request.header("X-Auth-Token", token);
		request.header("Content-Type", "application/json");
		request.body(exp);

		// return response
		Response response = request.put(endpoint);
		assertEquals(200, response.getStatusCode());

		List<Experience> experiences = response.jsonPath().getList("experience", Experience.class);
		return experiences;
	}

	public static List<Education> putEducation(String token, Education edu) {
		String endpoint = "/api/profile/education";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		// setting request
		request.header("X-Auth-Token", token);
		request.header("Content-Type", "application/json");
		request.body(edu);

		// return response
		Response response = request.put(endpoint);
		assertEquals(200, response.getStatusCode());

		List<Education> educations = response.jsonPath().getList("education", Education.class);
		return educations;
	}

	public static Post postBoraTechPosts(String token, String postContent) {
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		String endpoint = "/api/posts";
		RequestSpecification request = RestAssured.given();

		// setting a request
		request.header("X-Auth-Token", token);
		request.header("Content-Type", "application/json");
		Map<String, String> body = new HashMap<>();
		body.put("text", postContent);
		request.body(body);

		// return a response
		Response response = request.post(endpoint);
		assertEquals(200, response.getStatusCode());

		return response.jsonPath().getObject("", Post.class);
	}

	public static List<Post> getBoraTechPosts(String token) {
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		String endpoint = "/api/posts";
		RequestSpecification request = RestAssured.given();

		// setting a request
		request.header("X-Auth-Token", token);

		// return a response
		Response response = request.get(endpoint);
		assertEquals(200, response.getStatusCode());

		List<Post> posts = response.jsonPath().getList("", Post.class);
		return posts;
	}

}
