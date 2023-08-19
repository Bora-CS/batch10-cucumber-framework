package lenaTest.Api;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Api_Logging {

	public static void main(String[] args) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		HashMap<String, String> body = new HashMap<>();
		body.put("email", "chenlena@outlook.com");
		body.put("password", "798911");

		request.body(body);
		request.header("Content-Type", "application/json");
		Response response = request.post(endpoint);
		System.out.println(response.getStatusCode());

	}
}
