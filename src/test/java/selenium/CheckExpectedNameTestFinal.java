package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckExpectedNameTestFinal {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		String expectedName = "Turhun Juma";

		try {
			driver.get("file:///Users/apple/projects/batch10-html-practice/index.html");

			WebElement newNameInputBox = driver.findElement(By.id("name-input"));
			newNameInputBox.sendKeys(expectedName);

			WebElement submitNameButton = driver.findElement(By.id("name-submit"));
			submitNameButton.click();

			WebElement oldNameElement = driver.findElement(By.cssSelector("#name-display"));
			String actualName = oldNameElement.getText();

			if (expectedName.equals(actualName)) {
				System.out.println("Pass");
			} else {
				System.out.println("Fail");
			}
     
			Thread.sleep(5000);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

	}

}
