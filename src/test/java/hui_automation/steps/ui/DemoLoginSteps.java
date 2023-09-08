package hui_automation.steps.ui;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import hui_automation.utilities.DriverManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class DemoLoginSteps {

	WebDriver driver = DriverManager.getInstance();

	@Given("user is on demo login page")
	public void user_is_on_demo_login_page() {
		driver.get("https://www.saucedemo.com/");
	}

	@When("user enter {string} and {string} and then clicks Login button")
	public void user_enter_and_and_then_clicks_login_button(String username, String password, DataTable dataTable) {
		Map<String, String> userData = dataTable.asMap();
		driver.findElement(By.id("user-name")).sendKeys(userData.get(username));
		driver.findElement(By.id("password")).sendKeys(userData.get(password));
		driver.findElement(By.id("login-button")).click();
		Testkeys.pause(driver, 1);
	}

	@When("user attempts to enter {} and {} and then clicks Login button")
	public void login(String username, String password) {
		driver.findElement(By.id("user-name")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("login-button")).click();
		Testkeys.pause(driver, 1);
	}

	@Then("login page displays {}")
	public void login_page_displays(String errText) {
		String displayErr = driver.findElement(By.xpath("//*[@data-test='error']")).getText();
		assertTrue(displayErr.contains(errText));
	}

	@Then("user is on Products page")
	public void user_is_on_products_page() {
		assertTrue(driver.getCurrentUrl().contains("inventory"));
	}

}
