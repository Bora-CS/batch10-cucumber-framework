package hui_automation.pages.bora_tech;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
	private List<WebElement> postCards;

	// Constructor
	public PostsPage(WebDriver driver) {
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

	public void refresh() {
		if (this.driver.getCurrentUrl().equals(URL))
			this.driver.navigate().refresh();
	}

	public void writePost(String postContent) {
		textBox.sendKeys(postContent);
	}

	public void submitPost() {
		submitButton.click();
	}

	public void validateSubmitPost() {
		wait.until(ExpectedConditions.visibilityOf(successAlert));
		assertEquals("Post Created", successAlert.getText(), "Failed to create a new post.");
	}

	public WebElement findAndValidatePost(Post postAPI) {
		boolean targetPostfound = false;
		WebElement deleteButton = null;
		for (WebElement card : postCards) {
			String userName = card.findElement(By.tagName("h4")).getText();
			String content = card.findElement(By.tagName("p")).getText();
			List<WebElement> buttons = card.findElements(By.tagName("button"));
			if (postAPI.text.equals(content) && postAPI.name.equals(userName) && buttons.size() == 3) {
				deleteButton = buttons.get(2);
				targetPostfound = true;
				break;
			}
		}

		assertTrue(targetPostfound,
				String.format("Target post was not found. Expected:[%s, %s]", postAPI.name, postAPI.text));
		return deleteButton;
	}

	public void deletePost(Post postAPI) {
		WebElement deleteButton = findAndValidatePost(postAPI);
		deleteButton.click();
	}

	public void validateDeletePost() {
		assertTrue(successAlert.isDisplayed());
		assertEquals("Post Removed", successAlert.getText(), "Deleted post is still present on Posts page.");
	}

}
