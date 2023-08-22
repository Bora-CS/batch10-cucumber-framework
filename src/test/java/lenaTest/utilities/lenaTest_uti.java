package lenaTest.utilities;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class lenaTest_uti {

	public static String logging(String email, String password) {
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

	public static String getMeta(String token) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("x-auth-token", token);

		Response response = request.get(endpoint);

		String data = response.getBody().asPrettyString();
		JsonPath jp = response.jsonPath();
		return jp.get("name");

	}

	public static void addEducation(String token) {
		String endpoint = "/api/profile/education";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();
		request.header("x-auth-token", token);
		HashMap<String, String> body = new HashMap<String, String>();
		body.put("school", "aa");
		body.put("degree", "aa");
		body.put("fieldofstudy", "aa");
		body.put("from", "aa");
		body.put("description", "aa");
//		{
//		    "school": "ffc",
//		    "degree": "cccc",
//		    "fieldofstudy": "ccc",
//		    "from": "1995-07-01",
//		    "to": "",
//		    "current": true,
//		    "description": "ffffff"
//		}
		request.body(body);
		Response response = request.put(endpoint);

		System.out.println(response.getStatusLine());

	}

}
