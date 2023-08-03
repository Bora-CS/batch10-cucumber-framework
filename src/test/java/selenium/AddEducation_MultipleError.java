package selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class AddEducation_MultipleError {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		String username = "muradil.erkin@boratechschool.com";
		String password = "Boratech";
		String school = "";
		String degree = "";
		String fieldOfStudy = "Test Automation Engineering";
		String from = "08/13/2020";
		String to = "";
		boolean current = true;
		String description = "Thinking about how to automate everything everyday";
		List<String> expectedErrors = new ArrayList<>();
		expectedErrors.add("School is required");
		expectedErrors.add("Degree is required");

		try {
			driver.get("https://boratech-practice-app.onrender.com/login");
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password + Keys.ENTER);
			Keywords.wait(2);

			driver.findElement(By.xpath("//a[@href='/add-education']")).click();
			Keywords.wait(2);

			driver.findElement(By.xpath("//input[@name='school']")).sendKeys(school);
			driver.findElement(By.xpath("//input[@name='degree']")).sendKeys(degree);
			driver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys(fieldOfStudy);
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys(from);
			if (current) {
				driver.findElement(By.xpath("//input[@name='current']")).click();
			} else {
				driver.findElement(By.xpath("//input[@name='to']")).sendKeys(to);
			}
			driver.findElement(By.tagName("textarea")).sendKeys(description);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Keywords.wait(2);

			List<WebElement> errorAlerts = driver.findElements(By.xpath("//div[@class='alert alert-danger']"));
			List<String> actualErrors = new ArrayList<>();
			for (WebElement errorAlert : errorAlerts) {
				actualErrors.add(errorAlert.getText());
			}

			// check to see we have the same number of errors
			if (actualErrors.size() != expectedErrors.size()) {
				String message = "Number of errors don't match\n" + "Expected: " + expectedErrors + "\n" + "Actual: "
						+ actualErrors;
				throw new Exception(message);
			}

			// Compare all the expected errors w/ actual errors
			for (String expectedError : expectedErrors) {
				if (!actualErrors.contains(expectedError)) {
					throw new Exception("Expected error not found - [" + expectedError + "]");
				}
			}

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.close();
			driver.quit();
		}

	}

}
