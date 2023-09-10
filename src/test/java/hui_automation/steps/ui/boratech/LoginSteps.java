package hui_automation.steps.ui.boratech;

import java.util.Map;

import hui_automation.utilities.PageManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	private PageManager pages = PageManager.getInstance();

	@Given("user is on the BoraTech homepage")
	public void user_is_on_the_boratech_homepage() {
		pages.homePage().navigate();
		pages.homePage().isPageLoaded();
	}

	@And("user navigates to the Login page")
	public void user_navigates_to_the_login_page() {
		pages.navbar().navigateToLoginPage();
		pages.loginPage().isPageLoaded();
	}

	@When("user enters email - {string} and password - {string} then click the Login button")
	public void user_enters_the_username_and_password_and_submit(String email, String password) {
		pages.loginPage().login(email, password);
	}

	@Then("user should be on the Dashboard page")
	public void user_should_be_on_the_dashboard_page() {
		pages.dashboardPage().isPageLoaded();
	}

	@Then("user should receive login errors")
	public void user_should_receive_login_errors(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		String expectedErrorText = data.get("error");
		pages.loginPage().hasLoginFailed(expectedErrorText);
	}

}
