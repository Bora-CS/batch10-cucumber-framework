package api_stepdefinitions;

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

}
