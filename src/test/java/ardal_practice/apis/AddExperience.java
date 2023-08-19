package ardal_practice.apis;

import java.util.ArrayList;
import java.util.HashMap;

import ardal_practice.pojo.BoraTechAPIs;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddExperience {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		HashMap<String, String> infos = new HashMap<>();
		infos.put("company", "Salesforce");
		infos.put("title", "Salesman");
		infos.put("location", "Vancouver,Canada");
		infos.put("from", "2023-08-01");
		infos.put("to", "");
		infos.put("current", String.valueOf(true));
		infos.put("description", "Such a easy job!");

		request.body(infos);

		request.header("Content-Type", "application/json");

		String xAuthToken = BoraTechAPIs.login("ardal002713@gmail.com", "ardal123");

		request.header("X-Auth-Token", xAuthToken);

		Response response = request.put("/api/profile/experience");

		int actualStatusCode = response.statusCode();
		int expectedStatusCode = 200;

		if (actualStatusCode != expectedStatusCode) {
			System.out
					.println("Test failed expected code: " + expectedStatusCode + "\nactual code: " + actualStatusCode);
		}

		JsonPath jp = response.jsonPath();

		ArrayList<Object> experiences = new ArrayList<>();
		experiences = jp.get("experience");

		System.out.println(experiences.contains(infos));
		for (Object experience : experiences) {
			System.out.println(experience.toString());
		}
		System.out.println(experiences);

		System.out.println("Test passed");

	}

}
