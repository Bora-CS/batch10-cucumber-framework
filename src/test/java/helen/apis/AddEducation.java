package helen.apis;

import java.util.List;

import helen.apiPojos.Education;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddEducation {

	public static void main(String[] args) {
		
		String token = helen.utilities.BoraTechApis.login("helenhjahn@gmail.com", "06102021");		
		String endpoint = "/api/profile/education";
		
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");  
		
		Education expectedEducation = new Education("Boratech" + helen.utilities.Keywords.getTimeStamp(), "Certified Test Automation Engineer", "Certified Test Automation Engineer", "05/01/2022", "10/30/2022", false, "Learning all about Test Automation.");
		
		request.body(expectedEducation);
		
		Response response = request.put(endpoint);
		
		JsonPath jp = response.jsonPath();
		List<Education> educations = jp.getList("education", Education.class); //specific path, convert into Education object
		
		
		//validate
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
