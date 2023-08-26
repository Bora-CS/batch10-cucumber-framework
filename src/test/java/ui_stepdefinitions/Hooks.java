package ui_stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.DriverManager;

public class Hooks {

	@Before
	public void setup() {
		DriverManager.getInstance();
	}

	@After
	public void cleanup() {
		DriverManager.teardown();
	}

}
