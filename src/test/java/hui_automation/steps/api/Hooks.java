package hui_automation.steps.api;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import hui_automation.utilities.DataManager;

public class Hooks {

	@Before("@api")
	public void setup() {
		DataManager.getInstance();
	}

	@After("@api")
	public void cleanup() {
		DataManager.reset();
	}

}
