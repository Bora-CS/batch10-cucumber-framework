package hui_automation.rest_assured.boratech_tests;

import java.util.List;

import hui_automation.api_pojos.Error;
import hui_automation.utilities.BoraTechAPIs;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddExperienceNegativeTest {

	public static void main(String[] args) {
		String body = "{\"company\":\"\",\"title\":\"\",\"location\":\"\",\"from\":\"\",\"to\":\"\",\"current\":false,\"description\":\"\"}";
		String uri = "https://boratech-practice-app.onrender.com/api/profile/experience";
		RequestSpecification request = RestAssured.given();
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";
		String token = BoraTechAPIs.login(email, password);
		request.header("Content-Type", "application/json");
		request.header("X-Auth-Token", token);
		request.body(body);

		Response response = request.put(uri);
		System.err.println(response.getStatusLine());
		List<Error> errors = response.jsonPath().getList("errors", Error.class);
		for (Error error : errors) {
			System.out.println(error.msg);
		}
	}

}
