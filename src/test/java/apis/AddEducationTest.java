package apis;

import java.util.List;

import apiPojos.Education;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.BoraTechAPIs;
import utilities.Keywords;

public class AddEducationTest {

	public static void main(String[] args) {

		String token = BoraTechAPIs.login("muradil.erkin@boratechschool.com", "Boratech");

		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.header("x-auth-token", token);

		Education expectedEducation = new Education("BoraTech" + Keywords.getTimeStamp(), "Certified Coffee Drinker",
				"Coffee Drinking", "2023-08-01", "", true, "Drinking all sorts of coffee");

		request.body(expectedEducation);

		Response response = request.put("/api/profile/education");
		List<Education> educations = response.jsonPath().getList("education", Education.class);

		boolean found = false;
		for (Education actualEducation : educations) {
			if (expectedEducation.equals(actualEducation)) {
				found = true;
				break;
			}
		}

		if (found) {
			System.out.println("Pass");
		} else {
			System.out.println("Fail");
		}

	}

}
