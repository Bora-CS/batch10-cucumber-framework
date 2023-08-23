package automation;

import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {

	@Given("user is on the boratech homepage")
	public void user_is_on_the_boratech_homepage() {
		System.out.println("==> Step 1");
	}

	@When("user navigates to the Login page")
	public void user_navigates_to_the_login_page() {
		System.out.println("==> Step 2");
	}

	@When("user enters the username - {string} and password - {string} and submit")
	public void user_enters_the_username_and_password_and_submit(String username, String password) {
		System.out.println("==> Step 3");
		System.out.println("==> Username: " + username);
		System.out.println("==> Password: " + password);
	}

	@Then("user should be on the Dashboard page")
	public void user_should_be_on_the_dashboard_page() {
		System.out.println("==> Step 4");
	}

}
