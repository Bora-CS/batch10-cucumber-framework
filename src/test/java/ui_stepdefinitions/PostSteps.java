package ui_stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import utilities.DriverManager;

public class PostSteps {

	private WebDriver driver = DriverManager.getInstance();

	@Given("user is logged in")
	public void user_is_logged_in(DataTable dataTable) {
		Map<String, String> credentials = dataTable.asMap();
		String username = credentials.get("username");
		String password = credentials.get("password");

		// write code here to actually login
	}

	@When("user navigates to the Posts page")
	public void user_navigates_to_the_posts_page() {

	}

	@When("user enters the post content")
	public void user_enters_the_post_content(DataTable dataTable) {
		Map<String, String> data = dataTable.asMaps().get(0);
		String content = data.get("content");

		// use the content to create post
	}

	@When("user clicks on the submit button")
	public void user_clicks_on_the_submit_button() {

	}

	@Then("user should see a success alert that says {string}")
	public void user_should_see_a_success_alert_that_says(String successAlertText) {
		try {
			driver.findElement(By.xpath("//*[contains(text(), " + successAlertText + ")]"));
		} catch (NoSuchElementException e) {
			assertTrue(false, "Expected to see an success alert, but not found.");
		}
		assertTrue(true);
	}

}
