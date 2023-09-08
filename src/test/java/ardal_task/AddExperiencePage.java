package ardal_task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pojo.Experience;

public class AddExperiencePage {

//	Local Variable
	private WebDriver driver;
	private final String URL = "https://boratech-practice-app.onrender.com/add-experience";
	private final String TITLE_TEXT = "Add An Experience";

//	Elements

	@FindBy(xpath = "//h1[@class='large text-primary']")
	private WebElement titleText;

	@FindBy(name = "title")
	private WebElement jobTitle;

	@FindBy(name = "company")
	private WebElement company;

	@FindBy(name = "location")
	private WebElement location;

	@FindBy(name = "from")
	private WebElement from;

	@FindBy(name = "current")
	private WebElement current;

	@FindBy(name = "to")
	private WebElement to;

	@FindBy(name = "description")
	private WebElement description;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement submitButton;

	@FindBy(xpath = "//a[@class='btn btn-light my-1']")
	private WebElement goBackButton;

	private By errorAlerts = By.xpath("//div[@class='alert alert-danger']");

//	Constructor

	public AddExperiencePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	Actions

	public void validatePageload() {
		assertEquals(URL, driver.getCurrentUrl());
		assertEquals(TITLE_TEXT, titleText.getText());
	}

	public void enterExperienceContent(Experience experience) {
		jobTitle.sendKeys(experience.jobTitle);
		company.sendKeys(experience.company);
		location.sendKeys(experience.location);
		from.sendKeys(experience.from);
		if (experience.current) {
			current.click();
		}
		to.sendKeys(experience.to);
		description.sendKeys(experience.description);
	}

	public void submitExperience() {
		submitButton.click();
	}

	public void errorValidation(List<String> expectedErrors) {
		List<WebElement> actualError = driver.findElements(errorAlerts);
		List<String> actualErrors = new ArrayList<>();
		for (WebElement errorAlert : actualError) {
			actualErrors.add(errorAlert.getText());
		}

		assertTrue(actualErrors.size() == expectedErrors.size(), "Error messages size dosen't match");
		boolean found = false;
		for (String acterror : actualErrors) {
			for (String experror : expectedErrors) {
				if (acterror.equals(experror)) {
					found = true;
					break;
				}
			}
			if(found) {
				break;
			}
		}
		assertTrue(found,"Expected error message not found");

	}

}
