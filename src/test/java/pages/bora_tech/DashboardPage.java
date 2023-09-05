package pages.bora_tech;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

	// Local Variables
	private WebDriver driver;
	private final String URL = "https://boratech-practice-app.onrender.com/dashboard";
	private final String TITLE_TEXT = "Dashboard";

	// Elements
	private By titleText = By.xpath("//h1[@class='large text-primary']");

	// Constructor
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	// Actions
	public void isPageLoaded() {
		assertEquals(URL, driver.getCurrentUrl());
		assertEquals(TITLE_TEXT, driver.findElement(titleText).getText());
	}

}
