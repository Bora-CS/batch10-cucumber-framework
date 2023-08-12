package rubenTrials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UpOrDown {

	public static void main(String[] args) {
		// Set the path to the chromedriver executable

		// Create a new instance of the Chrome driver
		WebDriver driver = new ChromeDriver();

		try {
			// Navigate to the TradingView website
			driver.get("https://www.tradingview.com/");

			// Find the sign in button and click it
			driver.findElement(By.xpath("//button[@type='button']")).click();
			driver.findElement(By.xpath("//button[@data-name='header-user-menu-sign-in']")).click();
			driver.findElement(By.xpath("//button[@name='Email']")).click();
			Thread.sleep(3000);
			
			WebElement emailInput = driver.findElement(By.xpath("//input[@id='id_username']"));
			emailInput.sendKeys("ohwellruben17@icloud.com");
			Thread.sleep(2000);

			// Find the email input field and enter your email address

			// Find the password input field and enter your password
			WebElement passwordInput = driver.findElement(By.xpath("//input[@id='id_password']"));
			passwordInput.sendKeys("12345qwerT!");
			Thread.sleep(2000);
			// Find the sign in button and click it
			WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
			submitButton.click();

			Thread.sleep(3000);

		} catch (Exception e) {
			System.out.println("FailedTest");
			System.out.println("because of: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Close the browser window
			driver.quit();
		
		}
	}
}
