package krystal_apis;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class kyrstal_addExperience {

	public static void main(String[] args) {

		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		HashMap<String, String> body = new HashMap<>();
		body.put("company", "BoraTech");
		body.put("title", "QA");
		body.put("location", "Annandale, VA");
		body.put("to", "");
		body.put("current", "true");
		body.put("description", "responsible for monitoring, inspecting and proposing"
				+ " measures to correct or improve an organization's final products in order to meet established quality standards");

		request.body(body);
		request.header("Content-Type", "application/json");

		Response response = request.put(endpoint);
		System.out.println(response.body().asPrettyString());

	}

}
