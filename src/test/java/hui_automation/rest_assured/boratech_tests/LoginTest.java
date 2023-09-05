package hui_automation.rest_assured.boratech_tests;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginTest {

	public static void main(String[] args) {

		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		HashMap<String, String> body = new HashMap<>();
		body.put("email", "hui-pretender@outlook.com");
		body.put("password", "Hui123456");

		request.body(body);
		request.header("Content-Type", "application/json");

		Response response = request.post(endpoint);
		int actualStatusCode = response.statusCode();
		int expectedStatusCode = 200;

		if (actualStatusCode != expectedStatusCode) {
			System.out.println("Test failed, expected: " + expectedStatusCode + ", actual: " + actualStatusCode);
		} else {
			System.out.println("Test passed.");
		}

	}

}
