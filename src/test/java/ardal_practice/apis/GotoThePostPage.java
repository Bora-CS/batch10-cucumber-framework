package ardal_practice.apis;

import ardal_practice.pojo.BoraTechAPIs;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GotoThePostPage {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();
		String token = BoraTechAPIs.login("ardal002713@gmail.com", "ardal123");
		request.header("x-auth-token", token);

		Response response = request.get("/api/posts");

		int actualStatusCode = response.statusCode();
		int expectedStatusCode = 200;

		if (expectedStatusCode != actualStatusCode) {
			System.out.println("Test failed status code doesn;t match, Expected: " + expectedStatusCode + "\nActual: "
					+ actualStatusCode);
		}
		
		System.out.println("Test passed");

	}

}
