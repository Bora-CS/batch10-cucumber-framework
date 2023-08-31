package apis;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIPractice {
	public static void main(String[] args) {
		loginBora();
	}

	public static void loginBora() {

		// set up base URI->URL
		// set up request object and set baseURI
//		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		// set up header/body
		request.headers("Content-Type","application/json");
		
		
		HashMap<String, String> body = new HashMap<String, String>();
	
		body.put("email", "rubenmendozabri@gmail.com");
		body.put("password", "12345qwerT!");
		
		
		request.body(body);
	
		// do an API call with end point
	

		// get response

		Response resp = request.post("https://boratech-practice-app.onrender.com/api/auth");
		
		// get status code

		int code = resp.getStatusCode();
//		System.out.println("API STATUS CODE BE : " + code);
		//get response body
		String token = resp.jsonPath().get("token");
		System.out.println("the token be: "+ token);
	
	}
}
