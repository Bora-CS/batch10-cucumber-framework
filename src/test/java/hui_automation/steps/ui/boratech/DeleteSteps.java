package hui_automation.steps.ui.boratech;

import org.openqa.selenium.WebDriver;

import hui_automation.pages.bora_tech.DashboardPage;
import hui_automation.utilities.DataManager;
import hui_automation.utilities.DriverManager;
import io.cucumber.java.en.*;

public class DeleteSteps {

	private WebDriver driver = DriverManager.getInstance();
	private DataManager dataManager = DataManager.getInstance();
	private DashboardPage dashboardPage = new DashboardPage(driver);

	@Then("user deletes the newly added [Experience] on Dashboard page")
	public void user_deletes_the_newly_added_experience_on_dashboard_page() {
		dashboardPage.deleteExperience(dataManager.getExperienceUI());
	}

	@Then("user deletes the newly added [Education] on Dashboard page")
	public void user_deletes_the_newly_added_education_on_dashboard_page() {
		dashboardPage.deleteEducation(dataManager.getEducationUI());
	}

}
