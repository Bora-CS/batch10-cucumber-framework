package ui_stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.Keywords;
import utilities.PageManager;

public class LoginSteps {

	private PageManager pages = PageManager.getInstance();

	@Given("user is on the boratech homepage")
	public void user_is_on_the_boratech_homepage() {
		pages.homePage().navigate();
		pages.homePage().validatePageload();
	}

	@And("user navigates to the Login page")
	public void user_navigates_to_the_login_page() {
		pages.navbar().navigateToLoginPage();
		pages.loginPage().validatePageload();
	}

	@When("user enters the username - {string} and password - {string} and submit")
	public void user_enters_the_username_and_password_and_submit(String username, String password)
			throws InterruptedException {
		pages.loginPage().login(username, password);
		Keywords.wait(2);
	}

	@Then("user should be on the Dashboard page")
	public void user_should_be_on_the_dashboard_page() {
		pages.dashboardPage().validatePageload();
	}

	@Then("user should see a login error")
	public void user_should_see_a_login_error() {
		pages.loginPage().validateErrorState();
	}
}
