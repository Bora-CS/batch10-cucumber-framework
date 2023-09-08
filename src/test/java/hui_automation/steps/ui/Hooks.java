package hui_automation.steps.ui;

import hui_automation.utilities.DataManager;
import hui_automation.utilities.DriverManager;
import hui_automation.utilities.PageManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

	@Before(order = 3, value = "@UI or @ui or @Chrome or @chrome or @Firefox or @firefox or @Edge or @edge")
	public void powerUp() {
		DriverManager.getInstance();
		DataManager.getInstance();
		PageManager.getInstance();
		System.out.println("Power up driver...");
	}

	@After("@UI or @ui or @Chrome or @chrome or @Firefox or @firefox or @Edge or @edge")
	public void tearDown() {
		DriverManager.reset();
		DataManager.reset();
		PageManager.reset();
		System.out.println("Power down driver...");
	}

}
