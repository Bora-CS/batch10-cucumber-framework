package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckExpectedNameTestBeforeAfter {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		String expectedName = "Turhunjohn Juma";

		try {
			driver.get("file:///Users/apple/projects/batch10-html-practice/index.html");

			WebElement oldNameElement = driver.findElement(By.cssSelector("#name-display"));
			System.out.println("Before: " + oldNameElement.getText());

			Thread.sleep(2000);

			WebElement newNameInputBox = driver.findElement(By.id("name-input"));
			newNameInputBox.sendKeys(expectedName);
			Thread.sleep(2000);

			WebElement submitNameButton = driver.findElement(By.id("name-submit"));
			submitNameButton.click();

			Thread.sleep(2000);

		System.out.println("After: " + oldNameElement.getText());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

	}

}
