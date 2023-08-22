package Nafisa_apis;

import java.util.ArrayList;
import java.util.List;

import apiPojos.Experience;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.BoraTechAPIs;

public class GetAuthorizedUserProfile {

	public static void main(String[] args) {
		String token = BoraTechAPIs.login("nanfeise@gmail.com", "Nafisa1996");
		
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

	
		request.header("x-auth-token", token);
		

		Response response = request.get("/api/profile/me");
		JsonPath jp = response.jsonPath();
		String userName = jp.get("user.name");
		System.out.println(userName);
		
		ArrayList<String> skills = jp.get("skills");
		System.out.println(skills);
		
		List<Experience> experiences = jp.getList("experience", Experience.class);
		for (Experience experience : experiences) {
			System.out.println(experience.company);


	}

}}
