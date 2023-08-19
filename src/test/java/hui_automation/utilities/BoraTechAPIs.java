package hui_automation.utilities;

import java.util.HashMap;
import java.util.List;

import hui_automation.api_pojos.Education;
import hui_automation.api_pojos.Experience;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BoraTechAPIs {

	public static String login(String email, String password) throws Exception {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		HashMap<String, String> body = new HashMap<>();
		body.put("email", email);
		body.put("password", password);

		request.body(body);
		request.header("Content-Type", "application/json");

		Response response = request.post(endpoint);

		if (response.getStatusCode() != 200)
			throw new Exception("Login failed: " + response.getStatusLine());

		JsonPath jp = response.jsonPath();
		String token = jp.get("token");
		return token;
	}

	public static String getAuthorizedUserMeta(String token) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("x-auth-token", token);

		Response response = request.get(endpoint);
		return response.jsonPath().get("name");
	}

	public static void getUserProfile(String token) throws Exception {
		String endpoint = "/api/profile/me";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("x-auth-token", token);

		Response response = request.get(endpoint);

		if (response.getStatusCode() != 200)
			throw new Exception("User profile failed to load: " + response.getStatusLine());

		System.out.println(response.body().asPrettyString());
	}

	public static List<Experience> putExperience(String token, Experience exp) throws Exception {
		String endpoint = "/api/profile/experience";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		// setting request
		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");
		request.body(exp);

		// return response
		Response response = request.put(endpoint);

		if (response.getStatusCode() != 200)
			throw new Exception("Add experience failed: " + response.getStatusLine());
		List<Experience> experiences = response.jsonPath().getList("experience", Experience.class);
		return experiences;
	}

	public static List<Education> putEducation(String token, Education edu) throws Exception {
		String endpoint = "/api/profile/education";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		// setting request
		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");
		request.body(edu);

		// return response
		Response response = request.put(endpoint);

		if (response.getStatusCode() != 200)
			throw new Exception("Add education failed: " + response.getStatusLine());
		List<Education> educations = response.jsonPath().getList("education", Education.class);
		return educations;
	}

}
