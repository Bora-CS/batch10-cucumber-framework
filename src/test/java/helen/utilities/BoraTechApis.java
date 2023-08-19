package helen.utilities;

import java.util.HashMap;

import com.google.gson.Gson;

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
		
		JsonPath jp = response.jsonPath();  
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
		
		request.body(exp.pojoToJason());
		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");

		Response response = request.put(endpoint);

		System.out.println(exp.pojoToJason());
//		Object result = response.body().jsonPath().get("experience");
//		System.out.println(result);
		
	}



	public static void addEducation(String token, Education edu) {
		String endpoint = "/api/profile/education";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.body(edu.pojoToJason());
		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");

		Response response = request.put(endpoint);
	
		System.out.println(edu.pojoToJason());
//		Object result = response.body().jsonPath().get("education");
//		System.out.println(result);
	}

}
