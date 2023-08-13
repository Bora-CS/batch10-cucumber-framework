package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class CostcoMultipleWindow_2 {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();

		try {
			driver.navigate().to("https://www.costco.com/");
			driver.findElement(By.xpath("//a[@href='https://costconext.com/'][@target]")).click();
			String mainHandle = driver.getWindowHandle();
			Keywords.switchToTheOtherWindow(driver);

			String expectedUrl = "https://costconext.com/";
			String actualUrl = driver.getCurrentUrl();
			if (!expectedUrl.equals(actualUrl)) {
				throw new Exception("Expected to be on the Costco Next page. Current Url: " + actualUrl);
			}

			Keywords.checkIfElementExists(driver,
					By.xpath("//a[@id='what-is-costco-next'][text()='What is Costco Next?']"),
					"Costco Next title text is not found");

			driver.close();
			driver.switchTo().window(mainHandle);

			driver.findElement(By.xpath("//a[@href='/all-costco-grocery.html']")).click();
			Keywords.checkIfElementExists(driver,
					By.xpath("//*[@automation-id=\"headerOutput\"][contains(text(), '2-Day Delivery')]"),
					"Title text '2-Day Delivery' is not found");

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
