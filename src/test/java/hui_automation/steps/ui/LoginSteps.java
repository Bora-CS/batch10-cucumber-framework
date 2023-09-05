package hui_automation.steps.ui;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;

import hui_automation.utilities.DriverManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.java.en.*;
import pages.bora_tech.DashboardPage;
import pages.bora_tech.HomePage;
import pages.bora_tech.LoginPage;
import pages.bora_tech.Navbar;

public class LoginSteps {

	private WebDriver driver = DriverManager.getInstance();
	private HomePage homePage = new HomePage(driver);
	private Navbar navbar = new Navbar(driver);
	private LoginPage loginPage = new LoginPage(driver);
	private DashboardPage dashboardPage = new DashboardPage(driver);

	@Given("user is on the boratech homepage")
	public void user_is_on_the_boratech_homepage() {
		homePage.navigate();
		homePage.validatePageload();
	}

	@And("user navigates to the Login page")
	public void user_navigates_to_the_login_page() {
		navbar.navigateToLoginPage();
		loginPage.validatePageload();
	}

	@When("user enters the username - {string} and password - {string} and submit")
	public void user_enters_the_username_and_password_and_submit(String username, String password) {
		loginPage.login(username, password);
		Testkeys.pause(driver, 2);
	}

	@Then("user should be on the Dashboard page")
	public void user_should_be_on_the_dashboard_page() {
		dashboardPage.validatePageload();
	}

}
