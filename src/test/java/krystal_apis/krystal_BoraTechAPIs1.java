package krystal_apis;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class krystal_BoraTechAPIs {

	public static String Experience(String company, String title, String location, String from, String to,
			String current, String descrpition) {

		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		HashMap<String, String> body = new HashMap<>();
		body.put("company", company);
		body.put("title", title);
		body.put("location", location);
		body.put("from", from);
		body.put("to", to);
		body.put("current", current);
		body.put("description", descrpition);

		request.body(body);
		request.header("Content-Type", "application/json");

		Response response = request.put(endpoint);
		JsonPath jp = response.jsonPath();
		String token = jp.get("token");

		return token;

	}

	public static void kyrstal_login() {

		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com/";
		RequestSpecification request = RestAssured.given();

		HashMap<String, String> body = new HashMap<>();
		body.put("email", "krystal.lee.om@gmail.com");
		body.put("password", "123456");

		request.body(body);

		request.header("content-Type", "application/json");

		Response response = request.post(endpoint);
		System.out.println(response.body().asPrettyString());

	}

	public static void kyrstal_addExperience() {

		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		HashMap<String, String> body = new HashMap<>();
		body.put("company", "BoraTech");
		body.put("title", "QA");
		body.put("location", "Annandale, VA");
		body.put("to", "");
		body.put("current", "true");
		body.put("description", "responsible for monitoring, inspecting and proposing"
				+ " measures to correct or improve an organization's final products in order to meet established quality standards");

		request.body(body);
		request.header("Content-Type", "application/json");

		Response response = request.put(endpoint);
		System.out.println(response.body().asPrettyString());

	}

}
