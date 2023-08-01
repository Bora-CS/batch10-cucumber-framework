package javier;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddExperienceTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8000));

		try {

			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("javierlopez5037@gmail.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("752161jJ");
			driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

			// Waiting until add experience button is clickable
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/add-experience']"))).click();

			driver.findElement(By.xpath("//input[@name='title']")).sendKeys("Sr. Automation Engineer");
			driver.findElement(By.xpath("//input[@name='company']")).sendKeys("Ghost Tint");
			driver.findElement(By.xpath("//input[@name='location']")).sendKeys("Sterling, VA");
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys("06/28/2021");
			driver.findElement(By.xpath("//input[@name='to']")).sendKeys("07/30/23");
			driver.findElement(By.xpath("//textarea[@name='description']"))
					.sendKeys("I know how to use git with terminal....");
			driver.findElement(By.xpath("//input[@type='submit']")).click();

			// Waiting until back on dash board to start verification
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Dashboard']")));
			// Verification
			driver.findElement(By.xpath("//td[text()='Ghost Tint']"));
			driver.findElement(By.xpath("//td[text()='Sr. Automation Engineer']"));

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");

		} finally {
			driver.quit();
		}

	}

}
