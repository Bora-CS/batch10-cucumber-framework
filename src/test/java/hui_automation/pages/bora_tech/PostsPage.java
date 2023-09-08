package hui_automation.pages.bora_tech;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PostsPage {
	// Local Variables
	private WebDriver driver;
	private WebDriverWait wait;
	private final String URL = "https://boratech-practice-app.onrender.com/posts";
	private final String TITLE_TEXT = "Posts";

	// Elements
	@FindBy(xpath = "//h1[@class='large text-primary']")
	private WebElement titleText;

	@FindBy(tagName = "textarea")
	private WebElement textBox;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement submitButton;

	@FindBy(xpath = "//div[@class='alert alert-success']")
	private WebElement successAlert;

	// Constructor
	public PostsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void isPageLoaded() {
		assertEquals(URL, driver.getCurrentUrl());
		assertEquals(TITLE_TEXT, titleText.getText());
	}

	public void writePost(String postContent) {
		textBox.sendKeys(postContent);
	}

	public void submitPost() {
		submitButton.click();
	}

	public void validatePost() {
		wait.until(ExpectedConditions.visibilityOf(successAlert));
		assertEquals("Post Created", successAlert.getText(), "Failed to create post.");
	}

}
