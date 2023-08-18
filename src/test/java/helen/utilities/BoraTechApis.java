package helen.utilities;

import java.util.HashMap;

import helen.pojo.Education;
import helen.pojo.Experience;
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
		body.put("email", "helenhjahn@gmail.com");
		body.put("password", "06102021");

		request.body(body);
		request.header("Content-Type", "application/json");

		Response response = request.post(endpoint);
		
		JsonPath jp = response.jsonPath();  //get jsonpath object, extract token out of response
		String token = jp.get("token");
		return token;
		
	}
	
	
	
	public static String getAuthorizedUserMeta(String token) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("x-auth-token", token);  // no body
		
		Response response = request.get(endpoint);	
		
		//return response.jsonPath().get("name");
		return response.jsonPath().get("email");
		
	}

	public static void addExperience(String token, Experience exp) {
		String endpoint = "/api/profile/experience";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.body(exp);
		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");

		Response response = request.put(endpoint);
	
		Object jp = response.body().asPrettyString();
		System.out.println(jp);
		 
	}



	public static void addEducation(String token, Education edu) {
		String endpoint = "/api/profile/education";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.body(edu);
		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");

		Response response = request.put(endpoint);
	
		Object jp = response.body().asPrettyString();
		System.out.println(jp);
	}

}
