package anthony;

import java.util.HashMap;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API_BoraLogin {

	public static void main(String[] args) {

		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		HashMap<String, String> body = new HashMap<>();
		body.put("email", "anth0ny@gmail.com");
		body.put("password", "PaSsWoRd123!");

		request.body(body);
		request.header("Content-Type", "application/json");

		Response response = request.post(endpoint);
		int actualStatusCode = response.statusCode();
		int expectedStatusCode = 200;

		if (actualStatusCode != expectedStatusCode) {
			System.out.println("Test failed, expected: " + expectedStatusCode + " actual: " + actualStatusCode);
		} 
			System.out.println("Test passed.");
		}

	}


