package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstSeleniumTestFirstDaySearchBoxTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		try {
			driver.navigate().to("https://www.google.com/");
			WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys("BoraTech" + Keys.ENTER);

			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("Ran into some problem");

		} finally {
			driver.quit();
		}
	}
}