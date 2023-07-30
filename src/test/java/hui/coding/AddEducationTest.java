package hui.coding;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddEducationTest {

	public static void main(String[] args) {
		WebDriver testDriver = new ChromeDriver();

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			testDriver.navigate().to("https://boratech-practice-app.onrender.com/");
			testDriver.findElement(By.linkText("Login")).click();

			testDriver.findElement(By.xpath("//input[@name='email']")).sendKeys("hui-pretender@outlook.com");
			testDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hui123456");

			testDriver.findElement(By.xpath("//input[@type='submit']")).click();

			// adding experience
			testDriver.findElement(By.xpath("//a[@href='/add-education']")).click();
			testDriver.findElement(By.xpath("//input[@name='school']")).sendKeys("George Mason University");
			testDriver.findElement(By.xpath("//input[@name='degree']")).sendKeys("Bachelor's Degree");
			testDriver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys("Biology");
			testDriver.findElement(By.xpath("//input[@name='from']")).sendKeys("09/16/2008");
			testDriver.findElement(By.xpath("//input[@name='to']")).sendKeys("05/16/2010");
			testDriver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("Study and understand living organisms.");

			testDriver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(5000);

			// validation
			testDriver.findElement(By.xpath("//tbody/tr/td[text()='George Mason University']"));
			testDriver.findElement(By.xpath("//tbody/tr/td[text()=\"Bachelor's Degree\"]"));
			testDriver.findElement(By.xpath("//tbody/tr/td/time[text()='2008/09/16']"));
			testDriver.findElement(By.xpath("//tbody/tr/td/time[text()='2010/05/16']"));
			
			System.out.println("Test passed.");
		} catch (Exception e) {
			System.out.println("Test failed!");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			testDriver.quit();
		}
	}

}
