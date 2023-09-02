package rubenTrials;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apiPojos.Experience;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.BoraTechAPIs;


public class AddExperienceAPI {
	public static void main(String[]args) {
		Map<String, String> data = DataTable.asMap();
		Experience expData = new Experience(data.get("company") + " ",
				data.get("title"), data.get("location"), data.get("from"), data.get("to"),
				Boolean.parseBoolean(data.get("current")), data.get("description"));
		List<Experience> allExperiences = BoraTechAPIs.putExperience(dataManager.getToken(), expData);
	}
	 	public static List<Experience> putExperience(String token, Experience exp) {
		String endpoint = "/api/profile/experience";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		// setting request
		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");
		request.body(exp);

		// return response
		Response response = request.put(endpoint);
		assertEquals(200, response.getStatusCode());

		List<Experience> experiences = response.jsonPath().getList("experience", Experience.class);
		return experiences;
		
		
	}

}
