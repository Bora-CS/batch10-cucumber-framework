package selenium;

import java.sql.Timestamp;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class AddEducation_SingleError {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		String username = "muradil.erkin@boratechschool.com";
		String password = "Boratech";
		String school = "BoraTech";
		String degree = "";
		String fieldOfStudy = "Test Automation Engineering";
		String from = "08/13/2020";
		String to = "";
		boolean current = true;
		String description = "Thinking about how to automate everything everyday";
		String expectedErrorMessage = "School is required";

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
			if (errorAlerts.size() != 1) {
				throw new Exception("Received more than 1 error message");
			}

			String actualErrorMessage = errorAlerts.get(0).getText();
			if (!expectedErrorMessage.equals(actualErrorMessage)) {
				throw new Exception("Error mismatch. Expected: [" + expectedErrorMessage + "] Actual: ["
						+ actualErrorMessage + "]");
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
