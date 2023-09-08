package hui_automation.rest_assured.boratech_tests;

import hui_automation.utilities.BoraTechAPIs;
import io.restassured.RestAssured;
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
			request.header("X-Auth-Token", token);

			// return a response
			Response response = request.get(endpoint);

			System.out.println(response.statusLine());
			response.body().prettyPrint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
