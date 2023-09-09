package ui_stepdefinitions;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import apiPojos.Post;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import page_objects.DashboardPage;
import page_objects.HomePage;
import page_objects.LoginPage;
import page_objects.Navbar;
import page_objects.PostsPage;
import utilities.DataManager;
import utilities.DriverManager;
import utilities.Keywords;
import utilities.PageManager;

public class PostSteps {

	private PageManager pages = PageManager.getInstance();
	private DataManager data = DataManager.getInstance();

	private WebElement deleteButton;

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

	@Then("user should see the post that was created previously")
	public void user_should_see_the_post_that_was_created_previously() throws InterruptedException {
		Post previouslyCreatedPost = data.getPost();
		deleteButton = pages.postsPage().findAndValidatePost(previouslyCreatedPost);
	}

	@When("user deletes the post that was created previously")
	public void user_deletes_the_post_that_was_created_previously() {
		deleteButton.click();
		pages.postsPage().validateDeletePost();
	}

}
