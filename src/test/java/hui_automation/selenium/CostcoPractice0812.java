package hui_automation.selenium;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import hui_automation.utilities.Testkeys;

public class CostcoPractice0812 {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			driver.manage().window().maximize();
			driver.get("https://www.costco.com/");
			String mainHandle = driver.getWindowHandle();
			driver.findElement(By.xpath("//a[@href=\"https://costconext.com/\"]//p[text()='Costco NEXT']/../.."))
					.click();
			Set<String> handles = driver.getWindowHandles();
			for (String handle : handles) {
				if (!handle.equals(mainHandle))
					driver.switchTo().window(handle);
			}
			Testkeys.pause(driver, 2);
			String url = driver.getCurrentUrl();
			if (!url.equals("https://costconext.com/"))
				throw new Exception("Wrong url.");
			boolean isTargetText = Testkeys.containsElement(driver, By.xpath("//*[text()='What is Costco Next?']"));
			if (!isTargetText)
				throw new Exception("Target text not found.");
			driver.close();

			driver.switchTo().window(mainHandle);
			Testkeys.clickDropDown(driver, By.xpath("//a[@href='/all-costco-grocery.html']"));
			String targetText = driver.findElement(By.xpath("//h1[@automation-id='headerOutput']")).getText();
			String expectedText = "2-Day Delivery";

			if (!targetText.equals(expectedText))
				throw new Exception(String.format("Expected text not found: %s%n", expectedText));

			System.out.println("Test passed.");
		} catch (Exception e) {
			System.out.println("Test failed.");
			System.out.println("Reason: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Testkeys.terminate(driver);
		}
	}

}
