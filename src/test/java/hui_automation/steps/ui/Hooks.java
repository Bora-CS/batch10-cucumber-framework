package hui_automation.steps.ui;

import hui_automation.utilities.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	@Before("@ui")
	public void setup() {
		DriverManager.getInstance();
	}

	@After("@ui")
	public void cleanup() {
		DriverManager.tearDown();
	}

}