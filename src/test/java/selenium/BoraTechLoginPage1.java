package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BoraTechLoginPage1 {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		try {
			driver.navigate().to("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			
			String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
			System.out.println(titleText);

			String subtitleText = driver.findElement(By.cssSelector("p.lead")).getText();
			System.out.println(subtitleText);
			
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("turhunjohn@gmail.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Tz200561$");
			
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			//validation
		    driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
		    
		    
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