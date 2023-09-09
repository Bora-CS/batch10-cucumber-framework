package ui_stepdefinitions;

import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utilities.Keywords;
import utilities.PageManager;

public class CommonSteps {

	private PageManager pages = PageManager.getInstance();

	@Given("user is on the boratech homepage")
	public void user_is_on_the_boratech_homepage() {
		pages.homePage().navigate();
		pages.homePage().validatePageload();
	}

	@And("user navigates to the Login page")
	public void user_navigates_to_the_login_page() {
		pages.navbar().navigateToLoginPage();
		pages.loginPage().validatePageload();
	}

	@Given("user is logged in")
	public void user_is_logged_in(DataTable dataTable) throws InterruptedException {
		Map<String, String> credentials = dataTable.asMap();
		String username = credentials.get("username");
		String password = credentials.get("password");

		pages.homePage().navigate();
		pages.homePage().clickOnLogin();

		pages.loginPage().login(username, password);
		Keywords.wait(2);
		pages.dashboardPage().validatePageload();
	}

	@When("user navigates to the Posts page")
	public void user_navigates_to_the_posts_page() {
		pages.navbar().navigateToPostsPage();
		pages.postsPage().validatePageload();
	}

}
