package hui_automation.pages.bora_tech;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hui_automation.api_pojos.Post;

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

	@FindBy(xpath = "//div[@class='posts']/div[contains(@class,'post')]")
	private List<WebElement> postContainers;

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

	public void validateDeletePost() {
		assertTrue(successAlert.isDisplayed());
		assertEquals("Post Removed", successAlert.getText());
	}

	public WebElement findAndValidatePost(Post post) {
		boolean found = false;
		WebElement deleteButton = null;
		for (WebElement postContainer : postContainers) {
			String userName = postContainer.findElement(By.tagName("h4")).getText();
			String content = postContainer.findElement(By.tagName("p")).getText();
			List<WebElement> buttons = postContainer.findElements(By.tagName("button"));
			if (post.text.equals(content) && post.name.equals(userName) && buttons.size() == 3) {
				deleteButton = buttons.get(2);
				found = true;
				break;
			}
		}
		assertTrue(found, "Expected post was not found - User: " + post.name + " Content: " + post.text);
		return deleteButton;
	}

}
