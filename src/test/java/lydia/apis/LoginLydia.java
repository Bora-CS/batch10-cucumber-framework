package lydia.apis;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginLydia {

	public static void main(String[] args) {

		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		HashMap<String, String> body = new HashMap<>();
		body.put("email", "w.lydia.liu@gmail.com");
		body.put("password", "Liu123456");

		request.body(body);
		request.header("Content-Type", "application/json");

		Response response = request.post(endpoint);
		
		JsonPath jp = response.jsonPath();
		String token = jp.get("token");
	
		System.out.println(response.statusLine());
		System.out.println(response.getStatusCode());
		System.out.println(response.body().asString());
		System.out.println(response.body().asPrettyString());

		// Validation
		int actualStatusCode = response.statusCode();
		int expectedStatusCode = 200;

		if (actualStatusCode != expectedStatusCode) {
			System.out.println("Failed , expected :" + expectedStatusCode + "actual: " + actualStatusCode);
		}
		String responseBody = response.body().asString();
		if (!responseBody.contains("token")) {
			System.out.println("Failed, token not found");
		}
	}
	

}
