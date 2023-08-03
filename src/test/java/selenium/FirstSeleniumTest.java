package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class FirstSeleniumTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		String expectedName = "Jin Joh";
		String expectedDob = "07/29/2022";
		String formattedExpectedDob = Keywords.formatInputDate(expectedDob);

		try {
			driver.get("file:///Users/krystal/Desktop/batch10-html-practice/index.html");

			driver.findElement(By.id("name-input")).sendKeys(expectedName);
			driver.findElement(By.id("name-submit")).click();

			String actualName = driver.findElement(By.cssSelector("#name-display")).getText();

			if (!expectedName.equals(actualName)) {
				throw new Exception("Name mismatch, Expected name: " + expectedName + " vs Actual Name: " + actualName);
			}

			driver.findElement(By.id("dob-input")).sendKeys(expectedDob);
			driver.findElement(By.id("dob-submit")).click();

			String actualDob = driver.findElement(By.id("dob-display")).getText();

			if (!formattedExpectedDob.equals(actualDob)) {
				throw new Exception("DOB mismatch, Expected DOB: " + expectedDob + " vs Actual DOB: " + actualDob);
			}

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}

	}

}
