package turhunjohn;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import utilities.Keywords;

public class ClickActionOnTheWebElementAtHomePractice {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		try {
			driver.get("https://www.browserstack.com/");
			Actions action = new Actions(driver);
			WebElement element = driver.findElement(By.linkText("Get started free"));
			action.moveToElement(element).click().perform();
			
			driver.navigate().back();
	
			WebElement webElement = driver.findElement(By
					.xpath("//button[@class='btn btn-secondary btn-lg col-md-3']"));
			action.moveToElement( webElement).click().perform();
			
			Keywords.wait(2);

			System.out.println(" Test pass");

		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Test failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}
	}
}
