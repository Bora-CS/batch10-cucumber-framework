package api_stepdefinition;

import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import utilities.BoraTechAPIs;

public class LoginSteps {

	@Given("[API] user is logged in")
	public void api_user_is_logged_in(DataTable dataTable) {
		Map<String, String> credentials = dataTable.asMap();
		String token = BoraTechAPIs.login(credentials.get("username"), credentials.get("password"));
		System.out.println(token);
	}

}
