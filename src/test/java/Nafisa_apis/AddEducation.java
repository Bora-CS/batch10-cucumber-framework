package Nafisa_apis;


import apiPojos.Education;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.BoraTechAPIs;

public class AddEducation {

	public static void main(String[] args) {
String token = BoraTechAPIs.login("nanfeise@gmail.com", "Nafisa1996");
		
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.header("x-auth-token", token);
		
		Education education = new Education("Boratech", "Test automation", "QA tester", "2023/05/06","", true,
				"code everyday");
				request.body(education);

		Response response = request.put("/api/profile/education");
		System.out.println(response.body().asPrettyString());


	}

}
