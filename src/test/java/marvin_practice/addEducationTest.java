package marvin_practice;

import java.io.ObjectInputStream.GetField;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class addEducationTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		
		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.xpath("//li/a[@href='/login']")).click();


			driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("lopezmarvin678@gmail.com");
			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("MlBb721031@");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[@href='/add-education']")).click();
			driver.findElement(By.xpath("//input[@placeholder='* School or Bootcamp']")).sendKeys("George Mason University");
			driver.findElement(By.xpath("//input[@placeholder='* Degree or Certificate']")).sendKeys("Bachelor Degree");
			driver.findElement(By.xpath("//input[@placeholder='Field of Study']")).sendKeys("Criminal Justice");
			
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys("09/08/2016");
			driver.findElement(By.xpath("//input[@name='to']")).sendKeys("09/13/2020");
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("General Studies done, with specialization in Criminal System");
			
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//input[@type='submit']")).click();
		
//validation
			driver.findElement(By.xpath("\"//tbody/tr/td[text()='George Mason University']\""));
			driver.findElement(By.xpath("//td[text()='Bachelor Degree']"));
			
			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
			
		}finally {
			driver.quit();
		}
	}

}
