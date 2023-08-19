package hui_automation.selenium;

import java.time.Duration;

import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import hui_automation.utilities.Testkeys;

public class AutomateTargetRetail {

	public static void main(String[] args) {
		WebDriver autoDriver = Testkeys.getChromeDriver();
		try {
			autoDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			autoDriver.manage().window().maximize();
			String targetRetailUrl = "https://www.target.com/";
			autoDriver.get(targetRetailUrl);
//			WebElement searchBox = autoDriver.findElement(By.xpath("//input[@type='search']"));
//			searchBox.sendKeys("men's shoes"+ Keys.ENTER);
			WebElement categories = autoDriver.findElement(By.xpath("//a[@aria-label='Categories']"));
			categories.click();

			Testkeys.pause(3);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Automation completed.");
			Testkeys.terminate(autoDriver);
		}
	}

}
