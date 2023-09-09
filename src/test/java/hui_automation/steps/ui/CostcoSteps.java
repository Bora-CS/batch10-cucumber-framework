package hui_automation.steps.ui;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hui_automation.utilities.DriverManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class CostcoSteps {

	private WebDriver driver = DriverManager.getInstance();
	private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	@Given("user is on Costco homepage")
	public void user_is_on_costco_homepage() {
		driver.get("https://www.costco.com/");
	}

	@When("user clicks on My Warehouse")
	public void user_clicks_on_my_warehouse() {
		driver.findElement(By.xpath("//button[contains(@aria-label, 'current warehouse')]")).click();
	}

	@When("user enters zip coode {string} and clicks on Find")
	public void user_enters_zip_coode_and_clicks_on_find(String zipCode) {
		// wait for locations search box to be available
		wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.id("free-solo"))));
		Testkeys.pause(driver, 1);
		// enter data
		driver.findElement(By.id("free-solo")).sendKeys(zipCode);
		wait.until(ExpectedConditions.attributeToBe(By.id("free-solo"), "value", zipCode));
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(@data-bi-tc, 'Find a Warehouse')]"))).click();
	}

	@When("user chooses a store and sets it as My Warehouse")
	public void user_chooses_a_store_and_sets_it_as_my_warehouse(DataTable dataTable) {
		List<String> data = dataTable.asList();
		String loc = data.get(0);
		String locXpath = String.format("//button[contains(@data-bi-tc, '%s')]", loc);
		new Actions(driver).scrollToElement(driver.findElement(By.xpath(locXpath)))
				.moveToElement(driver.findElement(By.xpath(locXpath))).click().build().perform();
	}

	@When("users clicks on Delivery Location and enters {string}")
	public void users_clicks_on_delivery_location_and_enters(String zipCode) {
		String currentZipCodeXpath = "//button[contains(@aria-label, 'current delivery location')]";
		wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(currentZipCodeXpath))));
		driver.findElement(By.xpath("//button[contains(@aria-label, 'current delivery location')]")).click();
		driver.findElement(By.id("zipCode")).sendKeys(zipCode + Keys.ENTER);
		// wait for zip code to be updated
		wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath(currentZipCodeXpath))));
		wait.until(ExpectedConditions.attributeContains(By.xpath(currentZipCodeXpath), "aria-label", zipCode));
	}

	@Then("users see updated information")
	public void users_see_updated_information(DataTable dataTable) {
		String actualWarehouse = driver.findElement(By.xpath("//button[contains(@aria-label, 'current warehouse')]"))
				.getText();
		String actualZipCode = driver
				.findElement(By.xpath("//button[contains(@aria-label, 'current delivery location')]")).getText();
		List<String> data = dataTable.asList();
		Assertions.assertTrue(data.contains(actualWarehouse) && (data.contains(actualZipCode)));
	}

}
