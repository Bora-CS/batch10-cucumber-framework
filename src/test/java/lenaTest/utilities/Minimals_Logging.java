package lenaTest.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class Minimals_Logging {

	public static void login(WebDriver driver, String username, String password) throws InterruptedException {
		driver.get("https://minimals.cc/auth/amplify/login");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	
		
		
				
	}
	
	
	
	
}
