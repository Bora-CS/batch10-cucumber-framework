package api_stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.DataManager;
import utilities.DriverManager;

public class Hooks {

	@Before("@API or @api")
	public void setup() {
		DataManager.getInstance();
	}

	@After("@API or @api")
	public void cleanup() {
		DataManager.cleanup();
	}

}
