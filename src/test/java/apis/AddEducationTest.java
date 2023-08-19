package apis;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.BoraTechAPIs;

public class AddEducationTest {

	public static void main(String[] args) {

		String token = BoraTechAPIs.login("muradil.erkin@boratechschool.com", "Boratech");

		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.header("x-auth-token", token);

		JsonObject body = new JsonObject();
		body.addProperty("school", "BoraTech");
		body.addProperty("degree", "Certified Coffee Drinker");
		body.addProperty("fieldofstudy", "Coffee Drinking");
		body.addProperty("from", "2023-08-01");
		body.addProperty("to", "");
		body.addProperty("current", true);
		body.addProperty("description", "Drinking all sorts of coffee");

		request.body(body);

		Response response = request.put("/api/profile/education");
		System.out.println(response.body().asPrettyString());

	}

}
