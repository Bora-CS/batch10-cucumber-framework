package hui_automation.steps.api;

import hui_automation.utilities.DataManager;
import io.cucumber.java.After;

public class Hooks {

	@After("@api")
	public void cleanup() {
		DataManager.tearDown();
	}

}
