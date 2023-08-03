package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BoraTechLoginDashboardValidation {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		try {
			driver.navigate().to("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("turhunjuma@gmail.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("turhunjuma");

			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			Thread.sleep(2000);

			// validation   		
			String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
			if (!titleText.equals("Dashboard")) {
				throw new Exception("Title Text doesn't match, Excepted:[ Dashboard ]vs Actual: " + "[" +titleText+ "]");
			}

			String currentUrl = driver.getCurrentUrl();
			if (!currentUrl.endsWith("/dashboard")) {
             throw new Exception
                 ("Page url is wrong, Excepted url contains: /dashboard vs Actual url: " + currentUrl);
			}
			
		driver.findElement(By.xpath("//a[@href='#!']/span[text()='Logout']"));
			
			
			System.out.println("Test Passed");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}

	}
}
