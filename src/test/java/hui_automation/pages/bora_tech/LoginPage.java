package hui_automation.pages.bora_tech;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	// Local Variables
	private WebDriver driver;
	private WebDriverWait wait;

	private final String URL = "https://boratech-practice-app.onrender.com/login";
	private final String TITLE_TEXT = "Sign In";

	// Elements
	@FindBy(xpath = "//h1[@class='large text-primary']")
	private WebElement titleText;

	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailInput;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement passwordInput;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	private WebElement errorAlert;

	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void isPageLoaded() {
		ExpectedCondition<?>[] conditions = new ExpectedCondition[2];
		ExpectedCondition<Boolean> condition1 = ExpectedConditions.urlToBe(URL);
		ExpectedCondition<Boolean> condition2 = ExpectedConditions.textToBePresentInElement(titleText, TITLE_TEXT);
		conditions[0] = condition1;
		conditions[1] = condition2;
		wait.until(ExpectedConditions.and(conditions));
	}

	public void login(String email, String password) {
		emailInput.sendKeys(email);
		passwordInput.sendKeys(password);
		loginButton.click();
	}

	public void hasLoginFailed(String expectedErrorText) {
		assertEquals(URL, driver.getCurrentUrl());
		assertEquals(expectedErrorText, errorAlert.getText());
	}

}
