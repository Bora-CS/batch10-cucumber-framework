package page_objects;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

	// Local Variables
	private WebDriver driver;
	private final String URL = "https://boratech-practice-app.onrender.com/dashboard";
	private final String TITLE_TEXT = "Dashboard";

	// Elements
	@FindBy(xpath = "//h1[@class='large text-primary']")
	private WebElement titleText;

	@FindBy(xpath = "//a[@href='/add-experience']")
	private WebElement addExperienceLink;

	@FindBy(xpath = "//div[@class='alert alert-success']")
	private WebElement successAlert;

	// Constructor
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void validatePageload() {
		assertEquals(URL, driver.getCurrentUrl());
		assertEquals(TITLE_TEXT, titleText.getText());
	}

	public void initiateAddExperience() {
		addExperienceLink.click();
	}

	public void validateSuccessAlert(String alertText) {
		assertTrue(successAlert.isDisplayed());
		assertEquals(alertText, successAlert.getText());
	}

}
