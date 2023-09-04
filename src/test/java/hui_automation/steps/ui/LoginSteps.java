package hui_automation.steps.ui;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import hui_automation.utilities.DriverManager;
import hui_automation.utilities.Testkeys;
import pages.bora_tech.DashboardPage;
import pages.bora_tech.HomePage;
import pages.bora_tech.LoginPage;

public class LoginSteps {

	private WebDriver driver = DriverManager.getInstance();

	@Given("user is logged in")
	public void user_is_logged_in(DataTable dataTable) {
		Map<String, String> credentials = dataTable.asMap();
		String email = credentials.get("email");
		String password = credentials.get("password");

		// home page
		driver.get("https://boratech-practice-app.onrender.com/");
		Testkeys.pause(driver, 1);
		assertEquals("BoraTech", HomePage.pageTitle(driver).getText());

		// login page
		HomePage.mainLoginButton(driver).click();
		Testkeys.pause(driver, 1);
		assertTrue(driver.getCurrentUrl().endsWith("login"));

		LoginPage.emailInputBox(driver).sendKeys(email);
		LoginPage.passwordInputBox(driver).sendKeys(password);
		LoginPage.mainLoginButton(driver).click();
		Testkeys.pause(driver, 1);

		Testkeys.pause(driver, 1);
	}

	@Given("user is on the BoraTech homepage")
	public void user_is_on_the_bora_tech_homepage() {
		driver.get("https://boratech-practice-app.onrender.com/");
		Testkeys.pause(driver, 1);
		assertEquals("BoraTech", HomePage.pageTitle(driver).getText());
	}

	@When("user navigates to the Login page")
	public void user_navigates_to_the_login_page() {
		HomePage.mainLoginButton(driver).click();
		Testkeys.pause(driver, 1);
		assertTrue(driver.getCurrentUrl().endsWith("login"));
	}

	@When("user enters email - {string} and password - {string} then click the Login button")
	public void user_enters_email_and_password_then_click_the_login_button(String email, String password) {
		LoginPage.emailInputBox(driver).sendKeys(email);
		LoginPage.passwordInputBox(driver).sendKeys(password);
		LoginPage.mainLoginButton(driver).click();
		Testkeys.pause(driver, 1);
	}

	@Then("user should be on the Dashboard page")
	public void user_should_be_on_the_dashboard_page() {
		boolean urlFound = driver.getCurrentUrl().endsWith("dashboard");
		assertTrue(urlFound);
		assertEquals("Dashboard", DashboardPage.pageTitle(driver).getText());
		Testkeys.pause(driver, 1);
	}

}
