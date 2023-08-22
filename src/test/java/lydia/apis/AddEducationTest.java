package lydia.apis;


import java.util.List;

import io.restassured.RestAssured;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import lydia.POJO.EducationAPI;

import lydia.utilities.BoratechAPIs;
import lydia.utilities.Keywords;


public class AddEducationTest {

	public static void main(String[] args) {
		String token = BoratechAPIs.Login("w.lydia.liu@gmail.com", "Liu123456");
		
		//String userName = BoratechAPIs.getAuthorizedUserMeta(token);
		//System.out.println("Username: " + userName);
		EducationAPI body = new EducationAPI("BoraTe" + Keywords.getTimeStamp(), "Certificate", "Test Automation Engineer", "05/06/2023", "", true, "Learning to automate");

		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();
		
		request.header("x-auth-token",token);
		request.header("Content-Type", "application/json");
		
		request.body(body);
		Response response = request.put("/api/profile/education");
		
		
		List<EducationAPI> educations = response.jsonPath().getList("education",EducationAPI.class);
		
		boolean found = false;
		for (EducationAPI education : educations) {
			if(education.degree.equals(body.degree) && education.school.equals(body.school)) {
				found = true;
				break;
			}
		}
		if(found) {
			System.out.println("test passed");
		}else {
			System.out.println("test failed");
		}
	}

}
		
		
