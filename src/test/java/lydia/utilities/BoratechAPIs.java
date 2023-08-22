package lydia.utilities;

import java.util.HashMap;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lydia.POJO.Education;
import lydia.POJO.Experience;

public class BoratechAPIs {
	
	public static String Login(String email, String password) {
		
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();
		
		HashMap<String, String> body =  new HashMap<>();
		body.put("email", email);
		body.put("password", password);
		
		request.body(body);
		request.header("Content-Type", "application/json");
		
		Response response = request.post(endpoint);
		
		JsonPath jp = response.jsonPath();
		return jp.get("token");//return string token
		
	}
	
	public static String getAuthorizedUserMeta(String token) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();
		
		request.header("x-auth-token", token);
		
		Response response = request.get(endpoint);
		JsonPath jp = response.jsonPath();
		return jp.get("name");//return string name
		
	}
	public static List<Education> addEducation(String token, Education body) {
		String endpoint = "/api/profile/education";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();
		
		request.header("x-auth-token",token);
		
		request.body(body);
		
		Response response = request.put(endpoint);
		JsonPath jp = response.jsonPath();
		
		List<Education> education = jp.getList("education", Education.class);
	
		return education;
		
	}
	public static List<Experience> addExperience (String token, Experience body){
		String endpoint = "/api/profile/experience";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();
		
		request.header("X-Auth-Token", token);
		
		request.body(body);
		
		Response response = request.put(endpoint);
		JsonPath jp = response.jsonPath();
		
		List<Experience> experience = jp.getList("experience", Experience.class);
		return experience;
	}

	
}

	