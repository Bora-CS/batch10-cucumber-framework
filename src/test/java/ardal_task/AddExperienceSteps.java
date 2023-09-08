package ardal_task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.Experience;
import utilities.Keywords;
import utilities.PageManager;

public class AddExperienceSteps {
	private PageManager pages = PageManager.getInstance();

	@When("user clicks on [Add Experience] button")
	public void user_clicks_on_add_experience_button() {
		pages.dashboardPage().clickOnAddExperience();
	}

	@Then("user should be on the add experience page")
	public void user_should_be_on_the_add_experience_page() {
		Keywords.waitWithoutTry(2);
		pages.addExperiencePage().validatePageload();
	}

	@Then("user enters [Experience] content")
	public void user_enters_experience_content(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		Experience experience = new Experience(data.get("title"), data.get("company"), data.get("location"),
				data.get("from"), data.get("to"), false, data.get("description"));
		pages.addExperiencePage().enterExperienceContent(experience);
		pages.addExperiencePage().submitExperience();
	}

	@Then("user should be on the DashBoard page")
	public void user_should_be_on_the_dash_board_page() {
		Keywords.waitWithoutTry(3);
		pages.dashboardPage().validatePageload();
	}

	@Then("user should see errors pop up on screen")
	public void user_should_see_errors_pop_up_on_screen(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		String messages = data.get("error");
		String[] errorMessage = messages.split(",");
		List<String> expectedErrors = new ArrayList<>(Arrays.asList(errorMessage));
		pages.addExperiencePage().errorValidation(expectedErrors);
	}

}
