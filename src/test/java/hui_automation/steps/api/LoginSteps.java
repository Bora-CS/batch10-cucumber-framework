package hui_automation.steps.api;

import java.util.Map;

import hui_automation.utilities.BoraTechAPIs;
import hui_automation.utilities.DataManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class LoginSteps {

	private DataManager dataManager = DataManager.getInstance();

	@Given("[API] user is logged in")
	public void api_user_is_logged_in(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		String token = BoraTechAPIs.login(data.get("email"), data.get("password"));
		dataManager.setToken(token);
	}

}
