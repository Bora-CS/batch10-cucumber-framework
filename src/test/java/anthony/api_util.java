package anthony;

import java.util.HashMap;

import anthony_pojo.api_pojo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class api_util {
	public static String login(String email, String password) {
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

	public static api_pojo getAuthorizedUserMeta(String token) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("x-auth-token", token);

		Response response = request.get(endpoint);
		api_pojo user = response.as(api_pojo.class);

		return user;
	}

}