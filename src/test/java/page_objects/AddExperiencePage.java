package page_objects;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import apiPojos.Experience;
import utilities.Keywords;

public class AddExperiencePage {

	// Local Variables
	private WebDriver driver;
	private final String URL = "https://boratech-practice-app.onrender.com/add-experience";
	private final String TITLE_TEXT = "Add An Experience";

	// Elements
	@FindBy(xpath = "//h1[@class='large text-primary']")
	private WebElement titleText;

	@FindBy(xpath = "//input[@name='title']")
	private WebElement titleInput;

	@FindBy(xpath = "//input[@name='company']")
	private WebElement companyInput;

	@FindBy(xpath = "//input[@name='location']")
	private WebElement locationInput;

	@FindBy(xpath = "//input[@name='from']")
	private WebElement fromDateInput;

	@FindBy(xpath = "//input[@name='current']")
	private WebElement currentCheckbox;

	@FindBy(xpath = "//input[@name='to']")
	private WebElement toDateInput;

	@FindBy(xpath = "//textarea[@name='description']")
	private WebElement descriptionInput;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement submitButton;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	private List<WebElement> errorAlerts;

	// Constructor
	public AddExperiencePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void validatePageload() {
		assertEquals(URL, driver.getCurrentUrl());
		assertEquals(TITLE_TEXT, titleText.getText());
	}

	public void addExperience(Experience experience) {
		titleInput.sendKeys(experience.title);
		companyInput.sendKeys(experience.company);
		locationInput.sendKeys(experience.location);
		fromDateInput.sendKeys(experience.from);
		if (experience.current) {
			currentCheckbox.click();
		} else {
			toDateInput.sendKeys(experience.to);
		}
		descriptionInput.sendKeys(experience.description);
		submitButton.click();
	}

	public void errorValidation(List<String> expectedErrors) {
		List<String> actualErrors = new ArrayList<>();

		for (WebElement errorAlert : errorAlerts) {
			actualErrors.add(errorAlert.getText());
		}

		Collections.sort(expectedErrors);
		Collections.sort(actualErrors);
		assertEquals(expectedErrors, actualErrors);
	}

}
