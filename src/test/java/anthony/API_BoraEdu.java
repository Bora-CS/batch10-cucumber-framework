package anthony;

import anthony_pojo.Education_API;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;

public class API_BoraEdu {

	public static void main(String[] args) {
		String token = UTILITIES.login("anth0ny@gmail.com", "PaSsWoRd123!");

		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.header("x-auth-token", token);

		Education_API expectedEducation = new Education_API("ToraBech" + UTILITIES.getTimeStamp(), "Noob Automator",
				"Noob Tester", "2023-01-01", "", true, "6+ years experience");

		request.body(expectedEducation);

		Response response = request.put("/api/profile/education");

		List<Education_API> educations = response.jsonPath().getList("education", Education_API.class);

		boolean found = false;
		for (Education_API actualEducation : educations) {
			if (expectedEducation.equals(actualEducation)) {
				found = true;
				break;
			}
		}

		if (found) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test Fail");
		}
	}
}
