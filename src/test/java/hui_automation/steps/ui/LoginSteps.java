package hui_automation.steps.ui;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import hui_automation.utilities.DriverManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.java.en.*;

public class LoginSteps {

	WebDriver driver = DriverManager.getInstance();

	@Given("user is on the BoraTech homepage")
	public void user_is_on_the_bora_tech_homepage() {
		driver.get("https://boratech-practice-app.onrender.com/");
		Testkeys.pause(driver, 2);
		assertEquals("BoraTech", driver.findElement(By.xpath("//h1[@class]")).getText());
	}

	@When("user navigates to the Login page")
	public void user_navigates_to_the_login_page() {
		driver.findElement(By.xpath("//a[@href='/login'][contains(@class, 'btn')]")).click();
		Testkeys.pause(driver, 2);
		assertTrue(driver.getCurrentUrl().endsWith("login"));
	}

	@When("user enters email - {string} and password - {string} then click the Login button")
	public void user_enters_email_and_password_then_click_the_login_button(String email, String password) {
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Testkeys.pause(driver, 2);
	}

	@Then("user should be on the Dashboard page")
	public void user_should_be_on_the_dashboard_page() {
		boolean urlFound = driver.getCurrentUrl().endsWith("dashboard");
		assertTrue(urlFound);
		String title = driver.findElement(By.xpath("//h1[@class]")).getText();
		assertEquals("Dashboard", title);
		Testkeys.pause(driver, 2);
	}

}
