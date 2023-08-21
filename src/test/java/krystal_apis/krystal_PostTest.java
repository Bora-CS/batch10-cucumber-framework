package krystal_apis;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lydia.utilities.Keywords;

public class krystal_PostTest {

	public static void main(String[] args) {

		String token = krystal_BoraTechAPIs2.krystal_login2("kyrstal.lee.om@gmail.com", "123456");

		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.header("x-auth-token", token);

		krystal_PojoTask expectedPojoTask = new krystal_PojoTask("64e122fa1934da003f5fb4f2",
				"yay!" + Keywords.getTimeStamp(), "Krystal Om",
				"//www.gravatar.com/avatar/12fdd360c5dded4f841f0095c2d431ad?s=200&r=pg&d=mm",
				"64c878e78241d1003f764299", "[]", "[]", "2023-08-19T20:15:54.142Z", "0");

		request.body(expectedPojoTask);

		Response response = request.get("/api/posts");
		List<krystal_PojoTask> pojoTask = response.jsonPath().getList("krystal_PojoTask", krystal_PojoTask.class);

		boolean found = false;
		for (krystal_PojoTask actualPost : pojoTask) {
			if (expectedPojoTask.equals(actualPost)) {
				found = true;
				break;

			}
		}

		if (found) {
			System.out.println("pass");
		} else {
			System.out.println("Fail");
		}
	}
}
