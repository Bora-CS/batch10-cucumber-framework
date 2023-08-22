package hui_automation.rest_assured.boratech_tests;

import java.util.List;

import com.google.gson.Gson;

import hui_automation.utilities.BoraTechAPIs;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ViewProfileTest {

	public static void main(String[] args) {
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";
		try {
			String token = BoraTechAPIs.login(email, password);
			String endpoint = "/api/profile/me";
			RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
			RequestSpecification request = RestAssured.given();

			// setting a request
			request.header("x-auth-token", token);

			// return a response
			Response response = request.get(endpoint);

			System.out.println(response.statusLine());
			
			// check educations
			List<Object> educations = response.body().jsonPath().get("education");
			Gson gs = new Gson();
			for (Object education : educations) {
				String eduStr = gs.toJson(education);
				String school = JsonPath.from(eduStr).get("school");
				String degree = JsonPath.from(eduStr).get("degree");
				String fieldofstudy = JsonPath.from(eduStr).get("fieldofstudy");
				System.out.println(String.format("School: %s, Degree: %s, Major: %s.", school, degree, fieldofstudy));
			}
			// check experiences
			List<Object> experiences = response.body().jsonPath().get("experience");
			for (Object experience : experiences) {
				String expStr = gs.toJson(experience);
				String company = JsonPath.from(expStr).get("company");
				String title = JsonPath.from(expStr).get("title");
				String location = JsonPath.from(expStr).get("location");
				System.out.println(String.format("Company: %s, Title: %s, Location: %s.", company, title, location));
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
