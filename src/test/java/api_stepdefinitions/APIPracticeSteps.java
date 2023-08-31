package api_stepdefinitions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIPracticeSteps {
	Response resp;

	@Given("call login api with {string} and {string}")
	public void call_login_api(String email, String password) {

		RestAssured.baseURI = "https://boratech-practice-app.onrender.com/";
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		HashMap<String, String> body = new HashMap<>();
		body.put("email", email);
		body.put("password", password);
		request.body(body);

		resp = request.post("api/auth");
		System.out.println(resp.getBody().asPrettyString());

	}

	@Then("we should get reponse code {int}")
	public void we_should_get_reponse_code(int expectedStatusCode) {

		int actualStatusCode = resp.getStatusCode();

		Assertions.assertTrue(expectedStatusCode == actualStatusCode, "The actual status code is not match!");

	}

	@Given("login with user and validate status")
	public void login_with_user_and_validate_status(DataTable dataTable) {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
		// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
		// Double, Byte, Short, Long, BigInteger or BigDecimal.
		//
		// For other transformations you can register a DataTableType.

		List<Map<String, String>> datas = dataTable.asMaps();

		System.out.println("data from featuer file: " + datas.get(0));
		System.out.println("data from featuer file: " + datas.get(1));

	}

}
