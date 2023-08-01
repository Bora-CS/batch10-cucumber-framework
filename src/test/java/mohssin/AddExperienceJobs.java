package mohssin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddExperienceJobs {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		
		String username = "username";
		String password = "password";

		try {
			driver.get("https://boratech-practice-app.onrender.com");
			
			driver.findElement(By.linkText("Login")).click();
			
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
			
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(2000);
	
			driver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//input[@name='title']")).sendKeys("QA Testing");
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//input[@name='company']")).sendKeys("BoraTech");
			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@name='location']")).sendKeys("Remote");
			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@name='from']")).sendKeys("01/01/2023");
			Thread.sleep(2000);
	
			
			// Assuming driver is already initialized

			// Locate the checkbox element
			WebElement checkBoxElement = driver.findElement(By.xpath("//*[@type='checkbox']"));

			// Check if the checkbox is enabled
			boolean isEnabled = checkBoxElement.isEnabled();

			// performing click operation if the checkbox is enabled
			if (isEnabled) {
			    checkBoxElement.click();
			}

			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("Ensure the quality and reliability"
					+ " of software or products. I'am responsible for designing and executing test plans, "
					+ "identifying bugs, and verifying that the software meets specified requirements. "
	
					+ "As a QA , i help my team to maintain a high standard of quality for the end product.");
			Thread.sleep(2000);

			driver.findElement(By.xpath("//*[@type='submit']")).click();
			Thread.sleep(2000);
			
			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}

	}

}
