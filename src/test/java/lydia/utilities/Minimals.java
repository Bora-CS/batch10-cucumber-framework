package lydia.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import utilities.Keywords;

public class Minimals {
	
		public static void login (WebDriver driver, String email, String password) throws InterruptedException {
		driver.get("https://minimals.cc/auth/amplify/login");
		Keywords.wait(1);

		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		}
}

