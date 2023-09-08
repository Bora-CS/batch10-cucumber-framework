package LydiaHomework907;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import utilities.PageManager;

public class AddExperienceSteps {

	private PageManager pages = PageManager.getInstance();

	@Given("user is logged in with email - {string} and password - {string}")
	public void user_is_logged_in_with_email_and_password(String email, String password) throws InterruptedException {
		pages.homePage().navigate();
		pages.navbar().navigateToLoginPage();
		pages.loginPage().login(email, password);

	}

	@When("user is on the dashboard page, user click Add Experience button")
	public void user_is_on_the_dashboard_page_user_click_add_experience_button() {
		pages.dashboardPage().addExperienceButton();
	}

	@When("user should be able to add new experience")
	public void user_should_be_able_to_add_new_experience(DataTable dataTable) {

		pages.getAddExperiencePage().addExperienceHappy(dataTable);
	}

	@Then("user should be able to see Experience Added alert showing")
	public void user_should_be_able_to_see_experience_added_alert_showing() {

		pages.getAddExperiencePage().validateNewExperience();
	}

	@When("user tries to add new experiences")
	public void pom_user_tries_to_add_new_experiences(io.cucumber.datatable.DataTable dataTable) {
		pages.getAddExperiencePage().addExperienceUnhappy(dataTable);
	}

	@Then("user should receive error messages")
	public void pom_user_should_receive_error_messages(DataTable dataTable) {
		pages.getAddExperiencePage().validateErrorAlert(dataTable);
	}

}
