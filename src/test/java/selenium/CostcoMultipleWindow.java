package selenium;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class CostcoMultipleWindow {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();

		try {
			driver.navigate().to("https://www.costco.com/");
			driver.findElement(By.xpath("//a[contains(@data-bi-placement, 'top_scheduled_banner')]")).click();
			Set<String> handles = driver.getWindowHandles();
			String mainHandle = driver.getWindowHandle();

			for (String handle : handles) {
				if (!handle.equals(mainHandle)) {
					driver.switchTo().window(handle);
				}
			}

			Keywords.checkIfElementExists(driver,
					By.xpath("//*[contains(text(), 'Help people affected by the Hawaii wildfires.')]"),
					"We're not on the redcross page");

			driver.close();
			driver.switchTo().window(mainHandle);

			driver.findElement(By.xpath("//a[@id='header_sign_in']")).click();

			Keywords.checkIfElementExists(driver, By.xpath("//input[@id='signInName']"),
					"Email field was expected but not found");

			driver.close();

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
			e.printStackTrace();
		} finally {
			driver.quit();
		}

	}

}
