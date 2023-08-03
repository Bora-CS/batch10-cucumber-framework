package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAtHome {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		String expectedName = "Alice";

		try {
			driver.navigate().to("file:///Users/apple/projects/batch10-html-practice/index.html");
			WebElement titleText= driver.findElement(By.tagName("h1"));
			String  titleTextContent=titleText.getText();
			Thread.sleep(3000);
			System.out.println("Title Text Is : "  +  titleTextContent);
			
			driver.findElement(By.id("name-input")).sendKeys(expectedName );
			
			driver.findElement(By.id("name-submit")).click();

			String actualNmae = driver.findElement(By.id("name-display")).getText();
			
		if(expectedName.equals(actualNmae)) {
		System.out.println("test pass");
			
		}else {
			System.out.println("test fail");
		}
		Thread.sleep(3000);

		} catch (Exception e) {
			System.out.println("Reason:" + e.getMessage());

		} finally {

			driver.quit();
		}
	}
}
