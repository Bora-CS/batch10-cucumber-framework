package Nafisa_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Addexperience {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		String username = "nanfeise@gmail.com";
		String password = "Nafisa1996";
		String Jobtitle = "Social worker";
		String company = "Church World Service";
		String location ="Harrisonburg VA";
		String from = "02/01/2018";
		String to = "02/01/2019";

		try {
			driver.get("http://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			
			Thread.sleep(2000);
			
			
			String subtitleText = driver.findElement(By.xpath("//p[@class='lead']")).getText();
			System.out.println(subtitleText);
			
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password );
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(3000);
			String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
			if(!titleText.equals("Dashboard")) {
				throw new Exception("Title not match, va actual : " +titleText);
			}

			System.out.println("Test Passed");
			
			driver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='title']")).sendKeys(Jobtitle);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='company']")).sendKeys(company);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='location']")).sendKeys(location);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys(from);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@name='to']")).sendKeys(to);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@class='btn btn-primary my-1']")).click();
			Thread.sleep(2000);
			
			
			
			
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}




	}

}
