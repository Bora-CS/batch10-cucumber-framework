package hui_automation.steps.ui.boratech;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import hui_automation.pojos.Experience;
import hui_automation.utilities.DataManager;
import hui_automation.utilities.DriverManager;
import hui_automation.utilities.PageManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddExperienceSteps {

	private WebDriver driver = DriverManager.getInstance();
	private DataManager dataManager = DataManager.getInstance();
	private PageManager pages = PageManager.getInstance();

	@When("user clicks on [Add Experience] button")
	public void user_clicks_on_add_experience_button() {
		pages.dashboardPage().clickOnAddExperience();
	}

	@When("user enters a new [Experience] data")
	public void user_enters_a_new_experience_data(DataTable dataTable) {
		Testkeys.waitUtilURL_Contains(driver, "add-experience", 10);
		pages.addExperiencePage().isPageLoaded();
		Map<String, String> data = dataTable.asMap();
		Experience experience = new Experience(data);
		if (data.get("error") == null)
			experience.company += " " + Testkeys.getTimestamp();
		dataManager.setExperienceUI(experience);
		pages.addExperiencePage().addExperience(dataManager.getExperienceUI());
	}

	@Then("user sees a newly added [Experience] on Dashboard page")
	public void user_sees_a_newly_added_experience_on_dashboard_page() {
		Testkeys.waitUtilURL_Contains(driver, "dashboard", 10);
		pages.dashboardPage().isPageLoaded();
		pages.dashboardPage().validateAddExperience(dataManager.getExperienceUI());
	}

	@Then("user sees a list of error messages of [Experience]")
	public void user_sees_a_list_of_error_messages_of_experience() {
		Testkeys.waitUtilURL_Contains(driver, "add-experience", 10);
		pages.addExperiencePage().hasAddExperienceFailed();
	}

}
