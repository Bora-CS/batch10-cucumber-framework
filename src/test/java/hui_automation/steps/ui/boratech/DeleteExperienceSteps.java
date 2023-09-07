package hui_automation.steps.ui.boratech;

import org.openqa.selenium.WebDriver;

import hui_automation.pages.bora_tech.DashboardPage;
import hui_automation.utilities.DriverManager;
import io.cucumber.java.en.*;

public class DeleteExperienceSteps {

	private WebDriver driver = DriverManager.getInstance();
	private DashboardPage dashboardPage = new DashboardPage(driver);

	@Then("user deletes all experiences")
	public void user_deletes_all_experiences() {
		dashboardPage.deleteAllExperiences();
	}

}
