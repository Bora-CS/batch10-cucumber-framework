package hui_automation.steps.ui;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import hui_automation.utilities.DriverManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class PostSteps {

	private WebDriver driver = DriverManager.getInstance();

	@Given("user is logged in")
	public void user_is_logged_in(DataTable dataTable) {
		Map<String, String> credentials = dataTable.asMap();
		String username = credentials.get("username");
		String password = credentials.get("password");

		driver.get("https://boratech-practice-app.onrender.com/");
		Testkeys.pause(driver, 2);
		assertEquals("BoraTech", driver.findElement(By.xpath("//h1[@class]")).getText());

		driver.findElement(By.xpath("//a[@href='/login'][contains(@class, 'btn')]")).click();
		Testkeys.pause(driver, 2);
		assertTrue(driver.getCurrentUrl().endsWith("login"));

		driver.findElement(By.name("email")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Testkeys.pause(driver, 2);
	}

	@When("user navigates to the Posts page")
	public void user_navigates_to_the_posts_page() {
		driver.findElement(By.xpath("//a[@href='/posts']")).click();
		Testkeys.pause(driver, 2);
		assertTrue(driver.getCurrentUrl().endsWith("posts"));
	}

	@When("user enters the post content")
	public void user_enters_the_post_content(DataTable dataTable) {
		Map<String, String> data = dataTable.asMaps().get(0);
		String content = data.get("content");
		driver.findElement(By.tagName("textarea")).sendKeys(content);
	}

	@When("user clicks on the submit button")
	public void user_clicks_on_the_submit_button() {
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}

	@Then("user should see a success alert that says {string}")
	public void user_should_see_a_success_alert_that_says(String successAlertText) {
		try {
			driver.findElement(By.xpath(String.format("//*[contains(text(), '%s')]", successAlertText)));
		} catch (NoSuchElementException e) {
			assertTrue(false, "Expected to see an success alert, but not found.");
		}
		assertTrue(true);
	}

}
