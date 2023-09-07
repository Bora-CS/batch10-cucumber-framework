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
	private WebElement successAlert;

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

	private WebElement[] getCells(WebElement row, int num) {
		WebElement[] cells = new WebElement[num];
		for (int i = 0; i < cells.length; i++)
			cells[i] = row.findElement(By.xpath(String.format("td[%d]", i + 1)));
		return cells;
	}

	public void validateAddExperience(Experience experience) {
		wait.until(ExpectedConditions.visibilityOfAllElements(experienceTableRows));
		boolean targetFound = false;
		for (WebElement row : experienceTableRows) {
			String actualCompany = getCells(row, 2)[0].getText();
			String actualJobTitle = getCells(row, 2)[1].getText();
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
			String actualSchool = getCells(row, 2)[0].getText();
			String actualDegree = getCells(row, 2)[1].getText();
			if (actualSchool.equals(education.school) && actualDegree.equals(education.degree)) {
				targetFound = true;
				break;
			}
		}
		assertTrue(targetFound);
	}

	private WebElement findDeleteButton(WebElement row) {
		return row.findElement(By.tagName("button"));
	}

	public void deleteExperience(Experience experience) {
		assertTrue(experienceTableRows.size() > 0);
		boolean isTargetDeleted = false;
		for (WebElement row : experienceTableRows) {
			String company = getCells(row, 2)[0].getText();
			String jobTitle = getCells(row, 2)[1].getText();
			WebElement deleteExperienceButton = findDeleteButton(row);
			if (company.equals(experience.company) && jobTitle.equals(experience.jobTitle)) {
				deleteExperienceButton.click();
				isTargetDeleted = true;
				System.out.println(String.format("Delete Experience[%s, %s]", company, jobTitle));
				break;
			}
		}
		assertTrue(isTargetDeleted, "Target experience was not found or failed to be deleted.");
	}

	public void deleteEducation(Education education) {
		assertTrue(educationTableRows.size() > 0);
		boolean isTargetDeleted = false;
		for (WebElement row : educationTableRows) {
			String school = getCells(row, 2)[0].getText();
			String degree = getCells(row, 2)[1].getText();
			WebElement deleteEducationButton = findDeleteButton(row);
			if (school.equals(education.school) && degree.equals(education.degree)) {
				deleteEducationButton.click();
				isTargetDeleted = true;
				System.out.println(String.format("Delete Education[%s, %s]", school, degree));
				break;
			}
		}
		assertTrue(isTargetDeleted, "Target education was not found or failed to be deleted.");
	}

}
