package ardal_practice.apis;

import java.util.ArrayList;
import java.util.List;

import ardal_practice.pojo.BoraTechAPIs;
import ardal_practice.pojo.Education;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddEducation {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();
		String token = BoraTechAPIs.login("ardal002713@gmail.com", "ardal123");
		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");

		Education education = new Education("Yale", "Phd", "Medical", "2023-01-03", true, "",
				"Trying to save some lives.");

		request.body(education);

		Response response = request.put("/api/profile/education");

		JsonPath jp = response.jsonPath();

		List<Education> educations = jp.getList("education", Education.class);

		ArrayList<String> schools = new ArrayList<>();
		ArrayList<String> degrees = new ArrayList<>();

		for (Education education1 : educations) {
			schools.add(education1.school);
			degrees.add(education1.degree);
		}

		if (!schools.contains(education.school) || !degrees.contains(education.degree)) {
			System.out.println("Test failed, newly added education not found");
		}

		System.out.println("Test passed");

	}

}
