package page_objects;

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
	@FindBy(xpath = "//section//a[@href='/register']")
	private WebElement signUpButton;

	@FindBy(xpath = "//section//a[@href='/login']")
	private WebElement loginButton;

	@FindBy(xpath = "//h1[@class='x-large']")
	private WebElement titleText;

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void navigate() {
		driver.navigate().to(URL);
	}

	public void validatePageload() {
		assertEquals(TITLE_TEXT, titleText.getText());
	}

	public void clickOnLogin() {
		loginButton.click();
	}

	public void clickOnSignUp() {
		signUpButton.click();
	}

}
