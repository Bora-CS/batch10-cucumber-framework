package AliceSeleniumPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AliceEducation {
	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		try {
			driver.get("http://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("testemail@gmail.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Sewon12345");
			driver.findElement(By.xpath("//input[@type='submit']")).click();

			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[@href='/add-education']")).click();
			driver.findElement(By.xpath("//input[@name='school']")).sendKeys("George Mason University");
			driver.findElement(By.xpath("//input[@name='degree']")).sendKeys("BA");
			driver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys("Economics");
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys("08/14/2006");
			driver.findElement(By.xpath("//input[@name='to']")).sendKeys("05/09/2011");
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