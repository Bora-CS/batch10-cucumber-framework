package lydia.apis;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;


import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lydia.POJO.UserAPI;
import lydia.utilities.BoratechAPIs;
import lydia.utilities.Keywords;

public class Post_BoraTech {

	public static void main(String[] args) {
		
		String post = "{\"text\": \"I need practice more\"}";
		addPost(post);
		
		Response posts = getPost();

		JsonPath jp = posts.jsonPath();

		List<UserAPI> users = jp.getList("", UserAPI.class);
		
		for (UserAPI user : users) {
			
			if(user.name.contains("Lydia")&& user.date.contains("2023-08-22")) {
				
				System.out.println(user.text.toString());
				System.out.println(user.name.toString());
			}
		}
	
		}

	public static void addPost(String post) {
		
		String token = getToken("w.lydia.liu@gmail.com","Liu123456");
		String endpoint =  "/api/posts";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		
		RequestSpecification request = RestAssured.given();
		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");
		request.body(post);
		request.post(endpoint);
		
	}

	public static Response getPost() {
		String token = getToken("w.lydia.liu@gmail.com","Liu123456");
		String endpoint =  "/api/posts";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		
		RequestSpecification request = RestAssured.given();
		request.header("x-auth-token", token);
	
		
		return request.get(endpoint);
	}
	
	public static String getToken(String email, String password) {
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
}










