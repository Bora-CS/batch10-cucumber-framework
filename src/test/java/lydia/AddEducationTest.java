package lydia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddEducationTest {
	public static void main(String[] args) {
		WebDriver driver= new ChromeDriver();
		
		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			
			driver.findElement(By.name("email")).sendKeys("w.lydia.liu@gmail.com");
			driver.findElement(By.name("password")).sendKeys("Liu123456");
			driver.findElement(By.xpath("//input[@value='Login']")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.cssSelector("a[href='/add-education']")).click();
			Thread.sleep(1000);
			
			driver.findElement(By.cssSelector("input[name='school']")).sendKeys("UNVA");
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("input[name='degree']")).sendKeys("Master Degree");
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("input[name='fieldofstudy']")).sendKeys("Business Administration");
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("input[name='from']")).sendKeys("01/10/2005");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[name='to']")).sendKeys("06/25/2007");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("textarea[name='description']")).sendKeys("It's 2-year full time in-school program.");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[type='submit']")).click();
			Thread.sleep(1000);
			
			
			
			
			
			
			System.out.println("Test Passed");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Test Failed");
		}finally {
			driver.quit();
		}
}
}