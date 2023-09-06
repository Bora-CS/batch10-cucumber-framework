package pages.bora_tech;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	// Local Variables
	private WebDriver driver;
	private final String URL = "https://boratech-practice-app.onrender.com/";
	private final String TITLE_TEXT = "BoraTech";

	// Elements
	@FindBy(xpath = "//h1[@class='x-large']")
	private WebElement titleText;

	@FindBy(xpath = "//section//a[@href='/register']")
	private WebElement signUpButton;

	@FindBy(xpath = "//section//a[@href='/login']")
	private WebElement loginButton;

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void navigate() {
		driver.get(URL);
	}

	public void isPageLoaded() {
		assertEquals(TITLE_TEXT, titleText.getText());
	}

	public void clickOnLogin() {
		loginButton.click();
	}

	public void clickOnSignUp() {
		signUpButton.click();
	}

}
