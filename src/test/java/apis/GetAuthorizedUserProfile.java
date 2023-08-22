package apis;

import java.util.List;
import apiPojos.Experience;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.BoraTechAPIs;

public class GetAuthorizedUserProfile {

	public static void main(String[] args) {

		String token = BoraTechAPIs.login("muradil.erkin@boratechschool.com", "Boratech");

		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();
		request.header("x-auth-token", token);
		Response response = request.get("/api/profile/me");

		JsonPath jp = response.jsonPath();

		List<Experience> experiences = jp.getList("experience", Experience.class);
		for (Experience experience : experiences) {
			System.out.println(experience.company);
		}

	}

}
