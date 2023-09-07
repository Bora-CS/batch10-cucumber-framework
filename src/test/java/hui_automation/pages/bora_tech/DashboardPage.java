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

import hui_automation.pojos.Education;
import hui_automation.pojos.Experience;

public class DashboardPage {

	// Local Variables
	private WebDriver driver;
	private WebDriverWait wait;
	private final String URL = "https://boratech-practice-app.onrender.com/dashboard";
	private final String TITLE_TEXT = "Dashboard";

	// Elements
	@FindBy(xpath = "//h1[@class='large text-primary']")
	private WebElement titleText;

	@FindBy(xpath = "//a[@href='/edit-profile']")
	private WebElement editProfileLink;

	@FindBy(xpath = "//a[@href='/add-experience']")
	private WebElement addExperienceLink;

	@FindBy(xpath = "//a[@href='/add-education']")
	private WebElement addEducationLink;

	@FindBy(xpath = "//h2[text()='Experience Credentials']/following-sibling::table[1]/tbody/tr")
	private List<WebElement> experienceTableRows;

	@FindBy(xpath = "//h2[text()='Education Credentials']/following-sibling::table[1]/tbody/tr")
	private List<WebElement> educationTableRows;

	@FindBy(xpath = "//div[@class='alert alert-success']")
	private WebElement deleteSuccessAlert;

	@FindBy(xpath = "//h2[text()='Experience Credentials']/following-sibling::table[1]/tbody/tr//button[@class='btn btn-danger']")
	private List<WebElement> experienceDeleteButtons;

	@FindBy(xpath = "//h2[text()='Education Credentials']/following-sibling::table[1]/tbody/tr//button[@class='btn btn-danger']")
	private List<WebElement> educationDeleteButtons;

	// Constructor
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void isPageLoaded() {
		assertEquals(URL, driver.getCurrentUrl());
		assertEquals(TITLE_TEXT, titleText.getText());
	}

	public void clickOnAddExperience() {
		addExperienceLink.click();
	}

	public void clickOnAddEducation() {
		addEducationLink.click();
	}

	public void validateAddExperience(Experience experience) {
		wait.until(ExpectedConditions.visibilityOfAllElements(experienceTableRows));
		boolean targetFound = false;
		for (WebElement row : experienceTableRows) {
			String actualCompany = row.findElement(By.xpath("td[1]")).getText();
			String actualJobTitle = row.findElement(By.xpath("td[2]")).getText();
			if (actualCompany.equals(experience.company) && actualJobTitle.equals(experience.jobTitle)) {
				targetFound = true;
				break;
			}
		}
		assertTrue(targetFound);
	}

	public void validateAddEducation(Education education) {
		wait.until(ExpectedConditions.visibilityOfAllElements(educationTableRows));
		boolean targetFound = false;
		for (WebElement row : educationTableRows) {
			String actualSchool = row.findElement(By.xpath("td[1]")).getText();
			String actualDegree = row.findElement(By.xpath("td[2]")).getText();
			if (actualSchool.equals(education.school) && actualDegree.equals(education.degree)) {
				targetFound = true;
				break;
			}
		}
		assertTrue(targetFound);
	}

	public void deleteAllExperiences() {
		while (experienceDeleteButtons.size() > 0) {
			for (WebElement button : experienceDeleteButtons) {
				wait.until(ExpectedConditions.visibilityOf(button)).click();
				wait.until(ExpectedConditions.visibilityOf(deleteSuccessAlert));
				assertEquals("Experience Removed", deleteSuccessAlert.getText());
				wait.until(ExpectedConditions.invisibilityOf(deleteSuccessAlert));
				continue;
			}
		}
		assertEquals(0, experienceDeleteButtons.size());
	}

}
