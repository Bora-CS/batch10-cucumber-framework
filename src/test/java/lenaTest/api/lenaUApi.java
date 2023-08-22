package lenaTest.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import apiPojos.User;
import hui_automation.api_pojos.Post;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lenaTest.pojo.PostPojo;
import lydia.utilities.Keywords;

public class lenaUApi {

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

		JsonPath jp = response.jsonPath();
		String token = jp.get("token");
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

	public static void addPost(String token) throws Exception {

		String endpoint = "/api/posts";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();
		PostPojo body = new PostPojo("Lena");

		request.body(body);
		request.header("X-Auth-Token", token);
		request.header("Content-Type", "application/json");

		Response response = request.post(endpoint);

		if (response.getStatusCode() != 200) {
			throw new Exception("Add post failed: " + response.getStatusLine());

		} else {

			System.out.println("Pass");
		}

	}

	public static void getPost(String token) throws Exception {
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		String endpoint = "/api/posts";
		RequestSpecification request = RestAssured.given();

		request.header("X-Auth-Token", token);
		Response response = request.get(endpoint);

		
		System.out.println(response.jsonPath().get("text").toString());

	}
}