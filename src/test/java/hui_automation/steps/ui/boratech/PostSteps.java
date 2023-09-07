package hui_automation.steps.ui.boratech;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hui_automation.pages.bora_tech.Navbar;
import hui_automation.pages.bora_tech.PostsPage;
import hui_automation.utilities.DriverManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class PostSteps {

	private WebDriver driver = DriverManager.getInstance();
	private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	private Navbar navbar = new Navbar(driver);
	private PostsPage postsPage = new PostsPage(driver);

	@When("user navigates to the Posts page")
	public void user_navigates_to_the_posts_page() {
		navbar.navigateToPostsPage();
		wait.until(ExpectedConditions.urlContains("posts"));
		postsPage.isPageLoaded();
	}

	@When("user enters the post content")
	public void user_enters_the_post_content(DataTable dataTable) {
		Map<String, String> data = dataTable.asMaps().get(0);
		String content = data.get("content") + " " + Testkeys.getUniqueMillsTimeStr();
		postsPage.writePost(content);
	}

	@When("user clicks on the Submit button")
	public void user_clicks_on_the_submit_button() {
		postsPage.submitPost();
	}

	@Then("user should see a success alert")
	public void user_should_see_a_success_alert_that_says() {
		postsPage.validatePost();
	}

}
