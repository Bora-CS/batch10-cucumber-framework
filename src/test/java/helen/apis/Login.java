package helen.apis;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Login {

	public static void main(String[] args) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		
		HashMap<String, String> body = new HashMap<>();  //sending as json data
		body.put("email", "helenhjahn@gamil.com");  //map interface hashmap class implements map . class can only make object
		body.put("password", "06102021");

		request.body(body);
		
		Response response = request.post(endpoint);  //gson to convert
		
		//System.out.println(response.getStatusLine());
		//System.out.println(response.getStatusCode());
		//System.out.println(response.body());   //hashcode
		//System.out.println(response.body().asString());
		System.out.println(response.body().asPrettyString());
		JsonPath jp = response.jsonPath();
		String token = jp.getString("token");
		System.out.println("Token: " + token);
		//extract specific path
		
		//validation
		int actualStatusCode = response.statusCode();
		int expectedStuatusCode = 200;
		
		if (actualStatusCode != expectedStuatusCode) {
			System.out.println("Test Failed, expected: " + expectedStuatusCode + " actual: " + actualStatusCode);
		}
		
		String responseBody = response.body().asString();
		if (!responseBody.contains("token")) {
			System.out.println("Test Failed, token not found");	
			
		}
		//System.out.println("Test Paseed");
		
	}
}
