package helen_profile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Experience_Profile {

	public static void main(String[] args) {
	
		WebDriver driver = new ChromeDriver();
		
		
		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.name("email")).sendKeys("helenhjahn@gmail.com");
			driver.findElement(By.name("password")).sendKeys("06102021");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			Thread.sleep(2000);
			
			//validation on dashboard
			String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
			if (!titleText.equals("Dashboard")) {
				throw new Exception ("Title Text not matching. Expected: Dashboard VS actual: " + titleText);
			}
			
			
			driver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			
			
			//validation on experience page
			String titleText2 = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
			if (!titleText2.equals("Add An Experience")) {
				throw new Exception ("Title Text not matching. Expected: Add An Experience VS actual: " + titleText2);
			}
			
			//adding inputs
			driver.findElement(By.name("title")).sendKeys("Test Automation Engineer");
			driver.findElement(By.name("company")).sendKeys("Bora Tech");
			driver.findElement(By.name("location")).sendKeys("Annandale, VA");
			driver.findElement(By.xpath("//input[@type='date']")).sendKeys("05/01/2022");
			driver.findElement(By.xpath("//input[@type='checkbox']")).click();
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("Build and maintain an automation framework for the software using Selenium.");	
			
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			//validation for inputs?
			
			
			System.out.println("Test Passed");
			
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: "+ e.getMessage());	
		} finally {
			driver.quit();	
		}
	}
}
