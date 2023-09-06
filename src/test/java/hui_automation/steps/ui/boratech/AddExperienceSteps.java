package hui_automation.steps.ui.boratech;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import hui_automation.pages.bora_tech.AddExperiencePage;
import hui_automation.pages.bora_tech.DashboardPage;
import hui_automation.pojos.Experience;
import hui_automation.utilities.DataManager;
import hui_automation.utilities.DriverManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class AddExperienceSteps {

	private WebDriver driver = DriverManager.getInstance();
	private DataManager dataManager = DataManager.getInstance();
	private DashboardPage dashboardPage = new DashboardPage(driver);
	private AddExperiencePage addExperiencePage = new AddExperiencePage(driver);

	@When("user clicks on [Add Experience] button")
	public void user_clicks_on_add_experience_button() {
		dashboardPage.clickOnAddExperience();
	}

	@When("user enters a new [Experience] data")
	public void user_enters_a_new_experience_data(DataTable dataTable) {
		Testkeys.waitUtilURL_Contains(driver, "add-experience", 10);
		addExperiencePage.isPageLoaded();
		Map<String, String> data = dataTable.asMap();
		boolean isDataError = Boolean.valueOf(data.get("error"));
		if (!isDataError)
			dataManager.setExperienceUI(new Experience(data.get("company") + " " + Testkeys.getUniqueMillsTimeStr(),
					data.get("job title"), data.get("location"), data.get("from"), data.get("to"),
					Boolean.valueOf(data.get("current")), data.get("job description"), null));
		else
			dataManager.setExperienceUI(
					new Experience(data.get("company"), data.get("job title"), data.get("location"), data.get("from"),
							data.get("to"), Boolean.valueOf(data.get("current")), data.get("job description"), null));
		addExperiencePage.addExperience(dataManager.getExperienceUI());
	}

	@Then("user sees a newly added [Experience] on Dashboard page")
	public void user_sees_a_newly_added_experience_on_dashboard_page() {
		Testkeys.waitUtilURL_Contains(driver, "dashboard", 10);
		dashboardPage.isPageLoaded();
		dashboardPage.validateAddExperience(dataManager.getExperienceUI());
	}

	@Then("user sees a list of error messages of [Experience]")
	public void user_sees_a_list_of_error_messages_of_experience(DataTable dataTable) {
		Testkeys.waitUtilURL_Contains(driver, "add-experience", 10);
		// system default error messages
		Map<String, String> totalErrors = dataTable.asMap();

		Map<String, String> emptyFields = new HashMap<>();
		emptyFields.put("job title", dataManager.getExperienceUI().jobTitle);
		emptyFields.put("company", dataManager.getExperienceUI().company);
		emptyFields.put("from", dataManager.getExperienceUI().fromDate);

		// generate expected error messages
		List<String> expectedErrorMsgs = new ArrayList<>();
		for (String errorKey : emptyFields.keySet())
			if (emptyFields.get(errorKey).isEmpty())
				expectedErrorMsgs.add(totalErrors.get(errorKey));

		addExperiencePage.hasAddExperienceFailed(expectedErrorMsgs);
	}

}
