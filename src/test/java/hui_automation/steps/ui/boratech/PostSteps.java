package hui_automation.steps.ui.boratech;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hui_automation.utilities.DriverManager;
import hui_automation.utilities.PageManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PostSteps {

	private WebDriver driver = DriverManager.getInstance();
	private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	private PageManager pages = PageManager.getInstance();

	@When("user navigates to the Posts page")
	public void user_navigates_to_the_posts_page() {
		pages.navbar().navigateToPostsPage();
		wait.until(ExpectedConditions.urlContains("posts"));
		pages.postsPage().isPageLoaded();
	}

	@When("user enters the post content")
	public void user_enters_the_post_content(DataTable dataTable) {
		Map<String, String> data = dataTable.asMaps().get(0);
		String content = data.get("content") + " " + Testkeys.getTimestamp();
		pages.postsPage().writePost(content);
	}

	@When("user clicks on the Submit button")
	public void user_clicks_on_the_submit_button() {
		pages.postsPage().submitPost();
	}

	@Then("user should see a success alert")
	public void user_should_see_a_success_alert_that_says() {
		pages.postsPage().validatePost();
	}

}
