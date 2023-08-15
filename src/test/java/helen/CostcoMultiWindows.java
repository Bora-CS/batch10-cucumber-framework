package helen;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CostcoMultiWindows {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();

		try {
			driver.navigate().to("https://www.costco.com/");

			// click costco next and open in new window
			driver.findElement(By.xpath("//a[@href='https://costconext.com/'][@target='_blank']")).click();
			String mainHandle = driver.getWindowHandle();
			helen.utilities.Keywords.switchToTheOtherWindow(driver);

			// validate1 by url
			String expectedURL = "https://costconext.com/";
			String currentURL = driver.getCurrentUrl();
			if (!currentURL.equals(expectedURL)) {
				throw new Exception("Not on the Costco Next page. Current URL: " + currentURL);
			}

			// validate2 by target message element
			helen.utilities.Keywords.checkIfElementExists(driver,
					By.xpath("//*[contains(text(), 'What is Costco Next?')]"), "Not on the Costco Next page");

			helen.utilities.Keywords.wait(1);

			// close Costco Next window and switch back to main window
			driver.close();
			driver.switchTo().window(mainHandle);

			helen.utilities.Keywords.wait(1);

			// click 2day
			helen.utilities.Keywords.switchToTheOtherWindow(driver);
			driver.findElement(By.xpath("//a[@href='/all-costco-grocery.html']")).click();

			// validate by target message element
			helen.utilities.Keywords.checkIfElementExists(driver, By.xpath("//*[@automation-id='headerOutput']"),
					"Not on the 2-Day Delivery page");

			helen.utilities.Keywords.wait(1);

			// close the window
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
