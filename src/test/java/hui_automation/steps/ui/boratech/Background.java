package hui_automation.steps.ui.boratech;

import java.util.Map;

import hui_automation.utilities.PageManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

public class Background {

	private PageManager pages = PageManager.getInstance();

	@Given("user is logged in")
	public void user_is_logged_in(DataTable dataTable) {
		pages.homePage().navigate();
		pages.homePage().isPageLoaded();
		pages.homePage().clickOnLogin();
		pages.loginPage().isPageLoaded();
		Map<String, String> data = dataTable.asMap();
		String email = data.get("email");
		String password = data.get("password");
		pages.loginPage().login(email, password);
	}

}
