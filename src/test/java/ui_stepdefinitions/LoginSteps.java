package ui_stepdefinitions;

import io.cucumber.java.en.*;
import utilities.Keywords;
import utilities.PageManager;

public class LoginSteps {

	private PageManager pages = PageManager.getInstance();

	@When("user enters the username - {string} and password - {string} and submit")
	public void user_enters_the_username_and_password_and_submit(String username, String password)
			throws InterruptedException {
		pages.loginPage().login(username, password);
		Keywords.wait(2);
	}

	@Then("user should see a login error")
	public void user_should_see_a_login_error() {
		pages.loginPage().validateErrorState();
	}
}
