package hui_automation.steps.ui;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hui_automation.utilities.DriverManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class TargetSteps {

	private WebDriver driver = DriverManager.getInstance();
	private int minimum;
	private int maximum;
	private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	private Actions act = new Actions(driver);

	@Given("user is on Target homepage")
	public void user_is_on_target_homepage() {
		// target home page
		driver.get("https://www.target.com/");
	}

	@Given("user clicks on Deals drop down menu")
	public void user_clicks_on_deals_drop_down_menu() {
		// deals drop down menu
		driver.findElement(By.xpath("//a[@aria-label='Deals']")).click();
	}

	@Given("user clicks on Clearance option")
	public void user_clicks_on_clearance_option() {
		// select clearance option
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-test='deals-clearance']"))).click();
	}

	@When("user is on Clearance page")
	public void user_is_on_clearance_page() {
		wait.until(ExpectedConditions.urlContains("clearance"));
		Testkeys.pause(driver, 1);
		String pageTitle = driver.findElement(By.xpath("//h1[@data-test='page-title']")).getText();
		assertEquals("Clearance", pageTitle);
	}

	@When("user clicks on Price button and then user enters {string} whole dollar and {string} whole dollar amounts")
	public void user_clicks_on_price_button_and_then_user_enters_whole_dollar_and_whole_dollar_amounts(String minimum,
			String maximum, DataTable dataTable) {
		Map<String, Integer> data = dataTable.asMap(String.class, Integer.class);
		this.minimum = data.get(minimum);
		this.maximum = data.get(maximum);
		// filter by price
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Price']"))).click();
		// custom price range
		driver.findElement(By.id("minPriceValue")).sendKeys("" + this.minimum);
		driver.findElement(By.id("maxPriceValue")).sendKeys("" + this.maximum);
	}

	@When("user clicks on Apply button")
	public void user_clicks_on_apply_button() {
		// apply
		act.moveToElement(driver.findElement(By.xpath("//button[text()='Apply']"))).click().build().perform();
	}

	@When("user clicks on Sort button and then clicks on Price: low to high")
	public void user_clicks_on_sort_button_and_then_clicks_on_price_low_to_high() {
		// sort by price
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@aria-label, 'Sort')]")))
				.click();
		// low to high
		wait.until(ExpectedConditions.elementToBeClickable(By.id("PriceLow-price-low to high"))).click();
	}

	@Then("the outcomes are validated against filter and sort conditions")
	public void the_outcomes_are_validated_against_filter_and_sort_conditions() {
		// get window dimension
		Dimension dim = driver.manage().window().getSize();
		int height = dim.getHeight();

		// scan the whole page
		if (Testkeys.containsElement(driver, By.xpath("//button[@data-test='select']"))) {
			act.scrollByAmount(0, height).pause(Duration.ofSeconds(2)).scrollByAmount(0, height)
					.pause(Duration.ofSeconds(2)).scrollByAmount(0, height).pause(Duration.ofSeconds(2)).build()
					.perform();
			act.scrollToElement(driver.findElement(By.xpath("//button[@data-test='select']")))
					.pause(Duration.ofSeconds(1)).build().perform();
		}

		// check for low price products
		List<WebElement> productPrices = driver
				.findElements(By.xpath("//div[@data-test='product-details']//span[@data-test='current-price']"));
		System.out.println("All products on the 1st page: " + productPrices.size());

		boolean tooLow = false;
		for (WebElement productPrice : productPrices) {
			// check if price detail is available
			if (productPrice.getText().contains("$")) {
				String[] priceArr = productPrice.getText().replaceAll("[(].*[)]", "").split("-");
				String priceStr = null;
				if (priceArr.length > 1)
					priceStr = priceArr[priceArr.length - 1].trim().replace("$", "");
				else
					priceStr = priceArr[0].trim().replace("$", "");
				double price = Double.parseDouble(priceStr);
				if (price < this.minimum) {
					tooLow = true;
					break;
				}
			}
		}
		assertFalse(tooLow,
				String.format("Found a product which price is lower than accepted minimum of $%d.%n", this.minimum));
	}

	@Given("user clicks on Categories drop down menu")
	public void user_clicks_on_categories_drop_down_menu() {
		driver.findElement(By.xpath("//a[@aria-label='Categories']")).click();
	}

	@Given("user clicks on Clearance option and choose all")
	public void user_clicks_on_clearance_option_and_choose_all() {
		act.scrollToElement(driver.findElement(By.xpath("//a[contains(@data-url, 'clearance')]")))
				.pause(Duration.ofSeconds(1)).build().perform();
		driver.findElement(By.xpath("//a[contains(@data-url, 'clearance')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'Button')]"))).click();
	}

	@When("user clicks on Sort button and then clicks on Price: high to low")
	public void user_clicks_on_sort_button_and_then_clicks_on_price_high_to_low() {
		// sort by price
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@aria-label, 'Sort')]")))
				.click();
		// low to high
		wait.until(ExpectedConditions.elementToBeClickable(By.id("PriceHigh-price-high to low"))).click();
	}

	@Then("the outcomes are displayed")
	public void the_outcomes_are_displayed() {
		// get window dimension
		Dimension dim = driver.manage().window().getSize();
		int height = dim.getHeight();

		// scan the whole page
		if (Testkeys.containsElement(driver, By.xpath("//button[@data-test='select']"))) {
			act.scrollByAmount(0, height).pause(Duration.ofSeconds(2)).scrollByAmount(0, height)
					.pause(Duration.ofSeconds(2)).scrollByAmount(0, height).pause(Duration.ofSeconds(2)).build()
					.perform();
			act.scrollToElement(driver.findElement(By.xpath("//button[@data-test='select']")))
					.pause(Duration.ofSeconds(1)).build().perform();
		}

		// check for high price products
		List<WebElement> productPrices = driver
				.findElements(By.xpath("//div[@data-test='product-details']//span[@data-test='current-price']"));
		System.out.println("All products on the 1st page: " + productPrices.size());

		boolean tooLow = false;
		this.maximum = 100;
		for (WebElement productPrice : productPrices) {
			// check if price detail is available
			if (productPrice.getText().contains("$")) {
				String[] priceArr = productPrice.getText().replaceAll("[(].*[)]", "").split("-");
				String priceStr = null;
				if (priceArr.length > 1)
					priceStr = priceArr[priceArr.length - 1].trim().replace("$", "");
				else
					priceStr = priceArr[0].trim().replace("$", "");
				double price = Double.parseDouble(priceStr);
				if (price < this.maximum) {
					tooLow = true;
					break;
				}
			}
		}
		assertFalse(tooLow,
				String.format("Found a product which price is lower than accepted maximum of $%d.%n", this.maximum));

	}

	@When("user enters {} in search input field and clicks on submit button")
	public void user_enters_search_term_in_search_input_field_and_clicks_on_submit_button(String searchTerm) {
		driver.findElement(By.id("search")).sendKeys(searchTerm);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@Then("users sees all the products related to the {}")
	public void users_sees_all_the_products_related_to_the_search_term(String searchTerm) {
		System.out.println("Search for: " + searchTerm);
		String searchDetail = wait.until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[@data-test='resultsHeading']"))))
				.getText();
		System.out.println(searchDetail);
		Dimension dim = driver.manage().window().getSize();
		int height = dim.getHeight();

		// update search term
		String[] searchTerms = searchTerm.toLowerCase().replace("mens", "men's").replace("womens", "women's")
				.replace("dresses", "dress").split(" ");

		// scan the whole page
		if (Testkeys.containsElement(driver, By.xpath("//button[@data-test='select']"))) {
			act.scrollByAmount(0, height).pause(Duration.ofSeconds(2)).scrollByAmount(0, height)
					.pause(Duration.ofSeconds(2)).scrollByAmount(0, height).pause(Duration.ofSeconds(2)).build()
					.perform();
			act.scrollToElement(driver.findElement(By.xpath("//button[@data-test='select']")))
					.pause(Duration.ofSeconds(1)).build().perform();
		}

		List<WebElement> productTitles = driver.findElements(By.xpath("//a[@data-test='product-title']"));
		System.out.println("All products on the 1st page: " + productTitles.size());
		// validation
		boolean targetFound = false;
		for (WebElement productTitle : productTitles) {
			String title = productTitle.getText().toLowerCase();
			boolean check = true;
			// check if title contains all the words in the search term
			for (String word : searchTerms) {
				if (!title.contains(word)) {
					check = false;
					break;
				}
			}
			// related product found
			if (check) {
				targetFound = true;
				break;
			}
		}

		assertTrue(targetFound);
	}

}
