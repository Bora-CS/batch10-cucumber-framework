package hui_automation.rest_assured.boratech_tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import hui_automation.utilities.BoraTechAPIs;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PrintProfileJson {

	public static void main(String[] args) {
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";
		try {
			String token = BoraTechAPIs.login(email, password);
			String endpoint = "/api/profile/me";
			RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
			RequestSpecification request = RestAssured.given();

			// setting a request
			request.header("x-auth-token", token);

			// return a response
			Response response = request.get(endpoint);

			System.out.println(response.statusLine());

			String profile = response.body().asPrettyString();
			System.out.println(profile);

			if (response.getStatusCode() == 200) {
				File fileObj = new File("./src/test/java/hui_automation/rest_assured/target_json/hui_profile.json");
				FileWriter fw = new FileWriter(fileObj);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(profile);
				bw.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
