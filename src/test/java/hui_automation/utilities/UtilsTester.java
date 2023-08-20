package hui_automation.utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import hui_automation.pojos.Education;
import hui_automation.pojos.Experience;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UtilsTester {

	public static void main(String[] args) {
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";
		try {
			String token = BoraTechAPIs.login(email, password);
//			putEducation(token, new Education("Fake School", "Fake Degree", "Fake Major", "2010/10/10", "2014/04/04",
//					false, "Party all day long.", null));
//			putExperience(token, new Experience("Fake Company", "Fake Position", "Fake Location", "2024/12/23", "",
//					true, "Eat peanut butter cookies all day along", null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void putExperience(String token, Experience exp) throws Exception {
		String endpoint = "/api/profile/experience";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		// setting request
		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");
		request.body(exp.toHashMap());

		// return response
		Response response = request.put(endpoint);

		if (response.getStatusCode() != 200)
			throw new Exception("Add experience failed: " + response.getStatusLine());
		List<HashMap<String, Object>> expList = response.jsonPath().getList("experience");
		for (HashMap<String, Object> expX : expList) {
			for (Entry<String, Object> entry : expX.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
			System.out.println();
		}
	}

	public static void putEducation(String token, Education edu) throws Exception {
		String endpoint = "/api/profile/education";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		// setting request
		request.header("x-auth-token", token);
		request.header("Content-Type", "application/json");
		request.body(edu.toHashMap());

		// return response
		Response response = request.put(endpoint);

		if (response.getStatusCode() != 200)
			throw new Exception("Add education failed: " + response.getStatusLine());
		List<HashMap<String, Object>> eduList = response.jsonPath().getList("education");
		for (HashMap<String, Object> eduX : eduList) {
			for (Entry<String, Object> entry : eduX.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}
			System.out.println();
		}
	}

}
