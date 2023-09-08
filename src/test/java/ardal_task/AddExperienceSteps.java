package ardal_task;

import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.Experience;
import utilities.Keywords;
import utilities.PageManager;

public class AddExperienceSteps {
	private PageManager pages = PageManager.getInstance();
	
	@Given("user Ardal is logged in")
	public void user_ardal_is_logged_in(DataTable dataTable) {
		 Map<String, String> data = dataTable.asMap();
	    pages.homePage().navigate();
	    pages.homePage().clickOnLogin();
	    String email = data.get("email");
	    String password = data.get("password");
	    pages.loginPage().login(email, password);
		
		
	}

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
		Experience experience = new Experience(data.get("title"), data.get("company"), data.get("location"), data.get("from"), data.get("to"), false, data.get("description"));
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
	 
	}
	
	
	
	
	
	
	
}
