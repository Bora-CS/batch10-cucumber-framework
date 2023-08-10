package lydia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddEducationTest {
	public static void main(String[] args) {
		WebDriver driver= new ChromeDriver();
		String school = "UNVA";
		String degree = "Master";
		String fieldOfStudy = "Business Administration";
		String from ="01/10/2005";
		String to = "06/25/2007";
		String description = "It's 2-year full time in-school program.";
		
		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			
			driver.findElement(By.name("email")).sendKeys("w.lydia.liu@gmail.com");
			driver.findElement(By.name("password")).sendKeys("Liu123456");
			driver.findElement(By.xpath("//input[@value='Login']")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//a[@href='/add-education']")).click();
			driver.findElement(By.xpath("//input[@name='school']")).sendKeys(school);
			driver.findElement(By.xpath("//input[@name='degree']")).sendKeys(degree);			
			driver.findElement(By.xpath("//input[@name='fieldOfStudy']")).sendKeys(fieldOfStudy);
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys(from);			
			driver.findElement(By.xpath("//input[@name='to']")).sendKeys(to);			
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);			
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			
			
			
			
			
			
			System.out.println("Test Passed");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Test Failed");
		}finally {
			driver.quit();
		}
}
}