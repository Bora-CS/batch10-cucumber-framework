package hui_automation.steps.ui.boratech;

import java.util.Map;


import hui_automation.pojos.Education;
import hui_automation.utilities.DataManager;
import hui_automation.utilities.PageManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddEducationSteps {

	private PageManager pages = PageManager.getInstance();
	private DataManager dataManager = DataManager.getInstance();

	@When("user clicks on [Add Education] button")
	public void user_clicks_on_add_education_button() {
		pages.dashboardPage().isPageLoaded();
		pages.dashboardPage().clickOnAddEducation();
	}

	@When("user enters a new [Education] data")
	public void user_enters_a_new_education_data(DataTable dataTable) {
		pages.addEducationPage().isPageLoaded();
		Map<String, String> data = dataTable.asMap();
		Education education = new Education(data);
		if (data.get("error") == null)
			education.school += " " + Testkeys.getTimestamp();
		dataManager.setEducationUI(education);
		pages.addEducationPage().addEducation(dataManager.getEducationUI());
	}

	@Then("user sees a newly added [Education] on Dashboard page")
	public void user_sees_a_newly_added_education_on_dashboard_page() {
		pages.dashboardPage().isPageLoaded();
		pages.dashboardPage().validateAddEducation(dataManager.getEducationUI());
	}

	@Then("user sees a list of error messages of [Education]")
	public void user_sees_a_list_of_error_messages_of_education() {
		pages.addEducationPage().isPageLoaded();
		pages.addEducationPage().hasAddEducationFailed();
	}

}
