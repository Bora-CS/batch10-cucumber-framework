package ui_stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

public class PostSteps {

	private WebDriver driver = DriverManager.getInstance();
	private HomePage homePage = new HomePage(driver);
	private LoginPage loginPage = new LoginPage(driver);
	private DashboardPage dashboardPage = new DashboardPage(driver);
	private Navbar navbar = new Navbar(driver);
	private PostsPage postsPage = new PostsPage(driver);

	@Given("user is logged in")
	public void user_is_logged_in(DataTable dataTable) throws InterruptedException {
		Map<String, String> credentials = dataTable.asMap();
		String username = credentials.get("username");
		String password = credentials.get("password");

		homePage.navigate();
		homePage.clickOnLogin();

		loginPage.login(username, password);
		Keywords.wait(2);
		dashboardPage.validatePageload();
	}

	@When("user navigates to the Posts page")
	public void user_navigates_to_the_posts_page() {
		navbar.navigateToPostsPage();
		postsPage.validatePageload();
	}

	@When("user enters the post content")
	public void user_enters_the_post_content(DataTable dataTable) {
		Map<String, String> data = dataTable.asMaps().get(0);
		String content = data.get("content") + " - " + Keywords.getTimeStamp();

		postsPage.enterPostContent(content);
	}

	@When("user clicks on the submit button")
	public void user_clicks_on_the_submit_button() {
		postsPage.submitPost();
	}

	@Then("user should see a success alert that says [Post Created]")
	public void user_should_see_a_success_alert_that_says() {
		postsPage.validateCreatePost();
	}

}
