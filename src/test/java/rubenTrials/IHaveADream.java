package rubenTrials;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class IHaveADream {

	public static void main(String[] args) {

		WebDriver testDriver = new ChromeDriver();

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			testDriver.navigate().to("https://boratech-practice-app.onrender.com/");
			testDriver.findElement(By.linkText("Login")).click();

			testDriver.findElement(By.xpath("//input[@name='email']")).sendKeys("hui-pretender@outlook.com");
			testDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hui123456");
			testDriver.findElement(By.xpath("//input[@type='submit']")).click();

			// fake attempt at adding education
			testDriver.findElement(By.xpath("//a[@href='/add-education']")).click();
			testDriver.findElement(By.xpath("//input[@type='submit']")).click();

			System.out.println("Test passed.");
		} catch (Exception e) {
			System.out.println("Bad shit happened!");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			testDriver.quit();
		}

	}

}
