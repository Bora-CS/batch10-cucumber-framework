package krystal_apis;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class krystal_BoraTechAPIs2 {

	public static String krystal_login2(String email, String password) {

		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com/";
		RequestSpecification request = RestAssured.given();

		HashMap<String, String> body = new HashMap<>();
		body.put("email", email);
		body.put("password", password);

		request.body(body);
		request.header("content-Type", "application/json");
		

		Response response = request.post(endpoint);

		JsonPath jp = response.jsonPath();
		String token = jp.get("token");
		return token;

	}

	public static void getAuthorizedUserMeta(String token) {

		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com/";
		RequestSpecification request = RestAssured.given();

		request.header("x-auth-token", token);

		Response response = request.get(endpoint);

		System.out.println(response.body().asPrettyString());
	}
}