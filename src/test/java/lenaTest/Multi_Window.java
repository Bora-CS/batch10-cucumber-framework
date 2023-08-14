package lenaTest;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import utilities.Keywords;

public class Multi_Window {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		Actions ac = new Actions(driver);
		try {
			driver.navigate().to("https://www.costco.com/");

			WebElement stuff = driver.findElement(By.xpath("//p[text()='Costco NEXT']"));
//			ac.scrollToElement(stuff).perform();
			Keywords.wait(2);
			stuff.click();

			Keywords.wait(1);

			Set<String> handles = driver.getWindowHandles();
			String mainHandle = driver.getWindowHandle();

			for (String handle : handles) {
				if (!handle.equals(mainHandle)) {
					driver.switchTo().window(handle);
				}
			}

			boolean targetMessageExists = Keywords.checkIfElementExists(driver,
					By.xpath("//a[text()='What is Costco Next?']"));
			if (!targetMessageExists) {
				throw new Exception("We're not on the Costco Next page");
			}

			driver.close();

			driver.switchTo().window(mainHandle);
			Keywords.wait(1);
			driver.findElement(By.xpath("//a[@href='/all-costco-grocery.html']")).click();

			boolean message = Keywords.checkIfElementExists(driver, By.xpath("//h1[@class='t1-style']"));
			if (!message) {
				throw new Exception("mmessage was not found");
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
