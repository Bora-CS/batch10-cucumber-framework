package ui_stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.DataManager;
import utilities.DriverManager;
import utilities.PageManager;

public class Hooks {

	@Before("@UI or @ui")
	public void setup() {
		DriverManager.getInstance();
		DataManager.getInstance();
		PageManager.getInstance();
	}

	@After("@UI or @ui")
	public void cleanup() {
		DriverManager.teardown();
		DataManager.cleanup();
		PageManager.cleanup();
	}

}
