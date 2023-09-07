package hui_automation.steps.ui;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import hui_automation.utilities.DriverManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class CDC_Steps {

	private WebDriver driver = DriverManager.getInstance();
	private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	private List<String> filters = new ArrayList<String>();

	@Given("user is on CDC Data homepage")
	public void user_is_on_cdc_data_homepage() {
		driver.get("https://data.cdc.gov/");
	}

	@Given("user clicks on Data Catalog on top menu bar")
	public void user_clicks_on_data_catalog_on_top_menu_bar() {
		driver.findElement(By.xpath("//a[@href='/browse']")).click();
	}

	@Given("user is on Data search page")
	public void user_is_on_data_search_page() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("catalog-landing-page"))));
		assertTrue(driver.getCurrentUrl().endsWith("/browse"));
	}

	@When("user expands [Chronic Diseases] and clicks on [Youth Risk Behaviors] under [Categories]")
	public void user_expands_chronic_diseases_and_clicks_on_youth_risk_behaviors_under_categories(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		String title = "Show all options for " + data.get("Categories");
		String titleXpath = String.format("//a[@title='%s']/span", title);
		driver.findElement(By.xpath(titleXpath)).click();
		String subTitleXpath = String.format("//a[@title='%s']", data.get("Sub-Categories"));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(subTitleXpath))).click();
		String filter = "Categories > ";
		if (data.get("Sub-Categories").length() > 0)
			filter += data.get("Sub-Categories");
		else
			filter += data.get("Categories");
		this.filters.add(filter);
	}

	@When("user clicks on [Charts] under [View Types]")
	public void user_clicks_on_charts_under_view_types(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		driver.findElement(By.xpath(String.format("//a[@title='%s']", data.get("View Types")))).click();
		String filter = "View Types > ";
		filter += data.get("View Types");
		this.filters.add(filter);
	}

	@When("user expands [Tags] and on the pop up menu, user clicks on [youth online]")
	public void user_expands_tags_and_on_the_pop_up_menu_user_clicks_on_youth_online(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		String tag = data.get("Tags").replace(" ", "+");
		String tagXpath = String.format("//div[@role='dialog']//ul/li//a[contains(@href, '%s')]", tag);
		driver.findElement(By.xpath("//a[@title='Show all options for Tags']")).click();
		new Actions(driver).scrollToElement(driver.findElement(By.xpath(tagXpath))).pause(Duration.ofSeconds(1)).build()
				.perform();
		driver.findElement(By.xpath(tagXpath)).click();
		String filter = "Tags > ";
		filter += data.get("Tags");
		this.filters.add(filter);
	}

	@When("user selects {string} from Sort drop down menu")
	public void user_selects_from_sort_drop_down_menu(String sortText) {
		Select sortSelect = new Select(driver.findElement(By.id("browse2-sort-type")));
		sortSelect.selectByVisibleText(sortText);
	}

	@Then("users sees all filters on top of the result page")
	public void users_sees_all_filters_on_top_of_the_result_page() {
		assertTrue(Testkeys.containsElement(driver, By.xpath("//span[@class='browse2-results-clear-controls']/a")));
		List<WebElement> filterTags = driver.findElements(
				By.xpath("//span[@class='browse2-results-clear-controls']/a[@class='browse2-results-clear-control']"));
		// validate all filters
		boolean targetAll = true;
		for (WebElement filterTag : filterTags) {
			if (!this.filters.contains(filterTag.getText())) {
				targetAll = false;
				break;
			}
		}
		assertTrue(targetAll);
	}

	@Then("the result page is sorted by view counts")
	public void the_result_page_is_sorted_by_view_counts() {
		List<WebElement> viewCounts = driver.findElements(
				By.xpath("//div[@class='browse2-result']//div[@class='browse2-result-view-count-value']"));
		ArrayList<Integer> nums = new ArrayList<>();
		for (WebElement viewCount : viewCounts)
			nums.add(Integer.parseInt(viewCount.getText().replace(",", "")));
		int max = nums.get(0);
		for (int num : nums) {
			assertTrue(num <= max);
			max = num;
		}
	}

}
