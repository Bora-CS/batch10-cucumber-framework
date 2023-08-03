package AliceSeleniumPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AliceExperience {
	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		try {
			driver.get("http://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("testemail@gmail.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Sewon12345");
			driver.findElement(By.xpath("//input[@type='submit']")).click();

			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			driver.findElement(By.xpath("//input[@name='title']")).sendKeys("Mortgage Underwriter");
			driver.findElement(By.xpath("//input[@name='company']")).sendKeys("Atlantic Coast Mortgage");
			driver.findElement(By.xpath("//input[@name='location']")).sendKeys("Fairfax, VA");
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys(" 02/02/2020");
			driver.findElement(By.xpath("//input[@name='current']")).sendKeys("current");
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("Mortgage underwriting");
			driver.findElement(By.xpath("//input[@type='submit']")).click();

			Thread.sleep(3000);

			System.out.println("Test Passed");

		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}

	}

}
