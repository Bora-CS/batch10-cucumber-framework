package hui_automation.pages.bora_tech;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import hui_automation.pojos.Education;

public class AddEducationPage {

	private WebDriver driver;

	private final String URL = "https://boratech-practice-app.onrender.com/add-education";
	private final String TITLE_TEXT = "Add Your Education";

	@FindBy(xpath = "//h1[@class='large text-primary']")
	private WebElement titleText;

	@FindBy(name = "school")
	private WebElement schoolInput;

	@FindBy(name = "degree")
	private WebElement degreeInput;

	@FindBy(name = "fieldofstudy")
	private WebElement fieldofstudyInput;

	@FindBy(name = "from")
	private WebElement fromDateInput;

	@FindBy(name = "to")
	private WebElement toDateInput;

	@FindBy(name = "current")
	private WebElement currentCheckBox;

	@FindBy(xpath = "//textarea[@name='description']")
	private WebElement programDescriptionInput;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement submitButton;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	private List<WebElement> errorAlerts;

	private Education education;

	public AddEducationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void addEducation(Education education) {
		this.education = education;
		schoolInput.sendKeys(education.school);
		degreeInput.sendKeys(education.degree);
		fieldofstudyInput.sendKeys(education.fieldofstudy);
		fromDateInput.sendKeys(education.fromDate);
		if (education.current)
			currentCheckBox.click();
		else
			toDateInput.sendKeys(education.toDate);
		programDescriptionInput.sendKeys(education.programDescription);
		submitButton.click();
	}

	public void isPageLoaded() {
		assertEquals(URL, driver.getCurrentUrl());
		assertEquals(TITLE_TEXT, titleText.getText());
	}

	public void hasAddEducationFailed() {
		assertTrue(this.education.ErrorMessages.size() > 0);
		List<String> expectedErrorMsgs = this.education.ErrorMessages;
		List<String> actualErrorMsgs = new ArrayList<>();
		for (WebElement alert : errorAlerts)
			actualErrorMsgs.add(alert.getText());
		Collections.sort(expectedErrorMsgs);
		Collections.sort(actualErrorMsgs);
		assertEquals(expectedErrorMsgs, actualErrorMsgs);
	}

}
