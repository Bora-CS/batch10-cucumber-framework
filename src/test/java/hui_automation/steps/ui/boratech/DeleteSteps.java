package hui_automation.steps.ui.boratech;

import org.openqa.selenium.WebDriver;

import hui_automation.utilities.DataManager;
import hui_automation.utilities.DriverManager;
import hui_automation.utilities.PageManager;
import io.cucumber.java.en.Then;

public class DeleteSteps {

	private WebDriver driver = DriverManager.getInstance();
	private DataManager dataManager = DataManager.getInstance();
	private PageManager pages = PageManager.getInstance();

	@Then("user deletes the newly added [Experience] on Dashboard page")
	public void user_deletes_the_newly_added_experience_on_dashboard_page() {
		pages.dashboardPage().deleteExperience(dataManager.getExperienceUI());
	}

	@Then("user deletes the newly added [Education] on Dashboard page")
	public void user_deletes_the_newly_added_education_on_dashboard_page() {
		pages.dashboardPage().deleteEducation(dataManager.getEducationUI());
	}

}
