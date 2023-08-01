package krystal;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class KrystalAddEducationTest {

	public static void main(String[] args) {
		

	WebDriver driver = new ChromeDriver();
		
		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.name("email")).sendKeys("krystal.lee.om@gmail.com");
			driver.findElement(By.name("password")).sendKeys("123456");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@href='/add-education']")).click();
			Thread.sleep(1000);
			driver.findElement(By.name("school")).sendKeys("Bora School");
			Thread.sleep(1000);
			driver.findElement(By.name("degree")).sendKeys("Oracle Certified Associate - Java SE8 ");
			Thread.sleep(1000);
			driver.findElement(By.name("fieldofstudy")).sendKeys("TEST AUTOMATION ENGINEER");
			Thread.sleep(1000);
			driver.findElement(By.name("school")).sendKeys("Bora School");
			driver.findElement(By.name("from")).sendKeys("02/17/2015");
			driver.findElement(By.name("to")).sendKeys("03/02/2016");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("lalala");
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(1000);
			
			
		} catch (Exception e) {
			System.out.println("Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}
		
		

	}

}