package hui_automation.steps.ui.boratech;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import hui_automation.utilities.DriverManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
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

	@Given("user is on the BoraTech homepage")
	public void user_is_on_the_boratech_homepage() {
		homePage.navigate();
		homePage.isPageLoaded();
	}

	@And("user navigates to the Login page")
	public void user_navigates_to_the_login_page() {
		navbar.navigateToLoginPage();
		loginPage.isPageLoaded();
	}

	@When("user enters email - {string} and password - {string} then click the Login button")
	public void user_enters_the_username_and_password_and_submit(String email, String password) {
		loginPage.login(email, password);
	}

	@Then("user should be on the Dashboard page")
	public void user_should_be_on_the_dashboard_page() {
		Testkeys.waitUtilURL_Contains(driver, "dashboard", 10);
		dashboardPage.isPageLoaded();
	}

	@Then("user should receive login errors")
	public void user_should_receive_login_errors(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		String expectedErrorText = data.get("error");
		loginPage.isLoginFailed(expectedErrorText);
	}

}
