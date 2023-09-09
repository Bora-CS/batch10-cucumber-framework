package page_objects;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

	// Local Variables
	private WebDriver driver;
	private WebDriverWait wait;
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
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void validatePageload() {
		wait.until(ExpectedConditions.urlToBe(URL));
		wait.until(ExpectedConditions.textToBePresentInElement(titleText, TITLE_TEXT));
	}

	public void initiateAddExperience() {
		addExperienceLink.click();
	}

	public void validateSuccessAlert(String alertText) {
		assertTrue(successAlert.isDisplayed());
		assertEquals(alertText, successAlert.getText());
	}

}
