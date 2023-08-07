package hui_automation;

import java.time.Duration;

import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomationPractice0807 {

	public static void main(String[] args) {
		WebDriver autoDriver = new ChromeDriver();
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
			autoDriver.quit();
			System.out.println("Automation completed.");
		}
	}

}
