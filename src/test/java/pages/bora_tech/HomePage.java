package pages.bora_tech;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	// Local Variables
	private WebDriver driver;
	private final String URL = "https://boratech-practice-app.onrender.com/";
	private final String TITLE_TEXT = "BoraTech";

	// Elements
	private By signUpButton = By.xpath("//section//a[@href='/register']");
	private By loginButton = By.xpath("//section//a[@href='/login']");
	private By titleText = By.xpath("//h1[@class='x-large']");

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	// Actions
	public void navigate() {
		driver.navigate().to(URL);
	}

	public void isPageLoaded() {
		assertEquals(TITLE_TEXT, driver.findElement(titleText).getText());
	}

	public void clickOnLogin() {
		driver.findElement(loginButton).click();
	}

	public void clickOnSignUp() {
		driver.findElement(signUpButton).click();
	}

}
