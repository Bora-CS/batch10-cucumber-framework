package ardal_practice;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CostcoMultiWindowTask {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();

		try {

			driver.navigate().to("https://www.costco.com/");

			String mainTab = driver.getWindowHandle();
			String costcoNextXpath = "//a[@href='https://costconext.com/']//div[@class='eco-corners-r10-groc']//img[@class='img-responsive']";
			driver.findElement(By.xpath(costcoNextXpath)).click();

			Set<String> handles = driver.getWindowHandles();
			String secondTab = "";

			for (String handle : handles) {
				if (!handle.equals(mainTab)) {
					secondTab = handle;
					driver.switchTo().window(secondTab);
				}
			}

			String expectedURl = "https://costconext.com/";
			String actualURL = driver.getCurrentUrl();

			if (!expectedURl.equals(actualURL)) {
				throw new Exception("Expected URL don't match");
			}

			String expectedTitle = "What is Costco Next?";
			String actualTitle = driver.findElement(By.xpath("//a[@id='what-is-costco-next']")).getText();

			if (!expectedTitle.contains(actualTitle)) {
				throw new Exception("Expected title message don't match, \nExpected: " + expectedTitle + "\nActual: "
						+ actualTitle);
			}

			driver.close();

			driver.switchTo().window(mainTab);

			driver.findElement(By.xpath("//p[normalize-space()='2-Day']")).click();

			String expectedIntro = "2-Day Delivery";
			String actualIntro = driver.findElement(By.xpath("//h1[@automation-id='headerOutput']")).getText();

			if (!expectedIntro.equals(actualIntro)) {
				throw new Exception(
						"Expected intro message not found \nExpected:" + expectedIntro + "\nActual" + actualIntro);
			}

			driver.close();

			System.out.println("Test passed");

		} catch (Exception e) {
			System.out.println("Test Failed + " + e.getMessage());
			e.getStackTrace();
		} finally {
			driver.quit();
		}

	}

}
