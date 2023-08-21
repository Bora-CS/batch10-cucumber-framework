package apis;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import apiPojos.Experience;
import utilities.BoraTechAPIs;

public class AddExperienceTest {

	public static void main(String[] args) {

		String token = BoraTechAPIs.login("muradil.erkin@boratechschool.com", "Boratech");

		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.header("x-auth-token", token);

		Experience experience = new Experience("BoraTech", "Software Engineer", "Annandale", "2023-08-16",
				"", true, "This is a new experience");

		request.body(experience);

		Response response = request.put("/api/profile/experience");
		System.out.println(response.body().asPrettyString());

	}

}
