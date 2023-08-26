package ui_stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.*;
import utilities.DriverManager;

public class NikeSteps {

	private WebDriver driver = DriverManager.getInstance();

	@Given("user is on the nike homepage")
	public void user_is_on_the_nike_homepage() {
		driver.navigate().to("https://www.nike.com/");
		WebElement logo = driver.findElement(By.xpath("//a[@aria-label='Nike Home Page']"));
		assertTrue(logo.isDisplayed());
	}

	@When("user searches for {string}")
	public void user_searches_for(String searchTerm) {
		driver.findElement(By.id("VisualSearchInput")).sendKeys(searchTerm + Keys.ENTER);
	}

	@Then("user should see search results for {string}")
	public void user_should_see_search_results_for(String searchTerm) {
		List<WebElement> titles = driver.findElements(By.xpath(
				"//div[contains(@class, 'product-card product-grid__card')]//div[@class='product-card__title']"));
		boolean found = false;
		for (WebElement title : titles) {
			if (title.getText().contains(searchTerm)) {
				found = true;
				break;
			}
		}
		assertTrue(found, "Expected to find products that's realted to " + searchTerm + ", but didn't find any.");
	}

}
