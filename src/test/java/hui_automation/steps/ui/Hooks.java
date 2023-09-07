package hui_automation.steps.ui;

import hui_automation.utilities.Configuration;
import hui_automation.utilities.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	@Before(order = 1, value = "@Chrome or @chrome")
	public void setupChrome() {
		Configuration.config("browser", "chrome");
		System.out.println("Open Chrome...");
	}

	@Before(order = 1, value = "@Firefox or @firefox")
	public void setupFirefox() {
		Configuration.config("browser", "firefox");
		System.out.println("Open Firefox...");
	}

	@Before(order = 1, value = "@Edge or @edge")
	public void setupEdge() {
		Configuration.config("browser", "edge");
		System.out.println("Open Edge...");
	}

	@Before(order = 2, value = "@UI or @ui or @Chrome or @chrome or @Firefox or @firefox or @Edge or @edge")
	public void setup() {
		DriverManager.getInstance();
		System.out.println("Driver...");
	}

	@After("@UI or @ui")
	public void tearDown() {
		DriverManager.reset();
		System.out.println("Quitting driver...");
	}

}
