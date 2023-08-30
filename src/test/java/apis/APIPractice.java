package apis;

import java.util.HashMap;

import org.apache.commons.collections4.map.HashedMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIPractice {
	
	public static void main(String[] args) {
		loginBora();
	}
	
	
	
	
	
	public static void loginBora() {
		
		// Send a request
			// set up request object and set BaseURI
		RestAssured.baseURI="https://boratech-practice-app.onrender.com/";
		RequestSpecification request = RestAssured.given();
			// add header and body
		request.headers("Content-Type","application/json");
		
		HashMap<String, String> body = new HashMap<String, String>();
		body.put("email", "muradil.erkin@boratechschool.com");
		body.put("password", "Boratech");
		
		request.body(body);
			// Do an API call with endPoint
			// set up resposne object and init
		Response resp = request.post("api/auth");
		// BaseURI + () -> API URL

		
		// Get a response

			// get status code
		int code =	resp.getStatusCode();
		System.out.println("API Repsonse status code: "+code);
			// get response body
		System.out.println("print body asString: "+resp.getBody().asString()+"\n");
		System.out.println("print body asPrettyString: "+resp.getBody().asPrettyString());
		
		JsonPath path = resp.jsonPath();
		String token = path.get("token");
		
//		resp.jsonPath().get("token");

		System.out.println("The attribute token is: "+token);
	}
	
	
	
	
	
	
	
	
	
	
	

}
