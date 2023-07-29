package selenium;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstSeleniumTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		String expectedName = "Jin Joh";
		String expectedDob = "07/29/2022";
		String formattedExpectedDob = formatInputDate(expectedDob);

		try {
			driver.get("file:///Users/bora/Desktop/batch10-html-practice/index.html");

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

	/**
	 * Converts date string from "MM/dd/yyyy" format to "yyyy-mm-dd" format
	 * @param inputDateString
	 * @return formattedDateString
	 */
	private static String formatInputDate(String inputDateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date = LocalDate.parse(inputDateString, formatter);
		return date.toString();
	}
}
