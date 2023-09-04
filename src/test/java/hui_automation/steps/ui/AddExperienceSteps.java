package hui_automation.steps.ui;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hui_automation.pojos.Experience;
import hui_automation.utilities.BoraTech;
import hui_automation.utilities.DataManager;
import hui_automation.utilities.DriverManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import pages.bora_tech.AddExperiencePage;
import pages.bora_tech.DashboardPage;

public class AddExperienceSteps {

	private WebDriver driver = DriverManager.getInstance();
	private DataManager dataManager = DataManager.getInstance();

	@When("user clicks on [Add Experience] button")
	public void user_clicks_on_add_experience_button() {
		DashboardPage.AddExperienceButton(driver).click();
		Testkeys.pause(driver, 1);
	}

	@When("user enters a new [Experience] data")
	public void user_enters_a_new_experience_data(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		Experience expData = new Experience(data.get("company") + " " + Testkeys.getUniqueMillsTimeStr(),
				data.get("title"), data.get("location"), data.get("from"), data.get("to"),
				Boolean.parseBoolean(data.get("current")), data.get("description"), null);
		BoraTech.addExperience(driver, expData);
		dataManager.setExperienceUI(expData);
	}

	@When("user clicks on [Submit] button")
	public void user_clicks_on_submit_button() {
		AddExperiencePage.submitButton(driver).click();
	}

	@Then("user sees a newly added [Experience] on Dashboard page")
	public void user_sees_a_newly_added_experience_on_dashboard_page() {
		new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("dashboard"));
		String expectedTitle = dataManager.getExperienceUI().title;
		String expectedCompany = dataManager.getExperienceUI().company;
		Testkeys.pause(driver, 1);
		List<WebElement> rows = DashboardPage.ExperienceTableRows(driver);
		boolean targetFound = false;
		for (WebElement row : rows) {
			String actualComapny = row.findElement(By.xpath("td[1]")).getText();
			String actualTitle = row.findElement(By.xpath("td[2]")).getText();
			if (actualComapny.equals(expectedCompany) && actualTitle.equals(expectedTitle)) {
				targetFound = true;
				break;
			}
		}
		assertTrue(targetFound);
	}

}
