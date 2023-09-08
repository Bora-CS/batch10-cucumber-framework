package ui_stepdefinitions;

import io.cucumber.java.en.*;

import utilities.PageManager;

public class UI_ExperienceSteps {
	private PageManager pages = PageManager.getInstance();

	@When("user navigates to the Experience page")
	public void user_navigates_to_the_experience_page() {
		pages.ExperiencePage().experienceButton();

	}

	@And("user tries to add a new experience")
	public void user_tries_to_add_a_new_experience(io.cucumber.datatable.DataTable dataTable) {
		pages.ExperiencePage().enterExpContent(dataTable);
	}

	@Then("user should be able to see Experience Added alert showing")
	public void user_should_be_able_to_see_experience_added_alert_showing() {
		pages.ExperiencePage().validateExperiencePost();
	}

	@Then("user should receive error messages")
	public void user_should_receive_error_messages(io.cucumber.datatable.DataTable dataTable) throws Exception {
		pages.ExperiencePage().validateErrorAlert(dataTable);
	}
}
