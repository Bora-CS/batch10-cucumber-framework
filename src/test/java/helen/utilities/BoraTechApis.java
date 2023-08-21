package helen.utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import helen.apiPojos.User;
import helen.apiPojos.Education;
import helen.apiPojos.Experience;
import helen.apiPojos.NewPost;
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
	
	
	
	public static User getAuthorizedUserMeta(String token) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("x-auth-token", token); 
		
		Response response = request.get(endpoint);
		//return response.jsonPath().get("name");
		User user = response.as(User.class);
		return user;
		
	}
	

	public static void addExperience(String token, Experience exp) {
		String endpoint = "/api/profile/experience";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();
		
		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");
		request.body(exp);
		
		Response response = request.put(endpoint);

		//System.out.println(exp.pojoToJason());
		List <Experience> experience = response.jsonPath().getList("experience", Experience.class);
		System.out.println(experience);
		
	}



	public static void addEducation(String token, Education edu) {
		String endpoint = "/api/profile/education";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");  //sending body data
		request.body(edu);
		
//		
//		JsonObject body = new JsonObject();
//		body.addProperty("school", "BoraTech");
//		body.addProperty("degree", "STED");
//		body.addProperty("current", true);
//		
		Response response = request.put(endpoint);
		List <Education> education = response.jsonPath().getList("education", Education.class);
		System.out.println(education);
//		Object result = response.body().get("education");
//		System.out.println(result);
	}

	
	
	public static Object addNewPost (String token, Object ob) {
		//add new post
		String endpoint = "api/posts";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		//request
		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json"); 
		
		Map <String, String> body = new HashMap<>();
		body.put("text", ob + helen.utilities.Keywords.getTimeStamp());		
		
		//response back
		request.body(body);
		Response response = request.post(endpoint);
		
		//return a json object
		List <NewPost> np = response.jsonPath().getList("", NewPost.class);
		
		return np;
		
	}

	
	
}
