package page_objects;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.datatable.DataTable;

public class ExperiencePage {

	// Local Variables
	private WebDriver driver;
	private final String URL = "https://boratech-practice-app.onrender.com/add-experience";
	private final String TITLE_TEXT = "Add An experience";

	// Elements
	@FindBy(xpath = "//a[@href='/add-experience']")
	private WebElement ExpButton;

	@FindBy(xpath = "//h1[@class='large text-primary']")
	private WebElement titleText;

	@FindBy(tagName = "textarea")
	private WebElement textBox;

	@FindBy(xpath = "//input[@name='title']")
	private WebElement titleInput;

	@FindBy(xpath = "//input[@name='company']")
	private WebElement companyInput;

	@FindBy(xpath = "//input[@name='location']")
	private WebElement locationInput;

	@FindBy(xpath = "//input[@name='from']")
	private WebElement fromDateInput;

	@FindBy(xpath = "//input[@name='current']")
	private WebElement currentCheckBox;

	@FindBy(xpath = "//input[@name='to']")
	private WebElement toDateInput;

	@FindBy(xpath = "//textarea[@name='description']")
	private WebElement descriptionInput;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement submitButton;

	@FindBy(xpath = "//div[@class='alert alert-success']")
	private WebElement alertSuccess;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	private List<WebElement> errorMessages;

	// Constructor
	public ExperiencePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void experienceButton() {
		ExpButton.click();
	}

	public void validateExpPage() {
		assertEquals(URL, driver.getCurrentUrl());
		assertEquals(TITLE_TEXT, titleText.getText());
	}

	public void enterExpContent(DataTable datatable) {
		Map<String, String> data = datatable.asMap();
		if (data.get("title") != null)
			titleInput.sendKeys(data.get("title"));
		if (data.get("company") != null)
			companyInput.sendKeys(data.get("company"));
		locationInput.sendKeys(data.get("location"));
		if (data.get("from") != null)
			fromDateInput.sendKeys(data.get("from"));
		if (data.get("current").equals("true")) {
			currentCheckBox.click();
		} else {
			toDateInput.sendKeys(data.get("to"));
		}
		descriptionInput.sendKeys(data.get("description"));
		submitButton.click();
	}

	public void submitExperience() {
		submitButton.click();
	}

	public void validateExperiencePost() {
		if (alertSuccess.getText().equals("Experience Added")) {
			System.out.println("TEST PASSED!!!! We see your experience post!");
		} else {
			System.out.println("FAILED");
		}
	}

	public void validateErrorAlert(DataTable dataTable) throws Exception {
		List<String> actualErrors = new ArrayList<>();
//Returns true if this list contains no elements. (.isEmpty)
		if (!errorMessages.isEmpty()) {
			for (WebElement errorAlert : errorMessages) {
				actualErrors.add(errorAlert.getText());
			}

			if (actualErrors.size() != errorMessages.size()) {
				String message = "Total errors = " + errorMessages + "Actual errors = "
						+ actualErrors;
				throw new Exception(message);
			}

			for (WebElement errorMessages : errorMessages) {
				if (!actualErrors.contains(errorMessages.getText())) {
					throw new Exception("Expected error was not found: " + errorMessages.getText());
				} else {
	                System.out.println("Expected errors MATCHED!!!! ==> " + errorMessages.getText());
	            }
			}
		} else {
			throw new Exception("No error message found!");
		}
	}
}