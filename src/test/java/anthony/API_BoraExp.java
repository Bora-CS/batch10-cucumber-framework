package anthony;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import anthony_pojo.Experience_API;

public class API_BoraExp {

	public static void main(String[] args) {

		String token = UTILITIES.login("anth0ny@gmail.com", "PaSsWoRd123!");

		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.header("x-auth-token", token);

		Experience_API experience = new Experience_API("ToraBech", "Noob Engineer", "Arlington", "2023-01-01", "", true,
				"i have 6 years of exp");

		request.body(experience);

		Response response = request.put("/api/profile/experience");
		System.out.println(response.body().asPrettyString());

	}

}