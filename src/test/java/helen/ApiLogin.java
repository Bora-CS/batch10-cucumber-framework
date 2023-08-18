package helen;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiLogin {

	public static void main(String[] args) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		HashMap<String, String> body = new HashMap<>();
		body.put("email", "muradil.erkin@boratechschool.com");
		body.put("password", "Boratech");

		request.body(body);
		request.header("Content-Type", "application/json");

		Response response = request.post(endpoint);
		
		//System.out.println(response.getStatusLine());
		//System.out.println(response.getStatusCode());
		//System.out.println(response.body().asString());
		//System.out.println(response.body().asPrettyString());

		
		//validation
		int actualStatusCode = response.statusCode();
		int expectedStuatusCode = response.statusCode();
		
		if (actualStatusCode != expectedStuatusCode) {
			System.out.println("Failed, expected: " + expectedStuatusCode + "actual: " + actualStatusCode);
		}
		
		String responseBody = response.body().asString();
		if (!responseBody.contains("token")) {
			System.out.println("Failed, token not found");
		}
		
	}
}
