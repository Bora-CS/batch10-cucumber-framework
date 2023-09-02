package api_stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import utilities.BoraTechAPIs;
import utilities.DataManager;

public class LoginSteps {

	private DataManager dataManager = DataManager.getInstance();

	@Given("[API] user is logged in")
	public void api_user_is_logged_in(DataTable dataTable) {
		Map<String, String> credentials = dataTable.asMap();
		String token = BoraTechAPIs.login(credentials.get("username"), credentials.get("password"));
		dataManager.setToken(token);
	}

	@Given("[API] user tries to login")
	public void api_user_tries_to_login(DataTable dataTable) {
		Map<String, String> credentials = dataTable.asMap();
		String errorMessage = BoraTechAPIs.loginUnhappy(credentials.get("email"), credentials.get("password"));
		dataManager.setErrorMessage(errorMessage);
	}

	@Then("[API] user should receive login errors")
	public void api_user_should_receive_login_errors(DataTable dataTable) {
		String actualErrorMessage = dataManager.getErrorMessage();
		String expectedErrorMessage = dataTable.asMap().get("error");

		assertEquals(expectedErrorMessage, actualErrorMessage);
	}

}
