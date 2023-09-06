package page_objects;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	// Local Variables
	private WebDriver driver;
	private WebDriverWait wait;
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
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Actions
	public void login(String email, String password) {
		driver.findElement(emailInput).sendKeys(email);
		driver.findElement(passwordInput).sendKeys(password);
		driver.findElement(loginButton).click();
	}

	public void validateErrorState() {
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorAlert));
		assertEquals("Invalid credentials", error.getText());
	}

	public void validatePageload() {
		assertEquals(URL, driver.getCurrentUrl());
		assertEquals(TITLE_TEXT, driver.findElement(titleText).getText());
	}

}
