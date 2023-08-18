package helen.utilities;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BoraTechApis {
	
	public static String login(String email, String password) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		HashMap<String, String> body = new HashMap<>();
		body.put("email", "muradil.erkin@boratechschool.com");
		body.put("password", "Boratech");

		request.body(body);
		request.header("Content-Type", "application/json");

		Response response = request.post(endpoint);
		
		JsonPath jp = response.jsonPath();
		String token = jp.get("token");
		return token;
		
	}
	
	
	
	public static String getAuthorizedUserMeta(String token) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("x-auth-token", token);
		
		Response response = request.post(endpoint);	
		JsonPath jp = 
		return response.jsonPath().get("name"));
		
	}
	
	
	
}
