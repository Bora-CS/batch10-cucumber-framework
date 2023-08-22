package anthony;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

public class API_BoraPostStatus {

	public static void main(String[] args) {
		String endpoint = "/api/posts";
		String token = UTILITIES.login("anth0ny@gmail.com", "PaSsWoRd123!");

		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();
		//request.header("Content-Type", "application/json");
		request.header("x-auth-token", token);

		Response response = request.get(endpoint);

		int actualStatusCode = response.statusCode();
		int expectedStatusCode = 200;

		if (actualStatusCode != expectedStatusCode) {
			System.out.println("Test failed, expected: " + expectedStatusCode + " actual: " + actualStatusCode);
		} else {
			System.out.println("Status code matched!");
		}
	}
}
