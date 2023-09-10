package hui_automation.steps.api;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import hui_automation.utilities.DataManager;

public class Hooks {

	@Before("@API or @api or @E2E or @e2e")
	public void setup() {
		DataManager.getInstance();
	}

	@After("@API or @api or @E2E or @e2e")
	public void cleanup() {
		DataManager.reset();
	}

}
