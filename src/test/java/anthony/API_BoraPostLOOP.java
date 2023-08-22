package anthony;

import anthony_pojo.Blog_Post;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class API_BoraPostLOOP {
	public static void main(String[] args) {
		int loop = 3;
		String token = UTILITIES.login("anth0ny@gmail.com", "PaSsWoRd123!");

		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.header("x-auth-token", token);

		for (int i = 0; i < loop; i++) {
			Blog_Post post = new Blog_Post("Does this loop work?" + UTILITIES.getTimeStamp());

			request.body(post);

			io.restassured.response.Response postResponse = request.post("/api/posts");

			System.out.println("Response Status Code: " + postResponse.getStatusCode());
			System.out.println("Response Body: " + postResponse.getBody().asString());

			try {
				UTILITIES.wait(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
