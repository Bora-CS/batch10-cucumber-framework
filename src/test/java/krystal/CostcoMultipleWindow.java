package krystal;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class CostcoMultipleWindow {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		

		try {

			driver.navigate().to("https://www.costco.com/");

			driver.findElement(By.xpath("(//div[@class='eco-corners-r10-groc'])[3]")).click();

			Set<String> handles = driver.getWindowHandles();
			String mainHandle = driver.getWindowHandle();

			for (String handle : handles) {
				if (!handle.equals(mainHandle)) {
					driver.switchTo().window(handle);
				}
			}

			boolean targetMessageExists = Keywords.checkIfElementExists(driver,
					By.xpath("//a[@id='what-is-costco-next']"));
			if (!targetMessageExists) {
				throw new Exception("We're not on the costco-next page ");
			}

			driver.close();
			driver.switchTo().window(mainHandle);

			driver.findElement(By.xpath("(//div[@class='eco-corners-r10-groc'])[2]")).click();

			boolean targetMessageExists2 = Keywords.checkIfElementExists(driver,
					By.xpath("//h1[@automation-id='headerOutput']"));
			if (!targetMessageExists2) {
				throw new Exception("We don't see 2-Day Delivery ");
			}

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
