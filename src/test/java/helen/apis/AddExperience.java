package helen.apis;

import helen.apiPojos.Experience;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddExperience {

	public static void main(String[] args) {

		String token = helen.utilities.BoraTechApis.login("helenhjahn@gmail.com", "06102021");
		String endpoint = "api/profile/experience";
		
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.header("x-auth-token", token);

		Experience exp = new Experience("Test Automation Engineer", "Bora Tech", "Annandale,VA",
				"05/01/2022", "10/01/2022", false, "Build and maintain an automation framework for the software using Selenium.");

		request.body(exp);

		Response response = request.put(endpoint);
		System.out.println(response.body().asPrettyString());
		
	}

}
