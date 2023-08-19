package krystal;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class KrystalAddEducationTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		
		String username = "krystal.lee.om@gmail.com";
		String password = "123456";
		String school = "Bora School";
		String degree = "Oracle Certified Associate - Java SE8 ";
		String fieldOfStudy = "Test Automation Engineering";
		String from = "08/13/2020";
		String to = "";
		boolean current = true;
		String description = "Thinking about how to automate everything everyday";
		
		List<String> expectedErrors = new ArrayList<>();
		expectedErrors.add("School is required");
		expectedErrors.add("Degree is required");
		

		driver.get("https://boratech-practice-app.onrender.com/");

		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.name("email")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		driver.findElement(By.xpath("//a[@href='/add-education']")).click();

		driver.findElement(By.name("school")).sendKeys(school);

		driver.findElement(By.name("degree")).sendKeys(degree);

		driver.findElement(By.name("fieldofstudy")).sendKeys(fieldOfStudy);


		driver.findElement(By.name("from")).sendKeys(from);
		driver.findElement(By.name("to")).sendKeys("");

		driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);

		driver.findElement(By.xpath("//input[@type='submit']")).click();

	}
	
	public static void educationValidation(WebDriver driver, Pojo_Education_Krystal education) throws Exception {
		if (education.isHappyPath) {
			educationValidationHappyPath(driver, education);
		} else {
			educationValidationSadPath(driver, education);
		}
	}

	private static void educationValidationHappyPath(WebDriver driver, Pojo_Education_Krystal education) throws Exception {
		By educationTableLocator = RelativeLocator.with(By.tagName("table"))
				.below(By.xpath("//h2[text()='Education Credentials']"));
		WebElement educationTable = driver.findElement(educationTableLocator);
		List<WebElement> rows = educationTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

		boolean found = false;
		for (WebElement row : rows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			String school = cells.get(0).getText();
			String degree = cells.get(1).getText();
			if (education.school.equals(school) && education.degree.equals(degree)) {
				found = true;
				break;
			}
		}

		if (!found) {
			throw new Exception("The newly entered education was not found");
		}
	}

	private static void educationValidationSadPath(WebDriver driver, Pojo_Education_Krystal education) throws Exception {
		// Get all the error alert text into a List
		List<WebElement> errorAlerts = driver.findElements(By.xpath("//div[@class='alert alert-danger']"));
		List<String> actualErrors = new ArrayList<>();
		for (WebElement errorAlert : errorAlerts) {
			actualErrors.add(errorAlert.getText());
		}

		// check to see we have the same number of errors
		if (actualErrors.size() != education.expectedErrors.size()) {
			String message = "Number of errors don't match\n" + "Expected: " + education.expectedErrors + "\n"
					+ "Actual: " + actualErrors;
			throw new Exception(message);
		}

		// Compare all the expected errors w/ actual errors
		for (String expectedError : education.expectedErrors) {
			if (!actualErrors.contains(expectedError)) {
				throw new Exception("Expected error not found - [" + expectedError + "]");
			}
		}
	}

}
