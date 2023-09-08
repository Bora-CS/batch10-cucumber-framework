package LydiaHomework907;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;

public class AddExperiencePage {

	// Local Variables
	private WebDriver driver;
	private WebDriverWait wait;

	// Elements
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
	private WebElement successAlert;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	private List<WebElement> errorAlerts;

	// Constructor
	public AddExperiencePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void addExperienceHappy(DataTable datatable) {
		Map<String, String> data = datatable.asMap();
		titleInput.sendKeys(data.get("title"));
		companyInput.sendKeys(data.get("company"));
		locationInput.sendKeys(data.get("location"));
		fromDateInput.sendKeys(data.get("from"));
		if (data.get("current").equals("true")) {
			currentCheckBox.click();
		} else {
			toDateInput.sendKeys(data.get("to"));
		}
		descriptionInput.sendKeys(data.get("description"));
		submitButton.click();

	}

	public void validateNewExperience() {
		if (successAlert.getText().equals("Experience Added")) {
			System.out.println("Experience Added!");
		} else {
			System.out.println("failed to add new experience");
		}

	}

	public void addExperienceUnhappy(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		try {

			titleInput.sendKeys(data.get("title"));
			companyInput.sendKeys(data.get("company"));
			locationInput.sendKeys(data.get("location"));
			fromDateInput.sendKeys(data.get("from"));
			if (data.get("current").equals("true")) {
				currentCheckBox.click();
			} else {
				toDateInput.sendKeys(data.get("to"));
			}
			descriptionInput.sendKeys(data.get("description"));
			submitButton.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateErrorAlert(DataTable dataTable) throws Exception {

		List<String> actualErrors = new ArrayList<>();
		for (WebElement errorAlert : errorAlerts) {
			actualErrors.add(errorAlert.getText());
		}

		// check to see we have the same number of errors
		if (actualErrors.size() != errorAlerts.size()) {
			String message = "Number of errors don't match\n" + "Expected: " + errorAlerts + "\n" + "Actual: "
					+ actualErrors;
			throw new Exception(message);
		}

		// Compare all the expected errors w/ actual errors
		for (WebElement errorAlert : errorAlerts) {
			if (!actualErrors.contains(errorAlert.getText())) {
				throw new Exception("Expected error not found - [" + errorAlert + "]");
			}

		}

	}
}
