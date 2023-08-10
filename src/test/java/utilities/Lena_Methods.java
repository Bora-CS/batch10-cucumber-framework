package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Lena_Methods {

	
	
	public static void login(WebDriver driver, String email, String password) throws InterruptedException {
		driver.get("https://minimals.cc/auth/amplify/login?returnTo=%2Fdashboard");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password + Keys.ENTER);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Keywords.wait(2);
	}
}
