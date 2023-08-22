package helen.apis;

import java.util.List;
import helen.apiPojos.Experience;

import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import helen.utilities.BoraTechApis;

public class GetAuthorizedUserProfile {

	public static void main(String[] args) {
	
		String token = helen.utilities.BoraTechApis.login("helenhjahn@gmail.com", "06102021");
		
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
