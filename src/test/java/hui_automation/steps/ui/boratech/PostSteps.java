package hui_automation.steps.ui.boratech;

import java.util.Map;

import org.openqa.selenium.WebElement;

import hui_automation.utilities.DataManager;
import hui_automation.utilities.PageManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PostSteps {

	private PageManager pages = PageManager.getInstance();
	private DataManager dataManager = DataManager.getInstance();

	@When("user navigates to the Posts page")
	public void user_navigates_to_the_posts_page() {
		pages.navbar().navigateToPostsPage();
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
		pages.postsPage().validateSubmitPost();
	}

	@Then("user sees the newly created post")
	public void user_sees_the_newly_created_post() {
		pages.postsPage().refresh();
		pages.postsPage().isPageLoaded();
		WebElement deleteButton = pages.postsPage().findAndValidatePost(dataManager.getPostAPI());
		dataManager.setElement(deleteButton);
	}

	@When("user deletes the newly created post")
	public void user_deletes_newly_created_post() {
		dataManager.getElement().click();
		pages.postsPage().validateDeletePost();
	}

}
