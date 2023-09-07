package ui_stepdefinitions;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import page_objects.DashboardPage;
import page_objects.HomePage;
import page_objects.LoginPage;
import page_objects.Navbar;
import page_objects.PostsPage;
import utilities.DriverManager;
import utilities.Keywords;
import utilities.PageManager;

public class PostSteps {

	private PageManager pages = PageManager.getInstance();

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

	@When("user enters the post content")
	public void user_enters_the_post_content(DataTable dataTable) {
		Map<String, String> data = dataTable.asMaps().get(0);
		String content = data.get("content") + " - " + Keywords.getTimeStamp();

		pages.postsPage().enterPostContent(content);
	}

	@When("user clicks on the submit button")
	public void user_clicks_on_the_submit_button() {
		pages.postsPage().submitPost();
	}

	@Then("user should see a success alert that says [Post Created]")
	public void user_should_see_a_success_alert_that_says() {
		pages.postsPage().validateCreatePost();
	}

}
