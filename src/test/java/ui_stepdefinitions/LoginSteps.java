package ui_stepdefinitions;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page_objects.DashboardPage;
import page_objects.HomePage;
import page_objects.LoginPage;
import page_objects.Navbar;
import utilities.DriverManager;
import utilities.Keywords;

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
	public void user_enters_the_username_and_password_and_submit(String username, String password)
			throws InterruptedException {
		loginPage.login(username, password);
		Keywords.wait(2);
	}

	@Then("user should be on the Dashboard page")
	public void user_should_be_on_the_dashboard_page() {
		dashboardPage.validatePageload();
	}

	@Then("user should see a login error")
	public void user_should_see_a_login_error() {
		loginPage.validateErrorState();
	}
}
