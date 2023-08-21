package hui_automation.rest_assured.boratech_tests;

import java.util.List;
import java.util.Random;

import hui_automation.api_pojos.Experience;
import hui_automation.utilities.BoraTechAPIs;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteExperienceTest {

	public static void main(String[] args) {
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";
		try {
			String token = BoraTechAPIs.login(email, password);

			// profile get request
			String endpoint = "/api/profile/me";
			RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
			RequestSpecification request = RestAssured.given();

			request.header("x-auth-token", token);

			Response response = request.get(endpoint);
			List<Experience> experiences = response.jsonPath().getList("experience", Experience.class);

			Random rand = new Random();
			int count = rand.nextInt(experiences.size());
			String _id = experiences.get(count)._id;
			String company = experiences.get(count).company;

			System.out.println("Company: " + company);

			// delete request
			endpoint = "/api/profile/experience/" + _id;
			response = request.delete(endpoint);

			// validation
			experiences = response.jsonPath().getList("experience", Experience.class);
			boolean targetFound = false;
			for (Experience experience : experiences) {
				if (experience.company.equals(company)) {
					targetFound = true;
					break;
				}
			}

			if (targetFound) {
				System.out.println("Test failed. Target company failed to be deleted");
			} else
				System.out.println("Test passed. Target company deleted.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
