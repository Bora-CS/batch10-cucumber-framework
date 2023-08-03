package selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstSeleniumTestFirstDayFindElementsTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		try {
			driver.navigate().to("file:///Users/apple/projects/batch10-html-practice/index.html");
			List<WebElement> allH3s = driver.findElements(By.tagName("h3"));

			for (WebElement h3 : allH3s) {
				System.out.println(h3.getText());
			}

		} catch (Exception e) {
			System.out.println("Ran into some problem");

		} finally {
			driver.quit();
		}
	}
}
