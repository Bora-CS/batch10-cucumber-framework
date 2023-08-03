package selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckIfElementExists {

//==> Task
//	// Create a function, that checks to see if an element exists
//	// Params: WebDriver driver, By locator
//	// Return type: boolean
//	==> Solution

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		try {
			driver.navigate().to("file:///Users/apple/projects/batch10-html-practice/index.html");

			boolean result = checkIfElementExists_V1(driver, By.tagName("h1"));
			System.out.println(result);

		} catch (Exception e) {
			System.out.println("Ran into some problem");

		} finally {
			driver.quit();
		}
	}

	public static boolean checkIfElementExists_V1(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean checkIfElementExists_V2(WebDriver driver, By locator) {
		return driver.findElements(locator).size() > 0;

	}
}