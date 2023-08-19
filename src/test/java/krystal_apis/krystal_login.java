package krystal_apis;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class krystal_login {

	public static void main(String[] args) {
	
		
	String endpoint = "/api/auth";
	RestAssured.baseURI ="https://boratech-practice-app.onrender.com/";
	RequestSpecification request = RestAssured.given();
	
	HashMap<String, String> body = new HashMap<>();
	body.put("email","krystal.lee.om@gmail.com");
	body.put("password", "123456");
	
	request.body(body);
	
	request.header("content-Type", "application/json");
	
	Response response = request.post(endpoint);
	System.out.println(response.body().asPrettyString());
		
	

	}

}
