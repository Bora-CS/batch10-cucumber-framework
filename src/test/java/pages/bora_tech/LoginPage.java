package pages.bora_tech;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	// Local Variables
	private WebDriver driver;
	private final String URL = "https://boratech-practice-app.onrender.com/login";
	private final String TITLE_TEXT = "Sign In";

	// Elements
	private By titleText = By.xpath("//h1[@class='large text-primary']");
	private By emailInput = By.xpath("//input[@name='email']");
	private By passwordInput = By.xpath("//input[@name='password']");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By errorAlert = By.xpath("//div[@class='alert alert-danger']");

	// Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Actions
	public void login(String email, String password) {
		driver.findElement(emailInput).sendKeys(email);
		driver.findElement(passwordInput).sendKeys(password);
		driver.findElement(loginButton).click();
	}

	public void isPageLoaded() {
		assertEquals(URL, driver.getCurrentUrl());
		assertEquals(TITLE_TEXT, driver.findElement(titleText).getText());
	}

	public void isLoginFailed(String expectedErrorText) {
		assertEquals(URL, driver.getCurrentUrl());
		assertEquals(expectedErrorText, driver.findElement(errorAlert).getText());
	}

}
