package ui_stepdefinitions;

import io.cucumber.java.en.*;
import utilities.PageManager;

public class DashboardSteps {

	private PageManager pages = PageManager.getInstance();

	@Then("user should be on the Dashboard page")
	public void user_should_be_on_the_dashboard_page() {
		pages.dashboardPage().validatePageload();
	}

	@Then("user should see a success alert that says [Experience Added]")
	public void user_should_see_a_success_alert_that_says_experience_added() {
		pages.dashboardPage().validateSuccessAlert("Experience Added");
	}

}
