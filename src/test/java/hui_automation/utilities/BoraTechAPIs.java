package hui_automation.utilities;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hui_automation.pojos.Education;
import hui_automation.pojos.Experience;
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

	public static void putExperience(String token, Experience exp) throws Exception {
		String endpoint = "/api/profile/experience";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		// setting request
		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");
		request.body(exp.toJsonString());

		// return response
		Response response = request.put(endpoint);

		if (response.getStatusCode() != 200)
			throw new Exception("Add experience failed: " + response.getStatusLine());

		Object x = response.body().jsonPath().get("experience");
		Gson gs = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gs.toJson(x));
	}

	public static void putEducation(String token, Education edu) throws Exception {
		String endpoint = "/api/profile/education";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		// setting request
		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");
		request.body(edu.toJsonString());

		// return response
		Response response = request.put(endpoint);

		if (response.getStatusCode() != 200)
			throw new Exception("Add education failed: " + response.getStatusLine());

		Object x = response.body().jsonPath().get("education");
		Gson gs = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gs.toJson(x));
	}

}
