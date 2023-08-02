package krystal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class KrystalAddExperienceTest {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		
		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.name("email")).sendKeys("krystal.lee.om@gmail.com");
			driver.findElement(By.name("password")).sendKeys("123456");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			driver.findElement(By.name("title")).sendKeys("Test Automation Engineer");
			driver.findElement(By.name("company")).sendKeys("BoraTech");
			driver.findElement(By.name("location")).sendKeys("Fairfax, VA");
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys("11/01/2016");
			driver.findElement(By.name("current")).click();
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("I love my job!");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			Thread.sleep(3000);
			
			
		} catch (Exception e) {
			System.out.println("Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}
		
		

	}

}
