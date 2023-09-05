package hui_automation.steps.ui.google;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import hui_automation.utilities.DriverManager;
import io.cucumber.java.en.*;
import page_factory.google.SearchHomePage;

public class SearchSteps {

	private WebDriver driver = DriverManager.getInstance();
	private SearchHomePage homePage = new SearchHomePage(driver);

	@Given("user is on Google home page")
	public void user_is_on_google_home_page() {
		driver.get("https://www.google.com/");
	}

	@When("user enters {string} in search box")
	public void user_enters_in_search_box(String searchTerm) {
		homePage.enterSearchTerm(searchTerm);
	}

	@When("user clicks on search button")
	public void user_clicks_on_search_button() {
		homePage.clickSearchButton();
	}

	@Then("user sees {string} related titles")
	public void user_sees_related_titles(String searchTerm) {
		List<WebElement> elements = driver.findElements(By.xpath("//br/following-sibling::h3"));
		String[] words = searchTerm.toLowerCase().split(" ");
		boolean targetFound = false;
		SEARCH_LOOP: for (WebElement element : elements) {
			String title = element.getText().toLowerCase();
			for (String word : words) {
				if (!title.contains(word))
					continue SEARCH_LOOP;
			}
			targetFound = true;
			System.out.println(title);
			break;
		}
		assertTrue(targetFound);
	}

}
