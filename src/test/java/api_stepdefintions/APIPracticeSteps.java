package api_stepdefintions;

import java.util.HashMap;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIPracticeSteps {

	@Given("call login API with {string} and {string}")
	public void call_login_api_with_rubenmendozabri_gmail_com_and_12345qwer_t(String email, String password) {
		// Write code here that turns the phrase above into concrete actions
		RequestSpecification request = RestAssured.given();

		request.headers("Content-Type", "application/json");

		HashMap<String, String> body = new HashMap<String, String>();

		body.put("email", email);
		body.put("password", password);

		request.body(body);

		Response resp = request.post("https://boratech-practice-app.onrender.com/api/auth");

		// get status code

		int code = resp.getStatusCode();
		
		String token = resp.jsonPath().get("token");
		System.out.println("the token be: "+ token);

		throw new io.cucumber.java.PendingException();
	}

	@Then("we should get response code {int}")
	public void we_should_get_response_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
}
