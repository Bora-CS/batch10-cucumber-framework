package Nafisa_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BoraTechLogin {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		try {
			driver.get("http://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
			System.out.println(titleText);
			
			Thread.sleep(2000);
			
			driver.findElement(By.name("email")).sendKeys("nanfeise@gmail.com");
			driver.findElement(By.name("password")).sendKeys("Nafisa1996");
			driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
			
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}

	}

}
