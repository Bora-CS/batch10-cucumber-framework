package hui_automation.pages.bora_tech;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hui_automation.pojos.Experience;

public class AddExperiencePage {

	private WebDriver driver;
	private WebDriverWait wait;

	private final String URL = "https://boratech-practice-app.onrender.com/add-experience";
	private final String TITLE_TEXT = "Add An Experience";

	@FindBy(xpath = "//h1[@class='large text-primary']")
	private WebElement titleText;

	@FindBy(name = "title")
	private WebElement jobTitleInput;

	@FindBy(name = "company")
	private WebElement companyInput;

	@FindBy(name = "location")
	private WebElement locationInput;

	@FindBy(name = "from")
	private WebElement fromDateInput;

	@FindBy(name = "to")
	private WebElement toDateInput;

	@FindBy(name = "current")
	private WebElement currentCheckBox;

	@FindBy(xpath = "//textarea[@name='description']")
	private WebElement jobDescriptionInput;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement submitButton;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	private List<WebElement> errorAlerts;

	private Experience experience;

	public AddExperiencePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public void isPageLoaded() {
		ExpectedCondition<?>[] conditions = new ExpectedCondition[2];
		ExpectedCondition<Boolean> condition1 = ExpectedConditions.urlToBe(URL);
		ExpectedCondition<Boolean> condition2 = ExpectedConditions.textToBePresentInElement(titleText, TITLE_TEXT);
		conditions[0] = condition1;
		conditions[1] = condition2;
		wait.until(ExpectedConditions.and(conditions));
	}

	public void addExperience(Experience experience) {
		this.experience = experience;
		jobTitleInput.sendKeys(experience.jobTitle);
		companyInput.sendKeys(experience.company);
		locationInput.sendKeys(experience.location);
		fromDateInput.sendKeys(experience.fromDate);
		if (experience.current)
			currentCheckBox.click();
		else
			toDateInput.sendKeys(experience.toDate);
		jobDescriptionInput.sendKeys(experience.jobDescription);
		submitButton.click();
	}

	public void hasAddExperienceFailed() {
		List<String> actualErrorMsgs = new ArrayList<>();
		List<String> expectedErrorMsgs = this.experience.errorTexts;
		for (WebElement alert : errorAlerts)
			actualErrorMsgs.add(alert.getText());
		Collections.sort(expectedErrorMsgs);
		Collections.sort(actualErrorMsgs);
		assertEquals(expectedErrorMsgs, actualErrorMsgs);
	}

}
