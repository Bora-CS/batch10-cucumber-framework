package hui_automation.steps.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import hui_automation.utilities.BoraTechAPIs;
import hui_automation.utilities.DataManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class LoginSteps {

	private DataManager dataManager = DataManager.getInstance();
	private String loginErrorMsg;

	@Given("[API] user is logged in")
	public void api_user_is_logged_in(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		String token = BoraTechAPIs.login(data.get("email"), data.get("password"));
		dataManager.setToken(token);
	}

	@Given("[API] user enters wrong credentials")
	public void api_user_enters_wrong_credentials(DataTable dataTable) {
		String email = dataTable.asMap().get("email");
		String password = dataTable.asMap().get("password");
		this.loginErrorMsg = BoraTechAPIs.badLogin(email, password);
	}

	@Then("[API] user should receive login errors")
	public void api_user_should_receive_login_errors(DataTable dataTable) {
		String expectedErrorMsg = dataTable.asMap().get("error");
		String actualErrorMsg = this.loginErrorMsg;
		assertEquals(expectedErrorMsg, actualErrorMsg);
	}

}
