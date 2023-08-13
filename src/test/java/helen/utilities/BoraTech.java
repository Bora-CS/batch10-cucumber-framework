package helen.utilities;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import helen.pojo.Education;
import helen.pojo.Experience;

public class BoraTech {

	public static void login(WebDriver driver, String username, String password) throws InterruptedException {
		driver.get("https://boratech-practice-app.onrender.com/login");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password + Keys.ENTER);
		Keywords.wait(2);
	}

	public static void deleteAllExperiences(WebDriver driver) throws InterruptedException {
		int count = 0;
		while (true) {
			try {
				driver.findElement(By.xpath(
						"//h2[text()='Experience Credentials']/following-sibling::table[1]//button[@class='btn btn-danger']"))
						.click();
				Keywords.wait(1);
				count++;
			} catch (NoSuchElementException e) {
				break;
			}
		}
		if (count == 0) {
			System.out.println("No experience found that can be deleted.");
		} else {
			System.out.println("Successfully deleted " + count + " experience(s).");
		}
	}

	public static void addExperience(WebDriver driver, Experience experience) throws Exception {
		driver.navigate().to("https://boratech-practice-app.onrender.com/dashboard");
		driver.findElement(By.xpath("//a[@href='/add-experience']")).click();
		Keywords.wait(2);

		driver.findElement(By.xpath("//input[@name='title']")).sendKeys(experience.jobTitle);
		driver.findElement(By.xpath("//input[@name='company']")).sendKeys(experience.company);
		driver.findElement(By.xpath("//input[@name='location']")).sendKeys(experience.location);
		driver.findElement(By.xpath("//input[@name='from']")).sendKeys(experience.from);
		if (experience.current) {
			driver.findElement(By.xpath("//input[@name='current']")).click();
		} else {
			driver.findElement(By.xpath("//input[@name='to']")).sendKeys(experience.to);
		}
		driver.findElement(By.tagName("textarea")).sendKeys(experience.description);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Keywords.wait(2);

	}

	public static void experienceValidation(WebDriver driver, Experience experience) throws Exception {
		if (experience.isHappyPath) {
			experienceValidationHappyPath(driver, experience);
		} else {
			experienceValidationSadPath(driver, experience);
		}
	}

	private static void experienceValidationHappyPath(WebDriver driver, Experience experience) throws Exception {
		// locate experience table
		By experienceTableLocator = RelativeLocator.with(By.tagName("table"))
				.below(By.xpath("//h2[text()='Experience Credentials']"));
		WebElement experienceTable = driver.findElement(experienceTableLocator);
		List<WebElement> rows = experienceTable.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

		boolean found = false;
		for (WebElement row : rows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			String company = cells.get(0).getText();
			String jobTitle = cells.get(1).getText();
			if (experience.company.equals(company) && experience.jobTitle.equals(jobTitle)) {
				found = true;
				break;
			}
		}

		if (!found) {
			throw new Exception("The newly entered experience was not found");
		}
	}

	private static void experienceValidationSadPath(WebDriver driver, Experience experience) throws Exception {
		// Get all the error alert text into a List
		List<WebElement> errorAlerts = driver.findElements(By.xpath("//div[@class='alert alert-danger']"));
		List<String> actualErrors = new ArrayList<>();
		for (WebElement errorAlert : errorAlerts) {
			actualErrors.add(errorAlert.getText());
		}

		// check to see we have the same number of errors
		if (actualErrors.size() != experience.expectedErrors.size()) {
			String message = "Number of errors don't match\n" + "Expected: " + experience.expectedErrors + "\n"
					+ "Actual: " + actualErrors;
			throw new Exception(message);
		}

		// Compare all the expected errors w/ actual errors
		for (String expectedError : experience.expectedErrors) {
			if (!actualErrors.contains(expectedError)) {
				throw new Exception("Expected error not found - [" + expectedError + "]");
			}
		}
	}

	public static void deleteAllEducation(WebDriver driver) throws InterruptedException {
		int count = 0;
		while (true) {
			try {
				driver.findElement(By.xpath(
						"//h2[text()='Education Credentials']/following-sibling::table[1]//button[@class='btn btn-danger']"))
						.click();
				Keywords.wait(1);
				count++;
			} catch (NoSuchElementException e) {
				break;
			}
		}
		if (count == 0) {
			System.out.println("No education found that can be deleted.");
		} else {
			System.out.println("Successfully deleted " + count + " education(s).");
		}
	}

	public static void addEducation(WebDriver driver, Education education) throws Exception {
		// back to dashboard for refresh start
		driver.navigate().to("https://boratech-practice-app.onrender.com/dashboard");
		driver.findElement(By.xpath("//a[@href='/add-education']")).click();
		Keywords.wait(2);

		driver.findElement(By.name("school")).sendKeys(education.school);
		driver.findElement(By.name("degree")).sendKeys(education.degree);
		driver.findElement(By.name("fieldofstudy")).sendKeys(education.fieldOfStudy);
		driver.findElement(By.xpath("//input[@name='from']")).sendKeys(education.from);

		if (education.current) {
			driver.findElement(By.xpath("//input[@name='current']")).sendKeys(education.to);
		} else {
			driver.findElement(By.xpath("//input[@name='to']")).sendKeys(education.to);
		}
		driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(education.description);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Keywords.wait(2);

	}

	public static void educationValidation(WebDriver driver, Education education) throws Exception {
		if (education.isHappyPath) {
			educationValidationHappyPath(driver, education);
		} else {
			educationValidationSadPath(driver, education);
		}
	}

	private static void educationValidationHappyPath(WebDriver driver, Education education) throws Exception {
		// locate the education table
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

	private static void educationValidationSadPath(WebDriver driver, Education education) throws Exception {
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
