package hui_automation.pages.bora_tech;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import hui_automation.pojos.Experience;

public class AddExperiencePage {

	private WebDriver driver;

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
		PageFactory.initElements(driver, this);
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

	public void isPageLoaded() {
		assertEquals(URL, driver.getCurrentUrl());
		assertEquals(TITLE_TEXT, titleText.getText());
	}

	public void hasAddExperienceFailed(List<String> expectedErrorMsgs) {
		List<String> actualErrorMsgs = new ArrayList<>();
		for (WebElement alert : errorAlerts)
			actualErrorMsgs.add(alert.getText());
		Collections.sort(expectedErrorMsgs);
		Collections.sort(actualErrorMsgs);
		assertEquals(expectedErrorMsgs, actualErrorMsgs);
	}

}
