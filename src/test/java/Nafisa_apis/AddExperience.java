package Nafisa_apis;

import apiPojos.Experience;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.BoraTechAPIs;

public class AddExperience {

	public static void main(String[] args) {
		String token = BoraTechAPIs.login("nanfeise@gmail.com", "Nafisa1996");
		
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.header("x-auth-token", token);
		
		Experience experience = new Experience("Jpetal", "Manager", "Harrisonburg,VA", "2019-02-01",
				"", true, "This is a new experience");

		request.body(experience);

		Response response = request.put("/api/profile/experience");
		System.out.println(response.body().asPrettyString());

	}

}


	


