package hui.coding;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddExperienceTest {

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
			testDriver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			testDriver.findElement(By.xpath("//input[@name='title']")).sendKeys("Cashier");
			testDriver.findElement(By.xpath("//input[@name='company']")).sendKeys("Walmart");
			testDriver.findElement(By.xpath("//input[@name='location']")).sendKeys("Manassas, Virginia");
			testDriver.findElement(By.xpath("//input[@name='from']")).sendKeys("06/01/2006");
			testDriver.findElement(By.xpath("//input[@name='to']")).sendKeys("07/15/2008");
			testDriver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("Payment collection.");

			testDriver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(5000);

			// validation
			testDriver.findElement(By.xpath("//tbody/tr/td[text()='Walmart']"));
			testDriver.findElement(By.xpath("//tbody/tr/td[text()='Cashier']"));
			
			System.out.println("Test passed.");
		} catch (Exception e) {
			System.out.println("Test failed!");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			testDriver.quit();
		}
	}

}
